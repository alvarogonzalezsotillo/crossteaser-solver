package dideco

import com.typesafe.scalalogging.slf4j.LazyLogging

import scala.annotation.tailrec


/**
 * Created by alvaro on 31/08/2014.
 */



trait BFSNode[T]{
  def node : T
  def depth : Long
  def parent : BFSNode[T]
  def children : Seq[BFSNode[T]]
  def pathToRoot : Seq[BFSNode[T]] = {

    def path(n:BFSNode[T]) : List[BFSNode[T]] = n.parent match {
      case null => List(n)
      case p => n :: path(p)
    }

    path(this)
  }

}

trait BFS[T]{


  def search( msLimit: Int = -1 , nodesLimit: Int = -1 ) : Option[BFSNode[T]]
  def expandedNodes : Seq[BFSNode[T]]
  def nodesToExpand : Seq[BFSNode[T]]
  def allNodes : Seq[BFSNode[T]]

  object currentInfo{
    def numberOfExpandedNodes = expandedNodes.size
    def numberOfNodesToExpand = nodesToExpand.size

    override def toString = s"expandedNodes:${expandedNodes.size}  nodesToExpand:${nodesToExpand.size}"
  }

  override def toString = currentInfo.toString
}

object BFS extends LazyLogging{

  type expandFunction[T] = (T) => Seq[T]
  type equalFunction[T] = (T,T) => Boolean
  type finalFunction[T] = (T) => Boolean
  type heuristicFunction[T] = (T) => Long


  trait BFSDefinition[T]{
    def expand( t:T ) : Seq[T]
    def equal(a:T,b:T) = ordering.equiv(a,b)
    def ordering: Ordering[T]
    def found( t:T ) : Boolean
    def heuristic(t:T) = 0L
    def hashable( t:T) : AnyRef
  }


  private class BFSImpl[T]( initial:T, definition: BFSDefinition[T] ) extends BFS[T] {


    val nodeOrdering = Ordering.fromLessThan{ (n1: BFSNode[T], n2: BFSNode[T]) =>

      val v1 = n1.depth + definition.heuristic(n1.node)
      val v2 = n2.depth + definition.heuristic(n2.node)
      val ret = v1 - v2
      if( ret < 0 ){
        true
      }
      else if( ret > 0 ){
        false
      }
      else{
        definition.ordering.lt( n1.node,  n2.node )
      }
    }

    val _nodesToExpand = new collection.mutable.TreeSet()(nodeOrdering)
    val _expandedNodes = collection.mutable.Set[BFSNode[T]]()
    val _allNodes = collection.mutable.Map[AnyRef, BFSNode[T]]()

    _nodesToExpand += getOrCreateNode(0, null)(initial)


    override def expandedNodes = _expandedNodes.toSeq
    override def nodesToExpand = _nodesToExpand.toSeq
    override def allNodes = _allNodes.values.toSeq

    class BFSNodeImpl(val node: T, val depth: Long, val parent: BFSNode[T]) extends BFSNode[T] {

      override val toString = node.toString

      lazy val children = computeChildren


      def computeChildren: Seq[BFSNode[T]] = {
        definition.expand(node).
          filter(!definition.equal(_, node)).
          map(getOrCreateNode(depth + 1, this))
      }

      override def equals(o: Any): Boolean = o match{
        case n: BFSNode[T] => definition.equal( n.node, node )
        case _ => false
      }
    }


    def getOrCreateNode(depth: Long, parent: BFSNode[T])(n: T): BFSNode[T] = {
      val h = definition.hashable(n)
      val ret = _allNodes.get(h) match{
        case None =>
          logger.debug( "getOrCreateNode:  creando nuevo nodo para " + n.toString )
          val node = new BFSNodeImpl(n, depth, parent)
          _allNodes(h) = node
          node

        case Some(found) =>
          logger.debug( "getOrCreateNode:  hit:" + found  + " -- " + n )
          found

      }

      if (!_expandedNodes.contains(ret)) {
        logger.debug( "getOrCreateNode:  no estaba en la lista de expandir" )
        _nodesToExpand += ret
      }
      ret
    }

    def nextNodeToExpand: Option[BFSNode[T]] = {

      val ret = {
        if (_nodesToExpand.size > 0) {
          Some(_nodesToExpand.firstKey)
        }
        else {
          None
        }
      }

      logger.debug("nextNodeToExpand:" + ret)

      ret
    }

    def expandNode(n: BFSNode[T]): Option[BFSNode[T]] = {

      logger.debug( "expandNode " + n )

      if( definition.found(n.node) ){
        Some(n)
      }
      else {
        logger.debug( "expandNode: " + n.children.mkString(","))

        _nodesToExpand ++= n.children.filterNot(_expandedNodes.contains(_))
        _nodesToExpand -= n
        _expandedNodes += n

        logger.debug("expandNode: nodesToExpand:" + _nodesToExpand.mkString(","))

        n.children.map(_.node).find(definition.found).map(n => _allNodes(definition.hashable(n)))
      }
    }

    def search(msLimit: Int = -1, nodesLimit: Int = -1): Option[BFSNode[T]] = {



      val initMS = System.currentTimeMillis()

      @tailrec
      def search_tailrec(limit: Int) : Option[BFSNode[T]] = {

        if( limit % 10000 == 0 ) {
          val r = Runtime.getRuntime
          logger.info( s"limit $limit\n\tmaxMemory:  \t${r.maxMemory()}\n\ttotalMemory:\t${r.totalMemory()}\n\tfreeMemory: \t${r.freeMemory()}" )

        }

        if( msLimit != -1 && (initMS + msLimit < System.currentTimeMillis()) ) {
          logger.warn("search: timeout")
          None
        }
        else if (limit == 0) {
          logger.warn("search: node limit")
          None
        }
        else nextNodeToExpand match {
          case Some(next) =>

            expandNode(next) match {
              case Some(n) =>
                Some(n)

              case None =>
                search_tailrec(limit - 1)
            }

          case None =>
            None
        }
      }

      search_tailrec(nodesLimit)
    }

  }

  def apply[T]( node: T, d: BFSDefinition[T] ) : BFS[T] = new BFSImpl(node, d)


}

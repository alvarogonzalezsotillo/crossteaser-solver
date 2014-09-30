package dideco

import com.typesafe.scalalogging.slf4j.LazyLogging


/**
 * Created by alvaro on 31/08/2014.
 */



trait BFS[T]{

  val bfsNodeOrdering = Ordering.by( (n: BFSNode) => n.node.toString )

  trait BFSNode{
    def node : T
    def depth : Long
    def parent : BFSNode
    def children : Seq[BFSNode]
    def pathToRoot : Seq[BFSNode] = {

      def path(n:BFSNode) : List[BFSNode] = n.parent match {
        case null => List(n)
        case p => n :: path(p)
      }

      path(this)
    }

    override lazy val toString = "<" + depth + ", " + node.toString + ">"
    override lazy val hashCode = toString.hashCode
  }

  def search( limit: Int = -1 ) : Option[BFSNode]
  def expandedNodes : Seq[BFSNode]
  def nodesToExpand : Seq[BFSNode]
  def allNodes = expandedNodes ++ nodesToExpand
}

object BFS extends LazyLogging{

  type expandFunction[T] = (T) => Seq[T]
  type equalFunction[T] = (T,T) => Boolean
  type finalFunction[T] = (T) => Boolean
  type heuristicFunction[T] = (T) => Long


  trait BFSDefinition[T]{
    def expand( t:T ) : Seq[T]
    def equal(a:T,b:T) = a==b
    def found( t:T ) : Boolean
    def heuristic(t:T) = 0L
  }


  private class BFSImpl[T : Ordering]( initial:T, expandF: expandFunction[T], compareF: equalFunction[T], foundF: finalFunction[T], heuristicF: heuristicFunction[T] ) extends BFS[T] {

    val nodeOrdering = Ordering.fromLessThan{ (n1: BFSNode, n2: BFSNode) =>

      val v1 = n1.depth + heuristicF(n1.node)
      val v2 = n2.depth + heuristicF(n2.node)
      val ret = v1 - v2
      if( ret < 0 ){
        true
      }
      else if( ret > 0 ){
        false
      }
      else{
        implicitly[Ordering[T]].lt( n1.node,  n2.node )
      }
    }

    val _nodesToExpand = new collection.mutable.TreeSet()(nodeOrdering)
    val _expandedNodes = collection.mutable.Set[BFSNode]()
    val _allNodes = collection.mutable.Map[T, BFSNode]()

    _nodesToExpand += getOrCreateNode(0, null)(initial)


    override def expandedNodes = _expandedNodes.toSeq
    override def nodesToExpand = _nodesToExpand.toSeq

    class BFSNodeImpl(val node: T, val depth: Long, val parent: BFSNode) extends BFSNode {

      lazy val children = computeChildren

      lazy val rawChildren = expandF(node)

      def computeChildren: Seq[BFSNode] = {
        rawChildren.
          filter(!compareF(_, node)).
          map(getOrCreateNode(depth + 1, this))
      }

      override def equals(o: Any): Boolean = {
        toString == String.valueOf(o)
      }
    }


    def getOrCreateNode(depth: Long, parent: BFSNode)(n: T): BFSNode = {
      val ret = _allNodes.getOrElseUpdate(n, new BFSNodeImpl(n, depth, parent))
      if (!_expandedNodes.contains(ret)) {
        _nodesToExpand += ret
      }
      ret
    }

    def nextNodeToExpand: Option[BFSNode] = {

      val ret = {
        if (_nodesToExpand.size > 0) {
          Some(_nodesToExpand.firstKey)
        }
        else {
          None
        }
      }

      logger.error("nextNodeToExpand:" + ret)

      ret
    }

    def expandNode(n: BFSNode): Option[BFSNode] = {

      logger.error( "expandNode " + n + ": " + n.children.mkString(","))

      _nodesToExpand ++= n.children.filterNot( _expandedNodes.contains(_) )
      _nodesToExpand -= n
      _expandedNodes += n

      logger.error( "expandNode: nodesToExpand:" + _nodesToExpand.mkString(","))

      n.children.map(_.node).find(foundF).map(_allNodes)
    }

    def search(limit: Int): Option[BFSNode] = {
      if (limit == 0)
        None
      else nextNodeToExpand match {
        case Some(next) =>

          expandNode(next) match {
            case Some(n) =>
              Some(n)

            case None =>
              search(limit - 1)
          }

        case None =>
          None
      }
    }

  }


  def apply[T : Ordering]( node: T, expandF: expandFunction[T],
                         compareF: equalFunction[T],
                         foundF: finalFunction[T],
                         lowEstimateToFinal: heuristicFunction[T] ) : BFS[T] = {

    logger.error( "Creando una búsqueda" )
    new BFSImpl[T](node,expandF,compareF,foundF,lowEstimateToFinal)
  }

  def apply[T : Ordering]( node: T, d: BFSDefinition[T] ) : BFS[T] = apply( node, d.expand _, d.equal _, d.found _ , d.heuristic _ )


}

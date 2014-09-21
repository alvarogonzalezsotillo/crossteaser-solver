package dideco

import com.typesafe.scalalogging.slf4j.LazyLogging


/**
 * Created by alvaro on 31/08/2014.
 */



trait BFS[T]{
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
  }

  def search( limit: Int = -1 ) : Option[BFSNode]
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

    val nodesToExpand = new collection.mutable.TreeSet()(nodeOrdering)
    val nodesExpanded = collection.mutable.Set[BFSNode]()
    val allNodes = collection.mutable.Map[T, BFSNode]()

    nodesToExpand += getOrCreateNode(0, null)(initial)


    class BFSNodeImpl(val node: T, val depth: Long, val parent: BFSNode) extends BFSNode {

      lazy val children = computeChildren

      lazy val rawChildren = expandF(node)

      def computeChildren: Seq[BFSNode] = {
        rawChildren.
          filter(!compareF(_, node)).
          map(getOrCreateNode(depth + 1, this))
      }

      override def equals(o: Any): Boolean = o match {
        case bn: BFSNode => bn.node == node
        case _ => false
      }
    }


    def getOrCreateNode(depth: Long, parent: BFSNode)(n: T): BFSNode = {
      val ret = allNodes.getOrElseUpdate(n, new BFSNodeImpl(n, depth, parent))
      if (!nodesExpanded.contains(ret)) {
        nodesToExpand += ret
      }
      ret
    }

    def nextNodeToExpand: Option[BFSNode] = {

      val ret = {
        if (nodesToExpand.size > 0) {
          Some(nodesToExpand.firstKey)
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

      nodesToExpand ++= n.children
      nodesToExpand -= n
      nodesExpanded += n

      logger.error( "expandNode: nodesToExpand:" + nodesToExpand.mkString(","))

      n.children.map(_.node).find(foundF).map(allNodes)
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

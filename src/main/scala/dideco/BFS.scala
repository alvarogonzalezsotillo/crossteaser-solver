package dideco

/**
 * Created by alvaro on 31/08/2014.
 */




object BFS {

  type expandFunction[T] = (T) => Seq[T]
  type equalFunction[T] = (T,T) => Boolean
  type finalFunction[T] = (T) => Boolean
  type heuristicFunction[T] = (T) => Long


  class BFSImpl[T]( initial:T, expandF: expandFunction[T], compareF: equalFunction[T], foundF: finalFunction[T], heuristicF: heuristicFunction[T] ){

    trait BFSNode{
      def node : T
      def depth : Long
      def parent : BFSNode
      def children : Seq[BFSNode]
    }

    val nodeOrdering = Ordering.by{n : BFSNode => n.depth + heuristicF(n.node)}

    val nodesToExpand = new collection.mutable.TreeSet()(nodeOrdering)
    val nodesExpanded = collection.mutable.Set[BFSNode]()
    val allNodes = collection.mutable.Map[T,BFSNode]()

    nodesToExpand += getOrCreateNode(0,null)(initial)


    class BFSNodeImpl( val node: T, val depth: Long, val parent: BFSNode ) extends BFSNode{

      lazy val children = computeChildren

      lazy val rawChildren = expandF(node)

      def computeChildren : Seq[BFSNode] = {
        rawChildren.
          filter( !compareF(_,node) ).
          map( getOrCreateNode(depth+1,this) )
      }

      override def equals( o: Any ) : Boolean = o match{
        case bn:BFSNode => bn.node == node
        case _ => false
      }
    }


    def getOrCreateNode( depth: Long, parent: BFSNode)( n: T ) : BFSNode = {
      val ret = allNodes.getOrElseUpdate(n, new BFSNodeImpl(n, depth, parent))
      if (!nodesExpanded.contains(ret)){
        nodesToExpand += ret
      }
      ret
    }

    def nextNodeToExpand : Option[BFSNode] = {
      if( nodesToExpand.size > 0 ){
        Some(nodesToExpand.firstKey)
      }
      else{
        None
      }
    }

    def expandNode( n: BFSNode ) : Option[BFSNode] = {
      nodesToExpand -= n
      nodesExpanded += n

      n.children.map( _.node).find(foundF).map( allNodes )
    }

    def search : Option[BFSNode] = nextNodeToExpand match{
      case Some(next) =>

        expandNode(next) match {
          case Some(n) =>
            Some(n)

          case None =>
            search
        }

      case None =>
          None
    }
  }


  def apply[T]( node: T, expandF: expandFunction[T],
                         compareF: equalFunction[T],
                         foundF: finalFunction[T],
                         lowEstimateToFinal: heuristicFunction[T] ) = {
    new BFSImpl[T](node,expandF,compareF,foundF,lowEstimateToFinal)
  }


}

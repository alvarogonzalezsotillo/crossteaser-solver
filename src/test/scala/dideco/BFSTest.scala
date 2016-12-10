package dideco


import dideco.BFS.BFSDefinition
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BFSTest extends FlatSpec {

  def compareF[T](a: T, b: T) = a == b

  def notFoundF[T](n: T) = false

  def noEstimate[T](n: T) = 0L

  def bfsDefinition[T](expandF: (T) => Seq[T], equalF: (T, T) => Boolean, foundF: (T) => Boolean, heuristicF: (T) => Long, ord: Ordering[T]) = {
    new BFSDefinition[T]{
      override def equal(a: T, b: T): Boolean = equalF(a,b)

      override def expand(t: T) = expandF(t)

      override def found(t: T) = foundF(t)

      override def heuristic(t: T) = heuristicF(t)

      override val ordering = ord

      override def hashable(t: T) = t.toString
    }

  }

  "A node with no children" should "have an empty search tree" in {

    val node = 1
    val expandF = (_: Int) => Seq[Int]()

    val definition = bfsDefinition(expandF, compareF[Int] _, notFoundF[Int] _, noEstimate[Int] _, implicitly[Ordering[Int]])

    val bfs = BFS[Int](node, definition )

    assert(bfs.search().isEmpty)
  }

  "From 100 to 1" should "be 100 nodes" in {
    val node = 100
    val expandF = (i: Int) => Seq(i - 1)
    val foundF = (i: Int) => i == 1

    val definition = bfsDefinition( expandF, compareF[Int] _ , foundF, noEstimate[Int] _, implicitly[Ordering[Int]])

    val bfs = BFS(node, definition)

    val res = bfs.search()
    assert(res.isDefined)
    assert(res.get.node == 1)
    assert(res.get.pathToRoot.size == 100)
  }

  "abab" should "be generated in five levels" in{
    val node = ""
    val definition = new BFSDefinition[String]{
      def expand( t: String ) = Seq( t + "a", t + "b" )
      def found( t:String ) = t == "abab"

      override val ordering: Ordering[String] = implicitly[Ordering[String]]

      override def hashable(t: String) = t
    }

    val bfs = BFS( node, definition )
    val res = bfs.search()
    assert( res.isDefined )
    assert( res.get.node == "abab" )
    assert( res.get.pathToRoot.size == 5 )
    assert( res.get.pathToRoot.map( _.node ) == Seq( "abab", "aba", "ab", "a", "" ) )
  }

  "abab" should "be generated without duplicated nodes" in{
    val node = ""
    val definition = new BFSDefinition[String]{
      def expand( t: String ) = if( t.size > 0 )
        Seq( t.slice(0,t.size-2), t + "a", t + "b" )
      else
        Seq( t + "a", t + "b" )
      def found( t:String ) = t == "abab"

      override def ordering: Ordering[String] = implicitly[Ordering[String]]

      override def hashable(t: String) = t
    }

    val bfs = BFS( node, definition )
    val res = bfs.search()
    assert( res.isDefined )

    val expandedNodes = bfs.expandedNodes
    val allNodes = bfs.allNodes
    assert( expandedNodes.flatMap( _.children ).forall( child => allNodes.exists( child eq _ ) ) )
  }


}

package dideco


import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BFSTest extends FlatSpec {

  def compareF[T](a:T,b:T) = a == b
  def notFoundF[T](n:T) = false
  def noEstimate[T](n:T) = 0L

  "A node with no children" should "have an empty search tree" in {

    val node = 1
    val expandF = (_:Int) => Seq[Int]()

    val bfs = BFS[Int](node, expandF, compareF[Int], notFoundF[Int], noEstimate[Int])

    assert(bfs.search.isEmpty)
    assert(bfs.allNodes.size == 1)
    assert(bfs.nodesToExpand.size == 0)
    assert(bfs.nodesExpanded.size == 1)
  }

}

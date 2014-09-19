package dideco


import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BFSTest extends FlatSpec {

  def compareF[T](a: T, b: T) = a == b

  def notFoundF[T](n: T) = false

  def noEstimate[T](n: T) = 0L

  "A node with no children" should "have an empty search tree" in {

    val node = 1
    val expandF = (_: Int) => Seq[Int]()

    val bfs = BFS[Int](node, expandF, compareF[Int] _, notFoundF[Int] _, noEstimate[Int] _)

    assert(bfs.search.isEmpty)
  }

  "From 100 to 1" should "be 100 nodes" in {
    val node = 100
    val expandF = (i: Int) => Seq(i - 1)
    val foundF = (i: Int) => i == 1

    val bfs = BFS[Int](node, expandF, compareF[Int] _ , foundF, noEstimate[Int] _)

    val res = bfs.search
    assert(res.isDefined)
    assert(res.get.node == 1)
    assert(res.get.pathToRoot.size == 100)
  }

}

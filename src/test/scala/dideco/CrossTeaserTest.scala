package dideco

import com.typesafe.scalalogging.slf4j.LazyLogging
import dideco.OrientableColor.Color
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

/**
 * Created by alvaro on 29/12/14.
 */
@RunWith(classOf[JUnitRunner])
class CrossTeaserTest extends FlatSpec with LazyLogging{

  private def repeat( times: Int = 2 )( proc : => Unit ): Unit ={
    for( t <- 0 until times ){
      proc
    }
  }

  "A 20 steps-scrambled CrossTeaser" should "be topcolor-resolved in 20 steps or less" in{
    val p = ("Y","O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    repeat() {
      val scrambled = Board.scrambled(board, 20)

      val bfs = CrossTeaser.solveTopColor(scrambled)
      val found = bfs.search()

      assert(found.isDefined)
      logger.error(""+found.get.pathToRoot.head)
      assert(found.get.pathToRoot.size <= 21, s"Couldn't be resolved:$scrambled" )
      assert(found.get.pathToRoot.last.node == scrambled)
    }
  }

  "A 20 steps-scrambled CrossTeaser" should "have no duplicated nodes" in{
    val p = ("Y","O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    repeat() {
      val scrambled = Board.scrambled(board, 20)

      val bfs = CrossTeaser.solveTopColor(scrambled)
      val found = bfs.search()

      val expandedNodes = bfs.expandedNodes
      val allNodes = bfs.allNodes
      assert( expandedNodes.flatMap( _.children ).forall( child => allNodes.exists( child eq _ ) ) )
    }
  }




  "A solved CrossTeaser" should "have 0 stepsToTop heuristic" in{
    val p = ("Y","O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    assert( CrossTeaser.stepsToTop( board, Color("Y").get) == 0 )
  }

  "A 40 steps-scrambled CrossTeaser" should "be topcolor-colordefined-resolved in 20 steps or less" in{
    val p = ("Y","O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    repeat() {
      val scrambled = Board.scrambled(board, 40)


      logger.error( s"Solving:$scrambled")
      val bfs = CrossTeaser.solveTopColor(scrambled, Color("Y").get )
      val found = bfs.search()

      assert(found.isDefined)
      logger.error(""+found.get.pathToRoot.head)
      assert(found.get.pathToRoot.size <= 40, s"Couldn't be resolved:$scrambled" )
      assert(found.get.pathToRoot.last.node == scrambled)

    }
  }


  "stepsToTop heuristic" should "yield better results than no heuristic" in{
    val p = ("Y","O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )
    val topColor = Color("Y").get

    repeat() {
      val scrambled = Board.scrambled(board, 40)

      logger.error( s"Solving:$scrambled")

      val bfsH = CrossTeaser.solveTopColor(scrambled, topColor, CrossTeaser.stepsToTop(_, topColor) )
      val foundH = bfsH.search()
      logger.error("foundH"+foundH.get.pathToRoot.head)
      logger.error( s"bfsH:$bfsH")

      val bfsNoH = CrossTeaser.solveTopColor(scrambled, topColor, _ => 0 )
      val foundNoH = bfsNoH.search()
      logger.error("foundNoH:"+foundNoH.get.pathToRoot.head)
      logger.error( s"bfsNoH:$bfsNoH")

      assert(foundNoH.isDefined)
      assert(foundH.isDefined)
      assert( foundNoH.get.pathToRoot.size >= foundH.get.pathToRoot.size )
      assert( bfsNoH.currentInfo.numberOfExpandedNodes >= bfsH.currentInfo.numberOfExpandedNodes )
    }
  }


  "A 100 steps-scrambled CrossTeaser" should "be topcolor-colordefined-stepstotop--resolved in 100 steps or less" in{
    val p = ("Y","O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    repeat() {
      val scrambled = Board.scrambled(board, 100)


      logger.error( s"Solving:$scrambled")
      val bfs = CrossTeaser.solveTopColor(scrambled, Color("Y").get, CrossTeaser.stepsToTop(_, Color("Y").get) )
      val found = bfs.search()

      assert(found.isDefined)
      logger.error(""+found.get.pathToRoot.head)
      assert(found.get.pathToRoot.size <= 101, s"Couldn't be resolved:$scrambled" )
      assert(found.get.pathToRoot.last.node == scrambled)

    }
  }

  "A 40 steps-scrambled CrossTeaser" should "be perfectly-colordefined-resolved in 40 steps or less" in{
    val p = ("Y","O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    repeat() {
      val steps = 100
      val scrambled = Board.scrambled(board, steps)


      logger.error( s"Solving:$scrambled")
      val bfs = CrossTeaser.solvePerfectly(scrambled, Color("Y").get, Color("O").get )
      val found = bfs.search()

      assert(found.isDefined)
      logger.error(""+found.get.pathToRoot.head)
      assert(found.get.pathToRoot.size <= steps+1, s"Couldn't be resolved:$scrambled" )
      assert(found.get.pathToRoot.last.node == scrambled)

    }
  }

}

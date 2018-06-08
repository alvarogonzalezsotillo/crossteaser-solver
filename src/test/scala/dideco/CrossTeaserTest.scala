package dideco

import com.typesafe.scalalogging.slf4j.LazyLogging
import dideco.OrientableColor.Color
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

import scala.util.Random

/**
  * Created by alvaro on 29/12/14.
  */
@RunWith(classOf[JUnitRunner])
class CrossTeaserTest extends FlatSpec with LazyLogging {

  val msLimit = 100000

  "A 20 steps-scrambled CrossTeaser" should "be topcolor-resolved in 20 steps or less" in {
    val p = ("Y", "O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    val scrambled = Board.scrambled(board, 20)(createRandom())

    val bfs = CrossTeaser.solveTopColor(scrambled)
    val found = bfs.search(msLimit)

    assert(found.isDefined)
    logger.error("" + found.get.pathToRoot.head)
    assert(found.get.pathToRoot.size <= 21, s"Couldn't be resolved:$scrambled")
    assert(found.get.pathToRoot.last.node == scrambled)
  }

  "A 20 steps-scrambled CrossTeaser" should "have no duplicated nodes" in {
    val p = ("Y", "O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    val scrambled = Board.scrambled(board, 20)(createRandom())

    val bfs = CrossTeaser.solveTopColor(scrambled)
    val found = bfs.search(msLimit)

    val expandedNodes = bfs.expandedNodes
    val allNodes = bfs.allNodes

    logger.error(s"expanddedNodes:${expandedNodes.size}  allnodes:${allNodes.size}")

    //assert( expandedNodes.size == allNodes.size )
    assert(expandedNodes.flatMap(_.children).forall(child => allNodes.exists(child eq _)))
  }


  "A solved CrossTeaser" should "have 0 stepsToTop heuristic" in {
    val p = ("Y", "O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    assert(CrossTeaser.stepsToTop(board, Color("Y").get) == 0)
  }

  "A 40 steps-scrambled CrossTeaser" should "be topcolor-colordefined-resolved in 20 steps or less" in {
    val p = ("Y", "O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    val scrambled = Board.scrambled(board, 40)(createRandom())


    logger.error(s"Solving:$scrambled")
    val bfs = CrossTeaser.solveTopColor(scrambled, Color("Y").get)
    val found = bfs.search(msLimit)

    assert(found.isDefined)
    logger.error("" + found.get.pathToRoot.head)
    assert(found.get.pathToRoot.size <= 40, s"Couldn't be resolved:$scrambled")
    assert(found.get.pathToRoot.last.node == scrambled)

  }


  "stepsToTop heuristic" should "yield better results than no heuristic" in {
    val p = ("Y", "O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )
    val topColor = Color("Y").get

    val scrambled = Board.scrambled(board, 20)(createRandom())

    logger.error(s"Solving:$scrambled")

    val bfsH = CrossTeaser.solveTopColor(scrambled, topColor)
    val foundH = bfsH.search(msLimit)
    logger.error("foundH" + foundH.get.pathToRoot.head)
    logger.error(s"bfsH:$bfsH")

    val bfsNoH = CrossTeaser.solveTopColor(scrambled, topColor, _ => 0)
    val foundNoH = bfsNoH.search(msLimit)
    logger.error("foundNoH:" + foundNoH.get.pathToRoot.head)
    logger.error(s"bfsNoH:$bfsNoH")

    assert(foundNoH.isDefined)
    assert(foundH.isDefined)
    assert(foundNoH.get.pathToRoot.size >= foundH.get.pathToRoot.size)
    assert(bfsNoH.expandedNodes.size >= bfsH.expandedNodes.size)

  }


  "A 100 steps-scrambled CrossTeaser" should "be topcolor-colordefined-stepstotop--resolved in 100 steps or less" in {
    val p = ("Y", "O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    val scrambled = Board.scrambled(board, 100)(createRandom())


    logger.error(s"Solving:$scrambled")
    val bfs = CrossTeaser.solveTopColor(scrambled, Color("Y").get, CrossTeaser.stepsToTop(_, Color("Y").get))
    val found = bfs.search(msLimit)


    assert(found.isDefined)
    logger.error("" + found.get.pathToRoot.head)
    assert(found.get.pathToRoot.size <= 101, s"Couldn't be resolved:$scrambled")
    assert(found.get.pathToRoot.last.node == scrambled)
  }

  val stepsForPerfectlyColorDefined = 100
  s"A $stepsForPerfectlyColorDefined steps-scrambled CrossTeaser" should s"be perfectly-colordefined-resolved in $stepsForPerfectlyColorDefined steps or less" in {
    val p = ("Y", "O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    val scrambled = Board.scrambled(board, stepsForPerfectlyColorDefined)(createRandom())

    logger.error(s"Solving:$scrambled")
    val bfs = CrossTeaser.solvePerfectly(scrambled, Color("Y").get, Color("O").get)
    val found = bfs.search(msLimit)

    assert(found.isDefined)
    logger.error("" + found.get.pathToRoot.head)
    assert(found.get.pathToRoot.size <= stepsForPerfectlyColorDefined + 1, s"Couldn't be resolved:$scrambled")
    assert(found.get.pathToRoot.last.node == scrambled)

  }


  val stepsForPerfectlyColorDefined_3x2 = 10000
  s"A $stepsForPerfectlyColorDefined_3x2 steps-scrambled CrossTeaser" should s"be perfectly-colordefined-resolved in $stepsForPerfectlyColorDefined_3x2 steps or less" in {
    val p = ("Y", "O")
    val board = CrossTeaser(3, 2,
      p, p, p,
      p, null, p
    )


    val scrambled = Board.scrambled(board, stepsForPerfectlyColorDefined_3x2)(createRandom())

    logger.error(s"Solving:$scrambled")
    val bfs = CrossTeaser.solvePerfectly(scrambled, Color("Y").get, Color("O").get)
    val found = bfs.search(msLimit)

    assert(found.isDefined)
    logger.error("" + found.get.pathToRoot.head)
    assert(found.get.pathToRoot.size <= stepsForPerfectlyColorDefined_3x2 + 1, s"Couldn't be resolved:$scrambled")
    assert(found.get.pathToRoot.last.node == scrambled)


  }

}

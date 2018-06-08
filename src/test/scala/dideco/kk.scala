package dideco

import dideco.OrientableColor.Color
import com.typesafe.scalalogging.slf4j.LazyLogging
import dideco.OrientableColor.Color
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

/**
  * Created by alvaro on 3/12/16.
  */
@RunWith(classOf[JUnitRunner])
class kk extends FlatSpec with LazyLogging {

  val stepsForPerfectlyColorDefined_3x2 = 100

  val msLimit = 100000

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
    val nodes = found.get.pathToRoot
    println(nodes.mkString("\n"))

    val boards = nodes.map(_.node)
    BoardToJS(boards, System.out)
  }

}

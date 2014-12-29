package dideco

import com.typesafe.scalalogging.slf4j.LazyLogging
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

/**
 * Created by alvaro on 29/12/14.
 */
@RunWith(classOf[JUnitRunner])
class CrossTeaserTest extends FlatSpec with LazyLogging{

  private def repeat( times: Int )( proc : => Unit ): Unit ={
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

    repeat( 10 ) {
      val scrambled = Board.scrambled(board, 20)

      val bfs = CrossTeaser.solveTopColor(scrambled)
      val found = bfs.search()

      assert(found.isDefined)
      assert(found.get.pathToRoot.size <= 20, s"Couldn't be resolved:$scrambled" )
      assert(found.get.pathToRoot.last.node == scrambled)
      logger.error(found.get.pathToRoot.mkString("\n"))
    }
  }

  "A 2000 steps-scrambled CrossTeaser" should "be topcolor-resolved in 35 steps or less" in{
    val p = ("Y","O")
    val board = CrossTeaser(
      p, p, p,
      p, null, p,
      p, p, p
    )

    repeat( 100 ) {
      val scrambled = Board.scrambled(board, 2000)


      logger.error( s"Solving:$scrambled")
      val bfs = CrossTeaser.solveTopColor(scrambled)
      val found = bfs.search()

      assert(found.isDefined)
      assert(found.get.pathToRoot.size <= 25, s"Couldn't be resolved:$scrambled" )
      assert(found.get.pathToRoot.last.node == scrambled)
      logger.error(found.get.pathToRoot.head)
    }
  }

}

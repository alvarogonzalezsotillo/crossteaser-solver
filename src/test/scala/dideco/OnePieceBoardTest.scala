package dideco

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

/**
 * Created by alvaro on 21/09/2014.
 */
@RunWith(classOf[JUnitRunner])
class OnePieceBoardTest extends FlatSpec {

  "A 2x2 board with a single piece" should "have 2 neighbour boards" in{
    val piece = OrientableColor.originalOrientable

    val board = OnePieceBoard(2,2,piece,Location(0,0))
    assert( board.oneMovementBoards.size == 2 )
  }


  "A 3x3 board with a single piece at the corner" should "have 2 neighbour boards" in{
    val piece = OrientableColor.originalOrientable

    val board = OnePieceBoard(3,3,piece,Location(0,0))
    assert( board.oneMovementBoards.size == 2 )
  }


  "A 3x3 board with a single piece at the center" should "have 4 neighbour boards" in{
    val piece = OrientableColor.originalOrientable

    val board = OnePieceBoard(3,3,piece,Location(1,1))
    assert( board.oneMovementBoards.size == 4 )
  }

  "A 3x3 board with a single piece at the edge" should "have 3 neighbour boards" in{
    val piece = OrientableColor.originalOrientable

    val board = OnePieceBoard(3,3,piece,Location(1,0))
    assert( board.oneMovementBoards.size == 3 )
  }
}

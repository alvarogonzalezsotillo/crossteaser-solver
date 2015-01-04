package dideco

/**
 * Created by alvaro on 26/09/2014.
 */
import Orientation.Orientation
import Turn.Turn
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BoardTest extends FlatSpec {


  "The two implementations of one piece board" should "give the same positions in a 2x2 board with one piece" in{
    val piece = OrientableColor.originalOrientable

    val board1 = OnePieceBoard(2,2,piece,Location(0,0))
    val board2 = Board(2,2, IndexedSeq(piece,null,null,null) )

    val b1 = board1.oneMovementBoards.toSet
    val b2 = board2.oneMovementBoards.toSet

    assert( b1 == b2 )
  }
}

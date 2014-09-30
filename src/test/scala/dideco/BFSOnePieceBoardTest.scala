package dideco

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

/**
 * Created by alvaro on 21/09/2014.
 */
@RunWith(classOf[JUnitRunner])
class BFSOnePieceBoardTest extends FlatSpec {

  "A 2x2 board" should "have 12 posibilities" in{
    val piece = OrientablePiece.from( OrientablePiece.originalOrientable )
    val board = OnePieceBoard(2,2,piece,Location(0,0))

    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()

    assert( found.isEmpty )
    assert( bfs.allNodes.size == 12 )
    assert( bfs.expandedNodes.size == 12 )
  }


  "A 3x3 board" should "have a lot of posibilities" in{


    val piece = OrientablePiece.from( OrientablePiece.originalOrientable )
    val board = OnePieceBoard(3,3,piece,Location(0,0))

    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()


    assert( found.isEmpty )
    assert( bfs.allNodes.size == 108 )
  }

}
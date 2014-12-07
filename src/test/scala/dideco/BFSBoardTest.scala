package dideco

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

import scala.annotation.tailrec

/**
 * Created by alvaro on 21/09/2014.
 */
@RunWith(classOf[JUnitRunner])
class BFSBoardTest extends FlatSpec {



  "A 2x2 board" should "have 12 posibilities" in {
    val piece = OrientablePiece.from(OrientablePiece.originalOrientable)
    val board = Board(2, 2, Seq(piece, null, null, null))

    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()

    assert(found.isEmpty)
    assert(bfs.allNodes.size == 12)
    assert(bfs.expandedNodes.size == 12)
  }

  "A 3x3 board" should "have 108 posibilities starting at a corner" in {
    val piece = OrientablePiece.from(OrientablePiece.originalOrientable)
    val board = Board(3, 3, Seq(piece, null, null, null, null, null, null, null, null))

    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()

    assert(found.isEmpty)
    assert(bfs.allNodes.size == 108)
    assert(bfs.expandedNodes.size == 108)
  }

  "A 3x3 board" should "have 108 posibilities starting at an edge" in {
    val piece = OrientablePiece.from(OrientablePiece.originalOrientable)
    val board = Board(3, 3, Seq(null, piece, null, null, null, null, null, null, null))

    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()

    assert(found.isEmpty)
    assert(bfs.allNodes.size == 108)
    assert(bfs.expandedNodes.size == 108)
  }


  "A 2x2 board" should "have same posibilities as a one piece board" in {
    val piece = OrientablePiece.from(OrientablePiece.originalOrientable)
    val board = Board(2, 2, Seq(piece, null, null, null))
    val opboard = OnePieceBoard(2, 2, piece, Location(0, 0))

    val bfs = Board.exploreAllMovements(board)
    val opbfs = Board.exploreAllMovements(opboard)
    val found = bfs.search()
    val opfound = opbfs.search()


    assert(found.isEmpty == opfound.isEmpty)
    assert(bfs.allNodes.toSet == opbfs.allNodes.toSet)
    assert(bfs.expandedNodes.toSet == opbfs.expandedNodes.toSet)
  }

  "A 3x3 board" should "have same posibilities as a one piece board" in {
    val piece = OrientablePiece.from(OrientablePiece.originalOrientable)
    val board = Board(3, 3, Seq(piece, null, null, null, null, null, null, null, null))
    val opboard = OnePieceBoard(3, 3, piece, Location(0, 0))

    val bfs = Board.exploreAllMovements(board)
    val opbfs = Board.exploreAllMovements(opboard)
    val found = bfs.search()
    val opfound = opbfs.search()


    assert(found.isEmpty == opfound.isEmpty)
    assert(bfs.allNodes.toSet == opbfs.allNodes.toSet)
    assert(bfs.expandedNodes.toSet == opbfs.expandedNodes.toSet)
  }

  "A 3x3 board" should "have same posibilities as a one piece board starting at a edge " in {
    val piece = OrientablePiece.from(OrientablePiece.originalOrientable)
    val board = Board(3, 3, Seq(null, piece, null, null, null, null, null, null, null))
    val opboard = OnePieceBoard(3, 3, piece, Location(1, 0))

    val bfs = Board.exploreAllMovements(board)
    val opbfs = Board.exploreAllMovements(opboard)
    val found = bfs.search()
    val opfound = opbfs.search()


    assert(found.isEmpty == opfound.isEmpty)
    assert(bfs.allNodes.toSet == opbfs.allNodes.toSet)
    assert(bfs.expandedNodes.toSet == opbfs.expandedNodes.toSet)
  }


  "A 3x3 nine piece board" should "have one posibility" in {
    val piece = OrientablePiece.from(OrientablePiece.originalOrientable)
    val board = Board(3, 3, Seq(piece, piece, piece, piece, piece, piece, piece, piece, piece))
    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()

    assert(found.isEmpty == found.isEmpty)
    assert(bfs.allNodes.size == 1)

  }


}

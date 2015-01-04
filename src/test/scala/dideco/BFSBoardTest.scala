package dideco

import dideco.OrientableColor.Color.Color
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
    val piece = OrientableColor.originalOrientable
    val board = Board(2, 2, IndexedSeq(piece, null, null, null))

    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()

    assert(found.isEmpty)
    assert(bfs.allNodes.size == 12)
    assert(bfs.expandedNodes.size == 12)
  }

  "A 3x3 board" should "have 108 posibilities starting at a corner" in {
    val piece = OrientableColor.originalOrientable
    val board = Board(3, 3, IndexedSeq(piece, null, null, null, null, null, null, null, null))

    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()

    assert(found.isEmpty)
    assert(bfs.allNodes.size == 108)
    assert(bfs.expandedNodes.size == 108)
  }

  "A 3x3 board" should "have 108 posibilities starting at an edge" in {
    val piece = OrientableColor.originalOrientable
    val board = Board(3, 3, IndexedSeq(null, piece, null, null, null, null, null, null, null))

    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()

    assert(found.isEmpty)
    assert(bfs.allNodes.size == 108)
    assert(bfs.expandedNodes.size == 108)
  }


  "A 2x2 board" should "have same posibilities as a one piece board" in {
    val piece = OrientableColor.originalOrientable
    val board = Board(2, 2, IndexedSeq(piece, null, null, null))
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
    val piece = OrientableColor.originalOrientable
    val board = Board(3, 3, IndexedSeq(piece, null, null, null, null, null, null, null, null))
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
    val piece = OrientableColor.originalOrientable
    val board = Board(3, 3, IndexedSeq(null, piece, null, null, null, null, null, null, null))
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
    val piece = OrientableColor.originalOrientable
    val board = Board(3, 3, IndexedSeq(piece, piece, piece, piece, piece, piece, piece, piece, piece))
    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()

    assert(found.isEmpty == true)
    assert(bfs.allNodes.size == 1)
  }


  "A 3x3 one piece board" should "be resolved in 0 movements" in{
    val piece = OrientableColor.from( "G", "R" ).head
    val board = Board(3,3, IndexedSeq(piece,null,null,null,null,null,null,null,null))
    val goalB = board

    val bfs = Board.explorePathTo(board,goalB)
    val found = bfs.search()

    assert( found.isDefined == true )
    assert( found.get.node == goalB )
    assert( found.get.pathToRoot.size == 1 )
  }

  "A 3x3 one piece board" should "be resolved in 1 movement" in{
    val piece = OrientableColor.from( "G", "R" ).head
    val board = Board(3,3, IndexedSeq(piece,null,null,null,null,null,null,null,null))

    val goalP = OrientableColor.from( "Y", "R").head
    val goalB = Board(3,3, IndexedSeq(null,goalP,null,null,null,null,null,null,null))

    val bfs = Board.explorePathTo(board,goalB)
    val found = bfs.search()

    assert( found.isDefined == true )
    assert( found.get.node == goalB )
    assert( found.get.pathToRoot.size == 2 )
  }

  "A 3x3 one piece board" should "be resolved in 2 movement" in{
    val piece = OrientableColor.from( "B", "Y" ).head
    val board = Board(3,3, IndexedSeq(piece,null,null,null,null,null,null,null,null))

    val goalP = OrientableColor.from( "R", "P").head
    val goalB = Board(3,3, IndexedSeq(null,null,null,null,null,null,goalP,null,null))

    val bfs = Board.explorePathTo(board,goalB)
    val found = bfs.search()

    assert( found.isDefined == true )
    assert( found.get.node == goalB )
    assert( found.get.pathToRoot.size == 3 )
  }


  "A 3x3 one piece board" should "be resolved in 3 movement" in{
    val piece = OrientableColor.from( "B", "Y" ).head
    val board = Board(3,3, IndexedSeq(piece,null,null,null,null,null,null,null,null))

    val goalP = OrientableColor.from( "O", "P").head
    val goalB = Board(3,3, IndexedSeq(null,null,null,null,null,null,null,goalP,null))

    val bfs = Board.explorePathTo(board,goalB)
    val found = bfs.search()

    assert( found.isDefined == true )
    assert( found.get.node == goalB )
    assert( found.get.pathToRoot.size == 4 )

    assert( bfs.allNodes.map(_.pathToRoot.size).forall( _ <= 4 ) )
  }

  "A 3x3 one piece board" should "be resolved in 4 movements" in{
    val piece = OrientableColor.from( "B", "Y" ).head
    val board = Board(3,3, IndexedSeq(piece,null,null,null,null,null,null,null,null))

    val goalP = OrientableColor.from( "B", "P").head
    val goalB = Board(3,3, IndexedSeq(null,null,null,null,null,null,null,null,goalP))

    val bfs = Board.explorePathTo(board,goalB)
    val found = bfs.search()

    assert( found.isDefined == true )
    assert( found.get.node == goalB )
    assert( found.get.pathToRoot.size == 5 )

    assert( bfs.allNodes.map(_.pathToRoot.size).forall( _ <= 5 ) )
  }

}

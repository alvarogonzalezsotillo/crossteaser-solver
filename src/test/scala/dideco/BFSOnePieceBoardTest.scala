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
    val piece = OrientableColor.originalOrientable
    val board = OnePieceBoard(2,2,piece,Location(0,0))

    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()

    assert( found.isEmpty )
    assert( bfs.allNodes.size == 12 )
    assert( bfs.expandedNodes.size == 12 )
  }


  "A 3x3 board" should "have 108 posibilities" in{


    val piece = OrientableColor.originalOrientable
    val board = OnePieceBoard(3,3,piece,Location(0,0))

    val bfs = Board.exploreAllMovements(board)
    val found = bfs.search()


    assert( found.isEmpty )
    assert( bfs.allNodes.size == 108 )
  }

  "A 3x3 board" should "have same number of possibilities if starting from corner or edge" in{


    val piece = OrientableColor.originalOrientable
    val cornerBoard = OnePieceBoard(3,3,piece,Location(0,0))
    val edgeBoard = OnePieceBoard(3,3,piece.turn(Turn.toEast),Location(1,0))

    val cornerBfs = Board.exploreAllMovements(cornerBoard)
    val edgeBfs = Board.exploreAllMovements(edgeBoard)
    cornerBfs.search()
    edgeBfs.search()

    println( cornerBfs.allNodes.map(_.node.toString).sorted.zip(edgeBfs.allNodes.map(_.node.toString).sorted).mkString( "\n" ) )

    if( true ){
      val s1 = cornerBfs.allNodes.toSet
      val s2 = edgeBfs.allNodes.toSet

      println( s1.diff(s2).mkString(" -- " ) )
      println( s2.diff(s1).mkString(" -- " ) )
    }

    assert( cornerBfs.allNodes.size == edgeBfs.allNodes.size )
    assert( cornerBfs.allNodes.toSet.size == edgeBfs.allNodes.toSet.size )
    assert( cornerBfs.allNodes.map(_.node).toSet == edgeBfs.allNodes.map(_.node).toSet )
  }


}
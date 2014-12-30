package dideco

import dideco.BFS.heuristicFunction
import dideco.OrientablePiece.Color.Color
import dideco.Orientation.Orientation
import dideco.Orientation._

/**
 * Created by alvaro on 29/12/14.
 */
object CrossTeaser{


  import Board.BFSBoardDefinition

  type CrossTeaser = Board[OrientablePiece]

  implicit val boardOrdering = Ordering.by((b: CrossTeaser) => b.toString)

  def apply( pieces : (String,String)* ) = {
    def P(sides: (String,String) ) = if( sides == null ) null else OrientablePiece.from(sides._1,sides._2).head
    assert( pieces.size == 9 )
    Board( 3, 3, pieces.toIndexedSeq.map( P ) )
  }

  def topsAreEqual( t: CrossTeaser, c: Color = null ) = {
    val pieces = t.allPieces.filter(_ != null)
    val topSides = pieces.map( _.orientable.get(Orientation.top) )
    val color = if ( c == null ) topSides.head else c
    topSides.forall( _ == color )
  }



  def northsAreEqual( t: CrossTeaser, c : Color = null  ) = {
    val pieces = t.allPieces.filter(_ != null)
    val northSides = pieces.map( _.orientable.get(Orientation.north) )
    val color = if ( c == null ) northSides.head else c
    northSides.forall( _ == color )
  }

  def emptyInCenter( t: Board[_] ) = {
    val r = t.rows/2
    val c = t.columns/2
    t.pieceAt(c,r) == null
  }

  def maxNumberOfEqualTops( t: CrossTeaser ) = {
    val pieces = t.allPieces.filter(_ != null)
    val topSides = pieces.map( _.orientable.get(Orientation.top) )
    topSides.groupBy(c=>c).map{ case(key,values) => values.size }.max
  }

  def stepsToTop( t: CrossTeaser, color: Color ) = {

    def stepsToTop( o: Orientation ) = o match{
      case Orientation.top => 0
      case Orientation.bottom => 2
      case Orientation.north => 1
      case Orientation.west => 1
      case Orientation.east => 1
      case Orientation.south => 1
      case _ => throw new IllegalStateException();
    }

    val pieces = t.allPieces.filter(_ != null)
    pieces.map( p => stepsToTop( p.orientable.where(color) ) ).sum
  }

  def solvePerfectly(board: CrossTeaser) = {

    val bfsDef = new BFSBoardDefinition[OrientablePiece] {
      override def found(t: CrossTeaser) = {
        topsAreEqual(t) && northsAreEqual(t)
      }

      override def heuristic(t:CrossTeaser ) = {
        8 - maxNumberOfEqualTops(t)
      }
    }

    BFS(board, bfsDef)
  }

  def solvePerfectly(board: CrossTeaser, topColor: Color, northColor: Color ) = {

    val bfsDef = new BFSBoardDefinition[OrientablePiece] {
      override def found(t: CrossTeaser) = {
        topsAreEqual(t,topColor) && northsAreEqual(t,northColor)
      }

      override def heuristic(t:CrossTeaser ) = stepsToTop(t,topColor)
    }

    BFS(board, bfsDef)
  }


  def solveTopColor(board: CrossTeaser) = {
    val bfsDef = new BFSBoardDefinition[OrientablePiece] {
      override def found(t: CrossTeaser) = {
        topsAreEqual(t) && emptyInCenter(t)
      }

      override def heuristic(t:CrossTeaser ) = {
        8 - maxNumberOfEqualTops(t)
      }

    }

    BFS(board, bfsDef)
  }

  def solveTopColor(board: CrossTeaser, color: Color ) = {
    val bfsDef = new BFSBoardDefinition[OrientablePiece] {
      override def found(t: CrossTeaser) = topsAreEqual(t,color) && emptyInCenter(t)
    }

    BFS(board, bfsDef)
  }


  def solveTopColor(board: CrossTeaser, color: Color, lowEstimateToFinal : heuristicFunction[CrossTeaser] ) = {
    val bfsDef = new BFSBoardDefinition[OrientablePiece] {
      override def found(t: CrossTeaser) = topsAreEqual(t,color) && emptyInCenter(t)
      override def heuristic(t:CrossTeaser ) =  lowEstimateToFinal(t)
    }

    BFS(board, bfsDef)
  }
}

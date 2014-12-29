package dideco

import dideco.OrientablePiece.Color.Color


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

  def topsAreEqual( t: CrossTeaser ) = {
    val pieces = t.allPieces.filter(_ != null)
    val topSides = pieces.map( _.orientable.get(Orientation.top) )
    topSides.forall( _ == topSides.head )
  }

  def northsAreEqual( t: CrossTeaser ) = {
    val pieces = t.allPieces.filter(_ != null)
    val northSides = pieces.map( _.orientable.get(Orientation.north) )
    northSides.forall( _ == northSides.head )
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

}

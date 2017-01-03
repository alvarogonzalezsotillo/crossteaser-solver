package dideco

import com.typesafe.scalalogging.slf4j.LazyLogging
import dideco.BFS.heuristicFunction
import dideco.OrientableColor.Color.Color
import dideco.Orientation.Orientation

/**
  * Created by alvaro on 29/12/14.
  */
object CrossTeaser extends LazyLogging{


  import Board.BFSBoardDefinition

  type CrossTeaser = Board[Orientable[Color]]

  def rotate180(board: CrossTeaser ) = {
    val pieces = for( r <- board.rows-1 to 0 by -1 ; c <- board.columns-1 to 0 by -1 ) yield board.pieceAt(c,r)
    logger.warn( board.allPieces.mkString(",") )
    logger.warn( pieces.mkString(",") )
    Board(board.columns,board.rows,pieces)
  }

  def apply(w: Int, h: Int, pieces: (String, String)*): CrossTeaser = {
    def P(sides: (String, String)) = if (sides == null) null else OrientableColor.from(sides._1, sides._2).head

    assert(pieces.size == w * h)
    Board(w, h, pieces.toIndexedSeq.map(P))
  }

  def apply(pieces: (String, String)*): CrossTeaser = apply(3, 3, pieces: _*)

  private def topsAreEqual(t: CrossTeaser, c: Color = null) = {
    val pieces = t.allPieces.filter(_ != null)
    val topSides = pieces.map(_.get(Orientation.top))
    val color = if (c == null) topSides.head else c
    topSides.forall(_ == color)
  }


  private def northsAreEqual(t: CrossTeaser, c: Color = null) = {
    val pieces = t.allPieces.filter(_ != null)
    val northSides = pieces.map(_.get(Orientation.north))
    val color = if (c == null) northSides.head else c
    northSides.forall(_ == color)
  }

  private def emptyInCenter(t: Board[_]) = {
    val r = t.rows / 2
    val c = t.columns / 2
    t.pieceAt(c, r) == null
  }

  private def emptyInRow(t: Board[_], r: Int) = {
    (0 until t.columns).exists(c => t.pieceAt(c, r) == null)
  }


  private def emptyInColumn(t: Board[_], c: Int) = {
    (0 until t.rows).exists(r => t.pieceAt(c, r) == null)
  }

  private def maxNumberOfEqualTops(t: CrossTeaser) = {
    val pieces = t.allPieces.filter(_ != null)
    val topSides = pieces.map(_.get(Orientation.top))
    topSides.groupBy(c => c).map { case (key, values) => values.size }.max
  }

  def stepsToTop(t: CrossTeaser, color: Color) = {

    def stepsToTop(o: Orientation) = o match {
      case Orientation.top => 0
      case Orientation.bottom => 2
      case Orientation.north => 1
      case Orientation.west => 1
      case Orientation.east => 1
      case Orientation.south => 1
      case _ => throw new IllegalStateException();
    }

    def functionalAndSlow = {
      val pieces = t.allPieces.filter(_ != null)
      pieces.map(p => stepsToTop(p.where(color)) * 2).sum
    }

    def fast = {
      var sum = 0
      val pieces = t.allPieces
      var i = 0
      while (i < pieces.size) {
        if (pieces(i) != null) {
          sum += stepsToTop(pieces(i).where(color))
        }
        i += 1
      }
      sum
    }

    fast
  }

  private val boardOrdering = Ordering.by((b: CrossTeaser) => b.toShortString)

  def solvePerfectly(board: CrossTeaser) = {

    val bfsDef = new BFSBoardDefinition[Orientable[Color]] {

      override val ordering = boardOrdering

      override def found(t: CrossTeaser) = {
        topsAreEqual(t) && northsAreEqual(t)
      }

      override def heuristic(t: CrossTeaser) = {
        8 - maxNumberOfEqualTops(t)
      }
    }

    BFS(board, bfsDef)
  }

  def solvePerfectly(board: CrossTeaser, topColor: Color, northColor: Color) = {

    val bfsDef = new BFSBoardDefinition[Orientable[Color]] {
      override val ordering = boardOrdering

      override def found(t: CrossTeaser) = {
        topsAreEqual(t, topColor) && northsAreEqual(t, northColor)
      }

      override def heuristic(t: CrossTeaser) = stepsToTop(t, topColor)
    }

    BFS(board, bfsDef)
  }


  def solveTopColor(board: CrossTeaser) = {

    val bfsDef = new BFSBoardDefinition[Orientable[Color]] {
      override val ordering = boardOrdering


      override def found(t: CrossTeaser) = {
        topsAreEqual(t) && emptyInCenter(t)
      }

      override def heuristic(t: CrossTeaser) = {
        8 - maxNumberOfEqualTops(t)
      }

    }

    BFS(board, bfsDef)
  }

  def solveTopColor(board: CrossTeaser, color: Color): BFS[CrossTeaser] = {
    solveTopColor(board, color, stepsToTop(_, color))
  }


  def solveTopColor(board: CrossTeaser, color: Color, h: (CrossTeaser) => Long): BFS[CrossTeaser] = {
    val bfsDef = new BFSBoardDefinition[Orientable[Color]] {
      override val ordering = boardOrdering

      override def found(t: CrossTeaser) = topsAreEqual(t, color) && emptyInCenter(t)

      override def heuristic(t: CrossTeaser) = h(t)
    }

    BFS(board, bfsDef)
  }


  def solutionInHalfs(board: CrossTeaser, color: Color) = {

    logger.warn( "board:" + board.toShortString )

    assert(board.columns == 3)
    assert(board.rows == 3)
    val h : heuristicFunction[Board[Orientable[Color]]] = stepsToTop(_, color)

    val firstHalfPieces = for ( r <- 0 until 2; c <- 0 until 3) yield board.pieceAt(c, r)
    logger.warn( "firstHalfPieces:" + firstHalfPieces )
    val firstHalf = Board(3, 2, firstHalfPieces)

    val firstDefinition = new BFSBoardDefinition[Orientable[Color]] {
      override val ordering = boardOrdering
      override def found(t: Board[Orientable[Color]]): Boolean = topsAreEqual(t, color) && emptyInRow(t, 1)
      override def heuristic(t: CrossTeaser) = h(t)
    }

    logger.warn( "firstHalf:" + firstHalf.toShortString )
    val firstBFS = BFS(firstHalf, firstDefinition)
    val firstSolution = firstBFS.search().get

    val secondHalfPieces = {
      (for (r <- 1 until 2;c <- 0 until 3) yield firstSolution.node.pieceAt(c, r)) ++
        (for (r <- 2 until 3;c <- 0 until 3) yield board.pieceAt(c, r))
    }
    val secondHalf = Board( 3, 2, secondHalfPieces)
    logger.warn( "secondHalf:" + secondHalf.toShortString )

    val secondDefinition= new BFSBoardDefinition[Orientable[Color]] {
      override val ordering = boardOrdering
      override def found(t: Board[Orientable[Color]]): Boolean = topsAreEqual(t, color) && emptyInRow(t,0) && emptyInColumn(t,1)
      override def heuristic(t: CrossTeaser) = h(t)
    }

    val secondBFS = BFS(secondHalf,secondDefinition)
    val secondSolution = secondBFS.search().get

    val lastRowOfBoard = for(c <- 0 until 3) yield board.pieceAt(c,2)
    val firstSeq = firstSolution.pathToRoot.map(_.node).map( t => Board(3,3,t.allPieces ++ lastRowOfBoard))
    val firstRowOfBoard = for( c <- 0 until 3 ) yield firstSolution.pathToRoot.head.node.pieceAt(c,0)
    val secondSeq = secondSolution.pathToRoot.map(_.node).map( t => Board(3,3, firstRowOfBoard ++ t.allPieces))

    secondSeq ++ firstSeq
  }


}

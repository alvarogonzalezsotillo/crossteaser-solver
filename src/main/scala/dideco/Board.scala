package dideco

import com.typesafe.scalalogging.slf4j.LazyLogging
import dideco.BFS.BFSDefinition

import dideco.OrientableColor.Color.Color

import scala.util.Random


/**
  * Created by alvaro on 21/09/2014.
  */


trait Board[T] {


  val columns: Int
  val rows: Int

  def pieceAt(column: Int, row: Int): T

  def pieceAt(l: Location): T = pieceAt(l.col, l.row)

  def free(l: Location): Boolean = free(l.col, l.row)

  def free(column: Int, row: Int): Boolean = pieceAt(column, row) == null

  def inside(l: Location): Boolean = inside(l.col, l.row)

  def inside(col: Int, row: Int) = col >= 0 && col < columns && row >= 0 && row < rows

  def allPieces: Array[T]

  def slowCountPieces = allPieces.count(_ != null)


  override lazy val toString = {
    val strings = for (r <- 0 until rows) yield {
      (for (c <- 0 until columns) yield {
        String.valueOf(pieceAt(c, r))
      }).mkString("{", ",", "}")
    }
    strings.mkString("{", ",", "}")
  }

  def toShortString: String

  def oneMovementBoards: Seq[Board[T]]
}


trait BoardOfOrientablePieces extends Board[Orientable[Color]] {
  override lazy val toShortString = {
    if (false) {
      val strings = for (r <- 0 until rows) yield {
        (for (c <- 0 until columns) yield {
          val p = pieceAt(c, r)
          if (p != null) p.toShortString else "__"
        }).mkString("{", ",", "}")
      }
      strings.mkString("{", ",", "}")
    }
    else {
      this.allPieces.map(p => if (p != null) p.toShortString else "null").mkString("")
    }
  }

  override def equals(o: Any) = o match {
    case b: BoardOfOrientablePieces => b.toShortString == toShortString
    case _ => false
  }

  override def hashCode(): Int = toShortString.hashCode()
}

object Board extends LazyLogging {

  trait BFSBoardDefinition[T] extends BFS.BFSDefinition[Board[T]] {
    override def expand(t: Board[T]) = {
      val ret = t.oneMovementBoards
      logger.debug(s"expand: ${t.toShortString}")
      logger.debug("  " + ret.map(_.toShortString).mkString(" -- "))
      ret
    }

    override def hashable(t: Board[T]) = t.toShortString
  }

  class BoardOfOrientablePiecesFromSeq(c: Int, r: Int, pieces: Array[Orientable[Color]]) extends BoardOfOrientablePieces {
    assert(pieces.size == c * r)


    override val columns = c
    override val rows = r

    def locationFromIndex(i: Int) = Location(i % columns, i / columns)

    // ensuring ( i >= 0 && i < columns*rows )
    def indexFromLocation(column: Int, row: Int): Int = (column + row * columns) // ensuring inside(Location(column,row)) ensuring (_ < pieces.length)

    def indexFromLocation(l: Location): Int = indexFromLocation(l.col, l.row)

    override def pieceAt(column: Int, row: Int): Orientable[Color] = if( inside(column,row) ) pieces(indexFromLocation(column, row)) else null

    override val allPieces: Array[Orientable[Color]] = pieces


    override def oneMovementBoards: Seq[Board[Orientable[Color]]] = {

      val turns = Turn.values.toArray

      def slowButFunctional = {

        // SLOW
        for (c <- 0 until columns;
             r <- 0 until rows if (!free(c, r));
             location = Location(c, r);
             piece = pieceAt(location);
             t <- turns;
             newLocation = location.to(t) if (inside(newLocation) && free(newLocation));
             newPiece = piece.turn(if (Turn.horizontal(t)) t else Turn.opposite(t))) yield {
          val newPieces = pieces.clone
          newPieces(indexFromLocation(c, r)) = null
          newPieces(indexFromLocation(newLocation)) = newPiece

          Board(columns, rows, newPieces)
        }
      }

      def fastButUgly = {
        var c = 0
        var boards : List[Board[Orientable[Color]]] = Nil
        while( c < columns ){

          var r = 0
          while( r < rows ){
            //println( s"location:${Location(c,r)} free:${free(c,r)}" )

            if( !free(c,r) ) {
              val location = Location(c, r)

              val piece = pieceAt(location)
              for (t <- turns) {
                val newLocation = location.to(t)
                //println( s"  newLocation:$newLocation  inside:${inside(newLocation)}  free:${free(newLocation)}")
                if (inside(newLocation) && free(newLocation)) {
                  val newPiece = piece.turn(if (Turn.horizontal(t)) t else Turn.opposite(t))
                  val newPieces = pieces.clone
                  newPieces(indexFromLocation(c, r)) = null
                  newPieces(indexFromLocation(newLocation)) = newPiece

                  val b = Board(columns, rows, newPieces) :: boards
                  boards = b
                }
              }
            }
            r += 1
            //println( s"  siguiente r:$r")
          }


          c += 1
          //println( s"  siguiente c:$c")

        }

        boards
      }


      fastButUgly
    }

  }


  def exploreAllMovements[T](board: Board[T]) = {
    val bfsDef = new BFSBoardDefinition[T] {
      override def found(t: Board[T]) = false

      override val ordering = Ordering.by((b: Board[T]) => b.toShortString)
    }

    BFS(board, bfsDef)
  }

  def scrambled[T](board: Board[T], steps: Int)(implicit random: Random): Board[T] = steps match {
    case n if n <= 0 => board
    case n =>
      val posibleSteps = board.oneMovementBoards
      val nextStep = posibleSteps(random.nextInt(posibleSteps.size))
      scrambled(nextStep, n - 1)
  }


  def explorePathTo[T](board: Board[T], goal: Board[T]) = {
    val bfsDef = new BFSBoardDefinition[T] {
      override def found(t: Board[T]) = t == goal

      override val ordering = Ordering.by((b: Board[T]) => b.toString)
    }
    BFS(board, bfsDef)
  }


  def apply(width: Int, height: Int, pieces: Array[Orientable[Color]]): Board[Orientable[Color]] = {
    new BoardOfOrientablePiecesFromSeq(width, height, pieces)
  }

}


object OnePieceBoard extends LazyLogging {


  def apply(width: Int, height: Int, piece: Orientable[Color], location: Location): Board[Orientable[Color]] = {


    new BoardOfOrientablePieces {
      override val columns: Int = width
      override val rows: Int = height

      def pieceAt(column: Int, row: Int) = {
        if (location.col == column && location.row == row)
          piece
        else
          null
      }

      val allPieces: Array[Orientable[Color]] = (for (r <- 0 until rows; c <- 0 until columns) yield pieceAt(c, r)).toArray

      def oneMovementBoards: Seq[Board[Orientable[Color]]] = {


        val ret = for (t <- Turn.values.toArray) yield {
          val newLocation = location.to(t)
          if (inside(newLocation)) {
            val newPiece = piece.turn(if (Turn.horizontal(t)) t else Turn.opposite(t))
            apply(width, height, newPiece, newLocation)
          }
          else {
            null
          }
        }
        ret.filter(_ != null)
      }
    }
  }
}
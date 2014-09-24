package dideco

import com.typesafe.scalalogging.slf4j.LazyLogging
import dideco.BFS.BFSDefinition


/**
 * Created by alvaro on 21/09/2014.
 */


trait Board[T] {


  val columns: Int
  val rows: Int

  def pieceAt(column: Int, row: Int): T

  def locationOf(piece: T): Location

  def inside(l: Location) = l.col >= 0 && l.col < columns && l.row >= 0 && l.row < rows


  override lazy val toString = {
    val strings = for (r <- 0 until rows) yield {
      (for (c <- 0 until columns) yield {
        String.valueOf(pieceAt(c, r))
      }).mkString("{", ",", "}")
    }
    strings.mkString("{", ",", "}")
  }

  val oneMovementBoards: Seq[Board[T]]

  override def equals(a: Any) = a match {
    case b: Board[_] => b.toString == toString
    case _ => false
  }

  override lazy val hashCode = toString.hashCode
}

object Board{


  trait BFSBoardDefinition[T] extends BFS.BFSDefinition[Board[T]]{
    override def expand(t: Board[T]) = t.oneMovementBoards
  }

  def exploreAllMovements[T]( board: Board[T] ) = {
    implicit val boardOrdering = Ordering.by( (b : Board[T]) => b.toString )

    val bfsDef = new BFSBoardDefinition[T] {
      override def found(t: Board[T]) = false
    }

    BFS(board,bfsDef)
  }

}


object OnePieceBoard extends LazyLogging {


  def apply(width: Int, height: Int, piece: OrientablePiece, location: Location): Board[OrientablePiece] = {


    new Board[OrientablePiece] {
      override val columns: Int = width
      override val rows: Int = height

      def locationOf(p: OrientablePiece) = if (p == piece) location else null

      def pieceAt(column: Int, row: Int) = {
        if (location._1 == column && location._2 == row)
          piece
        else
          null
      }

      lazy val oneMovementBoards = {
        val boards = Turn.values.map { t =>
          val newLocation = location.to(t)
          val newPiece = piece.turn(if (Turn.horizontal(t)) t else Turn.opposite(t))
          (newLocation, apply(width, height, newPiece, newLocation))
        }

        boards.filter { case (l, b) => b.inside(l)}.map(_._2).toSeq
      }
    }
  }
}
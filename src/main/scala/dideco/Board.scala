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
  def pieceAt( l : Location ) : T = pieceAt( l.col, l.row )
  def free( l: Location ) : Boolean = free( l.col, l.row )
  def free(column: Int, row: Int ) : Boolean = pieceAt(column,row) == null

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

object Board extends LazyLogging{


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

  def apply( width: Int, height: Int, pieces : Seq[OrientablePiece] ) : Board[OrientablePiece] = {
    assert( pieces.size == width * height )


    new Board[OrientablePiece]{
      override val columns = width
      override val rows = height

      def locationFromIndex( i: Int ) = Location(i%columns,i/rows) ensuring ( i >= 0 && i < columns*rows )
      def indexFromLocation( column: Int, row: Int ) : Int = (column + row*rows) ensuring inside(Location(column,row))

      def indexFromLocation( l: Location ) : Int = indexFromLocation(l.col, l.row )

      override def pieceAt(column: Int, row: Int): OrientablePiece = pieces( indexFromLocation(column, row) )



      override def locationOf(piece: OrientablePiece): Location = pieces.indexOf(piece) match{
        case -1 =>  null
        case i  => locationFromIndex(i)
      }

      override lazy val oneMovementBoards: Seq[Board[OrientablePiece]] = {
        val turns = Turn.values.toArray
        for( c <- 0 until columns ;
             r <- 0 until rows if( !free(c,r) ) ;
             location = Location(c,r) ;
             piece = pieceAt(location);
             t  <- turns ;
             newLocation = location.to(t) if( inside(newLocation) && free(newLocation) ) ;
             newPiece = piece.turn(if (Turn.horizontal(t)) t else Turn.opposite(t) ) ) yield{
          val newPieces = pieces.toArray.clone
          newPieces( indexFromLocation(c,r) ) = null
          newPieces( indexFromLocation(newLocation) ) = newPiece
          apply( width, height, newPieces)
        }
      }
    }
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
package dideco

import dideco.Turn.Turn

/**
 * Created by alvaro on 21/09/2014.
 */


trait Board[T] {



  import dideco.Board._

  val columns: Int
  val rows: Int

  def pieceAt(column: Int, row: Int): T

  def locationOf(piece: T): Location

  def inside( l: Location ) = l.col > 0 && l.col < columns && l.row > 0 && l.row < rows

  override lazy val toString = {
    val strings = for (r <- 0 to rows) yield {
      (for (c <- 0 to columns) yield {
        String.valueOf(pieceAt(c, r))
      }).mkString("{", ",", "}")
    }
    strings.mkString("{", ",", "}")
  }

  def oneMovementBoards : Seq[Board[T]]
}


object Board {

  object Location{
    val increments = Map(
      Turn.toEast -> (1,0),
      Turn.toNorth -> (0,-1),
      Turn.toSouth -> (0,1),
      Turn.toWest -> (-1,0)
    )

    implicit def toLocation( p: (Int,Int) ) = new Location(p._1,p._2)
    implicit def toPair( l: Location ) = (l.col,l.row)
    def apply( col:Int, row: Int) = new Location(col,row)
  }

  class Location( val col:Int, val row:Int ){

    import Location._

    def add( p: (Int,Int) ) = {
      val c = col + p._1
      val r = row + p._2
      Location(c,r)
    }

    def to( t: Turn ) = add( increments(t) )

  }

  def apply(width: Int, height: Int, piece: OrientablePiece, location: Location) : Board[OrientablePiece]= {

    val w = width
    val h = height

    new Board[OrientablePiece]{
      val columns = w
      val rows = h

      def locationOf(p: OrientablePiece) = if (p == piece) location else null

      def pieceAt(column: Int, row: Int) = {
        if (location._1 == column && location._2 == row)
          piece
        else
          null
      }

      def oneMovementBoards = {
        val boards = Turn.values.map { t =>
          val newLocation = location.to(t)
          val newPiece = piece.turn( if(Turn.horizontal(t)) t else Turn.opposite(t) )
          (newLocation, apply( width, height, newPiece, newLocation ) )
        }

        boards.filter{ case (l,b) => b.inside(l) }.map( _._2 ).toSeq
      }
    }
  }
}

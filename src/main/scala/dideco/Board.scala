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
  def pieceAt( l : Location ) : T = pieceAt( l.col, l.row )
  def free( l: Location ) : Boolean = free( l.col, l.row )
  def free(column: Int, row: Int ) : Boolean = pieceAt(column,row) == null

  def locationOf(piece: T): Location

  def inside(l: Location) : Boolean = inside( l.col, l.row )
  def inside(col: Int, row: Int ) = col >= 0 && col < columns && row >= 0 && row < rows

  def allPieces : IndexedSeq[T] = (for( r <- 0 until rows ; c <- 0 until columns ) yield pieceAt(c,r)).toIndexedSeq

  def slowCountPieces = allPieces.count( _ == true)



  override def toString = {
    val strings = for (r <- 0 until rows) yield {
      (for (c <- 0 until columns) yield {
        String.valueOf(pieceAt(c, r))
      }).mkString("{", ",", "}")
    }
    strings.mkString("{", ",", "}")
  }

  lazy val toShortString = toString

  val oneMovementBoards: Seq[Board[T]]

  override def equals(a: Any) = a match {
    case b: Board[_] => b.toShortString == toShortString
    case _ => false
  }

  override lazy val hashCode = toString.hashCode
}


trait BoardOfOrientablePieces extends Board[Orientable[Color]]{
  override lazy val toShortString = {
    val strings = for (r <- 0 until rows) yield {
      (for (c <- 0 until columns) yield {
        val p = pieceAt(c, r)
        if( p != null ) p.toShortString else "null"
      }).mkString("{", ",", "}")
    }
    strings.mkString("{", ",", "}")
  }

}

object Board extends LazyLogging {

  private val random = new java.util.Random()

  trait BFSBoardDefinition[T] extends BFS.BFSDefinition[Board[T]] {
    override def expand(t: Board[T]) = t.oneMovementBoards
  }


  def exploreAllMovements[T]( board: Board[T] ) = {
    implicit val boardOrdering = Ordering.by((b: Board[T]) => b.toString)

    val bfsDef = new BFSBoardDefinition[T] {
      override def found(t: Board[T]) = false
    }

    BFS(board,bfsDef)
  }

  def scrambled[T](board: Board[T], steps: Int) : Board[T] = steps match{
    case n if n <= 0 => board
    case n =>
      val posibleSteps = board.oneMovementBoards
      val nextStep = posibleSteps( random.nextInt(posibleSteps.size) )
      scrambled(nextStep, n-1)
  }


  def explorePathTo[T]( board: Board[T], goal: Board[T] ) = {
    implicit val boardOrdering = Ordering.by((b: Board[T]) => b.toString)

    val bfsDef = new BFSBoardDefinition[T] {
      override def found(t: Board[T]) =  t == goal
    }
    BFS(board,bfsDef)
  }



  def apply( width: Int, height: Int, pieces : IndexedSeq[Orientable[Color]] ) : Board[Orientable[Color]] = {
    assert( pieces.size == width * height )


    new BoardOfOrientablePieces{
      override val columns = width
      override val rows = height

      def locationFromIndex( i: Int ) = Location(i%rows,i/rows) ensuring ( i >= 0 && i < columns*rows )
      def indexFromLocation( column: Int, row: Int ) : Int = (column + row*rows) ensuring
        inside(Location(column,row)) ensuring
        (_ < pieces.length)

      def indexFromLocation( l: Location ) : Int = indexFromLocation(l.col, l.row )

      override def pieceAt(column: Int, row: Int): Orientable[Color] = pieces( indexFromLocation(column, row) )

      override val allPieces : IndexedSeq[Orientable[Color]] = pieces

      override def locationOf(piece: Orientable[Color]): Location = pieces.indexOf(piece) match{
        case -1 =>  null
        case i  => locationFromIndex(i)
      }


      override lazy val oneMovementBoards: Seq[Board[Orientable[Color]]] = {
        val turns = Turn.values.toArray
        val ret = for( c <- 0 until columns ;
             r <- 0 until rows if( !free(c,r) ) ;
             location = Location(c,r) ;
             piece = pieceAt(location);
             t  <- turns ;
             newLocation = location.to(t) if( inside(newLocation) && free(newLocation) ) ;
             newPiece = piece.turn(if (Turn.horizontal(t)) t else Turn.opposite(t) ) ) yield{
          val newPieces = pieces.toArray.clone
          newPieces( indexFromLocation(c,r) ) = null
          newPieces( indexFromLocation(newLocation) ) = newPiece
          apply( columns, rows, newPieces)
        }



        ret ensuring( ret.forall( b => b.slowCountPieces == b.columns * b.columns -1 ) )
      }

    }
  }

}


object OnePieceBoard extends LazyLogging {


  def apply(width: Int, height: Int, piece: Orientable[Color], location: Location): Board[Orientable[Color]] = {


    new BoardOfOrientablePieces {
      override val columns: Int = width
      override val rows: Int = height

      def locationOf(p: Orientable[Color]) = if (p == piece) location else null

      def pieceAt(column: Int, row: Int) = {
        if (location._1 == column && location._2 == row)
          piece
        else
          null
      }

      lazy val oneMovementBoards : Seq[Board[Orientable[Color]]]= {


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
        ret.filter( _ != null )
      }
    }
  }
}
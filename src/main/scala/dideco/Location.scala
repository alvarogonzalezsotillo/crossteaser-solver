package dideco

import dideco.Turn._

/**
 * Created by alvaro on 22/09/2014.
 */
object Location{
  val increments = Map(
    Turn.toEast -> (1,0),
    Turn.toNorth -> (0,-1),
    Turn.toSouth -> (0,1),
    Turn.toWest -> (-1,0)
  )

  implicit def toLocation( p: (Int,Int) ) = new Location(p._1,p._2)
  implicit def toPair( l: Location ) = (l.col,l.row)

  private val cols = 100
  private val rows = 100
  private val cache = Array.tabulate(cols,rows){ (c, r) =>
    new Location(c-cols/2,r-rows/2)
  }


  def apply( col:Int, row: Int) = cache(col+cols/2)(row+rows/2)
}

class Location( val col:Int, val row:Int ){

  import Location._

  def add( p: (Int,Int) ) = {
    val c = col + p._1
    val r = row + p._2
    Location(c,r)
  }

  def to( t: Turn ) = add( increments(t) )

  override val toString = s"($col,$row)"

}

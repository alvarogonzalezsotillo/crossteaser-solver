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

  private val cache = collection.mutable.Map[Int,collection.mutable.Map[Int,Location]]()

  private def getFromCache( col:Int, row: Int ) = {
    val level1 = cache.getOrElseUpdate( col, collection.mutable.Map[Int,Location]() )
    level1.getOrElseUpdate( row, new Location(col,row) )
  }

  def apply( col:Int, row: Int) = getFromCache(col, row )
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

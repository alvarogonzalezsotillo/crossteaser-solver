package dideco

/**
 * Created by alvaro on 24/08/2014.
 */

import dideco.Orientation._
import dideco.Turn._

trait Orientable[T] extends (Orientation=>T){

  import Turn.Turn
  import Orientable._

  def get(o:Orientation): T

  def apply(o:Orientation) = get(o)

  def turn(t: Turn) : Orientable[T] = Orientable.turn(this,t)

}

object Orientable{
  class IndexedOrientable[T]( val base:Orientable[T],
                              top:Orientation,
                              north:Orientation,
                              east:Orientation,
                              south:Orientation,
                              west:Orientation,
                              bottom:Orientation) extends Orientable[T] {


    val indexArray = Array(top,north,east,south,west,bottom)

    override def get(o: Orientation): T = base.get(indexArray(o))
  }

  def turn[T]( o: Orientable[T], t: Turn ) : Orientable[T] = o match {

    case io: IndexedOrientable[T] =>
      val ia = io.indexArray
      t match {
        case Turn.toWest =>
          new IndexedOrientable(io.base, ia(east), ia(north), ia(bottom), ia(south), ia(top), ia(west))
        case Turn.toEast =>
          new IndexedOrientable(io.base, ia(west), ia(north), ia(top), ia(south), ia(bottom), ia(east))
        case Turn.toNorth =>
          new IndexedOrientable(io.base, ia(south), ia(top), ia(east), ia(bottom), ia(west), ia(north))
        case Turn.toSouth =>
          new IndexedOrientable(io.base, ia(north), ia(bottom), ia(east), ia(top), ia(west), ia(south))
        case _ =>
          throw new IllegalArgumentException(t.toString)
      }

    case or: Orientable[T] =>
      t match {
        case Turn.toWest =>
          new IndexedOrientable(or, east, north, bottom, south, top, west)
        case Turn.toEast =>
          new IndexedOrientable(or, west, north, top, south, bottom, east)
        case Turn.toNorth =>
          new IndexedOrientable(or, south, top, east, bottom, west, north)
        case Turn.toSouth =>
          new IndexedOrientable(or, north, bottom, east, top, west, south)
        case _ =>
          throw new IllegalArgumentException(t.toString)
      }
  }

}





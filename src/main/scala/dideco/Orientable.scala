package dideco

/**
 * Created by alvaro on 24/08/2014.
 */

import dideco.Orientation._
import dideco.Turn._

trait Orientable[T] extends (Orientation=>T){

  import Turn.Turn
  import Orientable._

  def asIndexedSeq(): IndexedSeq[T]

  def get(o:Orientation): T = asIndexedSeq()(o)

  def apply(o:Orientation) = get(o)

  def where(t:T) = Orientation.values.find( get(_) == t ).get

  def turn(t: Turn) : Orientable[T] = Orientable.turn(this,t)

  def someTurns( turns: Turn* ) = turns.foldLeft(this)( (o, t) => o.turn(t) )

  override lazy val toString = asIndexedSeq.mkString("[",",","]")

  lazy val toShortString = asIndexedSeq.take(2).mkString("[",",","]")

  override lazy val hashCode  = asIndexedSeq.mkString.hashCode

  override def equals(o:Any) = toString.equals(String.valueOf(o))
}

object Orientable{

  def apply[T]( inTop: T, inNorth: T, inEast: T, inSouth: T, inWest: T, inBottom: T ) : Orientable[T] ={
    new Orientable[T]{
      val asIndexedSeq = IndexedSeq(inTop,inNorth,inEast,inSouth,inWest,inBottom)
    }
  }


  private class IndexedOrientable[T]( val base:Orientable[T], val indexArray: Array[Orientation] ) extends Orientable[T] {
    def asIndexedSeq = Orientation.values.map( this )
    override def get(o: Orientation): T = base.get(indexArray(o))
  }

  private object IndexedOrientable{

    private val seqCache = collection.mutable.Map[IndexedSeq[Orientation],Array[Orientation]]()

    def apply[T]( base: Orientable[T],
                  inTop: Orientation,
                  inNorth: Orientation,
                  inEast: Orientation,
                  inSouth: Orientation,
                  inWest: Orientation,
                  inBottom: Orientation ) : IndexedOrientable[T] ={
      val seq = IndexedSeq(inTop,inNorth,inEast,inSouth,inWest,inBottom)
      val array = seqCache.getOrElseUpdate( seq, seq.toArray )
      new IndexedOrientable(base,array)
    }
  }


  def turn[T]( o: Orientable[T], t: Turn ) : Orientable[T] = o match {

    case io: IndexedOrientable[T] =>
      val ia = io.indexArray
      t match {
        case Turn.toWest =>
          IndexedOrientable(io.base, ia(east), ia(north), ia(bottom), ia(south), ia(top), ia(west))
        case Turn.toEast =>
          IndexedOrientable(io.base, ia(west), ia(north), ia(top), ia(south), ia(bottom), ia(east))
        case Turn.toNorth =>
          IndexedOrientable(io.base, ia(south), ia(top), ia(east), ia(bottom), ia(west), ia(north))
        case Turn.toSouth =>
          IndexedOrientable(io.base, ia(north), ia(bottom), ia(east), ia(top), ia(west), ia(south))
        case _ =>
          throw new IllegalArgumentException(t.toString)
      }

    case or: Orientable[T] =>
      t match {
        case Turn.toWest =>
          IndexedOrientable(or, east, north, bottom, south, top, west)
        case Turn.toEast =>
          IndexedOrientable(or, west, north, top, south, bottom, east)
        case Turn.toNorth =>
          IndexedOrientable(or, south, top, east, bottom, west, north)
        case Turn.toSouth =>
          IndexedOrientable(or, north, bottom, east, top, west, south)
        case _ =>
          throw new IllegalArgumentException(t.toString)
      }
  }

  def withCache[T]( o: Orientable[T] ) : (Orientable[T],Set[Orientable[T]]) = {

    class CachedOrientable[T]( override val asIndexedSeq: IndexedSeq[T] ) extends Orientable[T]{
      override def turn( t: Turn ) = turns(t)
      val turns = new Array[Orientable[T]](Turn.values.size)
    }

    val cache = collection.mutable.Map[ IndexedSeq[T], CachedOrientable[T] ]()

    def explore( co: CachedOrientable[T] ) : Unit = {
      for( t <- Turn.values if co.turns(t) == null ){
        val tco = Orientable.turn(co,t)
        val seq = tco.asIndexedSeq()
        val cachedTco = cache.getOrElseUpdate( seq, new CachedOrientable(seq) )
        co.turns(t) = cachedTco
        explore( cachedTco )
      }
    }

    val root = new CachedOrientable(o.asIndexedSeq)
    cache( root.asIndexedSeq ) = root
    explore(root)

    (root,cache.values.toSet)
  }
}

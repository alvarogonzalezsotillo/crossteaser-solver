package dideco

/**
 * Created by alvaro on 27/08/2014.
 */

import Turn._

object OrientablePiece {

  object Color extends Enumeration{
    type Color = Value
    val G,R,P,B,Y,O = Value

    def apply(s:String) = values.find( c => c.toString()(0) == s.toUpperCase()(0) )
  }

  import Color._
  val originalOrientable = Orientable( G, R, P, B, Y, O )


  type OrColor = Orientable[Color]

  private val orientableCache : collection.Map[OrColor,OrColor]= {
    val ret = collection.mutable.Map[OrColor,OrColor]()

    val originalO = originalOrientable
    ret(originalO) = originalO
    var oldSize = 0
    while( oldSize != ret.size ){
      oldSize = ret.size
      for( o <- ret.keySet ; t <- Turn.values ){
        val newO = o.turn(t)
        ret(newO) = newO
      }
    }

    ret
  }



  private val pieceCache = collection.mutable.Map[OrColor,OrientablePieceImpl]()

  allOrientables.foreach( o => getOrCreatePiece(o) )

  def allPieces : Seq[OrientablePiece] = pieceCache.values.toSeq
  def allOrientables : Seq[OrColor] = orientableCache.values.toSeq

  private def getOrCreatePiece( o: OrColor ) = pieceCache.getOrElseUpdate(o, new OrientablePieceImpl(orientableCache(o)))

  private class OrientablePieceImpl( override val orientable: OrColor ) extends OrientablePiece{

    val pieceTurns = new Array[OrientablePiece](Turn.values.size)

    override def turn(t:Turn) = pieceTurns(t.id) match{
      case null =>
        val newOrientable = orientable.turn(t)
        val ret = getOrCreatePiece( newOrientable )
        pieceTurns(t.id) = ret
        ret

      case ret =>
        ret
    }
  }

  def fromColors( sides: Color* ) : Iterable[OrientablePiece] = {
    pieceCache.values.filter { op =>
      sides.zip( op.orientable.asSeq() ).forall { case(s, c) =>
        s == c
      }
    }
  }

  def from( sides: String* ) : Iterable[OrientablePiece] = fromColors( sides.map( s=> Color(s).get ):_* )
  def from( o: OrColor ) : OrientablePiece = getOrCreatePiece(o)

}

trait OrientablePiece {

  import OrientablePiece._
  import OrientablePiece.Color._

  val orientable : Orientable[Color]

  def turn(t:Turn) : OrientablePiece

  override val toString = orientable.toString

  val toShortString = orientable.toShortString

}

package dideco

/**
 * Created by alvaro on 27/08/2014.
 */

import Turn._

object OrientablePiece {

  object Color extends Enumeration{
    type Color = Value
    val G,R,P,B,Y,O = Value

    def apply(s:String) = values.find( c => c.toString == s.toUpperCase )
  }

  import Color._

  lazy val orientableCache : collection.Map[Orientable[Color],Orientable[Color]]= {
    val ret = collection.mutable.Map[Orientable[Color],Orientable[Color]]()

    val originalO = Orientable( G, R, P, B, Y, O )
    ret(originalO) = originalO
    var oldSize = 0
    while( oldSize != ret.size ){
      oldSize = ret.size

      println( "\n\n\n***************************************")
      println( s"size:${ret.size}")
      for( o <- ret.keySet ){
        println( s"${o.toString} : ${o.hashCode}")
      }

      for( o <- ret.keySet ; t <- Turn.values ){
        val newO = o.turn(t)
        ret(newO) = newO
      }
    }

    ret

  }
}

trait OrientablePiece {

  import OrientablePiece._
  import OrientablePiece.Color._

  val orientable : Orientable[Color]

  def turn(t:Turn) : OrientablePiece


}

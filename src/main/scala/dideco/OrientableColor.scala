package dideco

/**
 * Created by alvaro on 27/08/2014.
 */

import Turn._
import dideco.OrientableColor.Color.Color

object OrientableColor {


  type OrientableColor = Orientable[Color]


  object Color extends Enumeration{
    type Color = Value
    val G,R,P,B,Y,O = Value

    def apply(s:String) = values.find( c => c.toString()(0) == s.toUpperCase()(0) )
  }

  import Color._

  val originalOrientableWithoutCache = Orientable(G, R, P, B, Y, O)
  private val cache = Orientable.withCache( originalOrientableWithoutCache )


  val (originalOrientable, allPieces) = cache


  def numberOfPiecesInCache = allPieces.size

  def fromColors( sides: Color* ) : Iterable[OrientableColor] = {
    allPieces.filter { o =>
      sides.zip( o.asIndexedSeq() ).forall { case(s, c) =>
        s == c
      }
    }
  }

  def from( sides: String* ) : Iterable[OrientableColor] = fromColors( sides.map( s=> Color(s).get ):_* )

}

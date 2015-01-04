package dideco

import java.io.PrintStream

import dideco.OrientableColor.Color.Color

/**
 * Created by alvaro on 7/12/14.
 */
object BoardToJS {

  def apply( b: Board[Orientable[Color]], out: PrintStream ){
    out.println( "new Board(" )
    out.println( "  [" )

    for( r <- 0 until b.rows ){
      out.println( "    [" )
      for( c <- 0 until b.columns ) {
        val piece = b.pieceAt(c, r)
        out.print( "      new Piece(" )
        if( piece != null ) {
          val p = piece.
            asIndexedSeq().
            mkString("'", "','", "'")
          out.print( p  )
        }
        out.print( ")" )
        out.println( if (c == b.columns-1) "" else "," )
      }
      out.print( "    ]" )
      out.println( if (r == b.rows-1) "" else "," )
    }
    out.println( "  ]" )
    out.println( ")")
  }

  def apply( boards: Seq[Board[Orientable[Color]]], out: PrintStream ) {
    out.println("[")
    val array = boards.toArray
    for (i <- 0 until array.size) {
      apply(array(i), out)
      out.println(if (i == array.size - 1) "" else ",")
    }
    out.println("]")
  }
}

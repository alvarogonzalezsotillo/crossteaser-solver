package dideco

import dideco.OrientableColor.Color
import dideco.OrientableColor.Color
import dideco.OrientableColor.Color.Color

/**
 * Created by alvaro on 28/12/14.
 */
object Main extends App{

  def measure[T](proc: => T) = {
    val ini = System.currentTimeMillis
    val ret = proc
    val end = System.currentTimeMillis
    println(s"${end - ini} ms")
    ret
  }


  def P( sides : String* ) = OrientableColor.from(sides:_*).head
  val Array(y,o,g,b,r,p) = Array("Y","O","G","B","R","P")

  println( OrientableColor.allPieces.map(_.toShortString).mkString("\n") )


  val board = Board( 3, 2, IndexedSeq(
    P(y,b),    P(y,b),    P(r,g),
    P(p,g),    null,    P(r,g)
  ))

  Logging.configure()

  measure {
    val topColor = Color(o).get

    //val bfs = CrossTeaser.solvePerfectly(board, Color("Y").get, Color("O").get )
    val bfs = CrossTeaser.solveTopColor(board, topColor, CrossTeaser.stepsToTop(_, topColor) )

    val found = bfs.search()

    val solution = found.get.pathToRoot

    println(solution)

    BoardToJS(solution.map(_.node), System.out)
  }

}

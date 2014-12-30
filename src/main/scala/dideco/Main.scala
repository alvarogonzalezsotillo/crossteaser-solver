package dideco

import dideco.OrientablePiece.Color

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


  def P( sides : String* ) = OrientablePiece.from(sides:_*).head

  val board = Board( 3, 3, IndexedSeq(
    P("Y","O"), P("G","B"), P("R","P"),
    P("O","B"), P("B","O"), P("P","O"),
    null, P("R","Y"), P("O","R")
  ))

  measure {
    val bfs = CrossTeaser.solvePerfectly(board, Color("Y").get, Color("O").get )

    val found = bfs.search()

    val solution = found.get.pathToRoot

    println(solution)

    BoardToJS(solution.map(_.node), System.out)
  }

}

package dideco

import java.util.Scanner

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



  val Array(y,o,g,b,r,p) = Array("Y","O","G","B","R","P")

  println( OrientableColor.allPieces.map(_.toShortString).mkString("\n") )


  val board = CrossTeaser( 3, 2,
    (y,b),(y,b),(r,g),
    (p,g),null,(r,g)
  )

  Logging.configure()

  def waitForProfiler() = {
    println( "Intro para seguir..." )
    util.Try(new Scanner(System.in).nextLine())
  }

  waitForProfiler()

  measure {
    val topColor = Color(o).get

    //val bfs = CrossTeaser.solvePerfectly(board, Color("Y").get, Color("O").get )
    val bfs = CrossTeaser.solveTopColor(board, topColor )

    val found = bfs.search()

    val solution = found.get.pathToRoot

    println(solution)

    BoardToJS(solution.map(_.node), System.out)
  }

}

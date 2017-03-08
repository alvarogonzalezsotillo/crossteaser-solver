package dideco

import java.io.{FileOutputStream, PrintStream}
import java.util.Scanner

import dideco.OrientableColor.Color
import dideco.OrientableColor.Color
import dideco.OrientableColor.Color.Color

/**
  * Created by alvaro on 28/12/14.
  */
object Main extends App {

  def measure[T](proc: => T) = {
    val ini = System.currentTimeMillis
    val ret = proc
    val end = System.currentTimeMillis
    println(s"${end - ini} ms")
    ret
  }


  val Array(y, o, g, b, r, p) = Array("Y", "O", "G", "B", "R", "P")

  println(OrientableColor.allPieces.map(_.toShortString).mkString("\n"))


  val board1 = CrossTeaser(3, 3,
    (y, o), (g, b), (r, p),
    (o, b), (b, o), (p, o),
    null, (r, y), (o, r)
  )

  val board2 = CrossTeaser(3, 3,
    null, (p, b), (g, p),
    (o, b), (r, y), (o, p),
    (o, y), (o, b), (o, y)
  )

  val board3 = CrossTeaser(3, 3,
    (p,o), null, (o,b),
    (g,y), (b,o), (p,b),
    (p,o), (y,o), (o,y)

  )
  //val board = CrossTeaser.rotate180(board1)
  val board = board3

  Logging.configure()

  def waitForProfiler() = {
    println("Intro para seguir...")
    new Scanner(System.in).nextLine()
  }

  waitForProfiler()

  measure {
    val topColor = Color(y).get
    val northColor = Color(y).get

    def simpleSolution = {

      //val bfs = CrossTeaser.solvePerfectly(board, Color("Y").get, Color("O").get )
      val bfs = CrossTeaser.solveTopColor(board, topColor)


      val found = bfs.search()

      found.get.pathToRoot.map(_.node)
    }

    def complexSolution = {
      CrossTeaser.solutionInHalfs(board, topColor, northColor)
    }

    println(board)
    val solution = complexSolution

    println(solution)

    BoardToJS(solution, System.out)
    BoardToJS(solution, new PrintStream(new FileOutputStream("solution.json")))
  }

}

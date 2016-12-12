package dideco

/**
 * Created by alvaro on 24/08/2014.
 */
object Orientation{
  type Orientation = Int
  val top:Orientation = 0
  val north:Orientation = 1
  val east:Orientation = 2
  val south:Orientation = 3
  val west:Orientation = 4
  val bottom:Orientation = 5

  val values = Array(top,north,east,south,west,bottom)

  val first = top
  val last = bottom
}

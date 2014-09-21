package dideco

/**
 * Created by alvaro on 24/08/2014.
 */
object Turn extends Enumeration{

  type Turn = Value

  val toWest, toEast, toNorth, toSouth = Value

  val opposite = Map(
    toWest -> toEast,
    toEast -> toWest,
    toNorth -> toSouth,
    toSouth -> toNorth
  )

  def horizontal( t: Turn ) = t == toWest || t== toEast
  def vertical( t: Turn ) = !horizontal(t)
}

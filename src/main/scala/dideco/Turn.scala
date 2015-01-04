package dideco

/**
 * Created by alvaro on 24/08/2014.
 */
object Turn{

  type Turn = Int

  val toWest = 0
  val toEast = 1
  val toNorth = 2
  val toSouth = 3

  val values = IndexedSeq(toWest,toEast,toNorth,toSouth)

  val opposite = Map(
    toWest -> toEast,
    toEast -> toWest,
    toNorth -> toSouth,
    toSouth -> toNorth
  )

  def horizontal( t: Turn ) = t == toWest || t== toEast
  def vertical( t: Turn ) = !horizontal(t)
}

package dideco

/**
 * Created by alvaro on 24/08/2014.
 */

import Orientation.Orientation
import Turn.Turn
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IndexedOrientableTest extends FlatSpec {


  import Turn._
  import Orientation._

  object oTest extends Orientable[String] {

    override def get(o: Orientation): String = o match {
      case Orientation.top => "arriba"
      case Orientation.north => "norte"
      case Orientation.east => "este"
      case Orientation.south => "sur"
      case Orientation.west => "oeste"
      case Orientation.bottom => "abajo"
    }

  }

  "An orientable" should "stay the same rotated to the south and then to the north" in {
    val o = oTest.turn(toSouth).turn(toNorth)

    assert( o(top) == oTest(top) )
    assert( o(north) == oTest(north) )
    assert( o(east) == oTest(east) )
    assert( o(south) == oTest(south) )
    assert( o(west) == oTest(west) )
    assert( o(bottom) == oTest(bottom) )
  }

  "An orientable" should "rotate to the south" in {
    val o = oTest.turn(toSouth)

    assert( o(top) == oTest(north) )
    assert( o(north) == oTest(bottom) )
    assert( o(east) == oTest(east) )
    assert( o(south) == oTest(top) )
    assert( o(west) == oTest(west) )
    assert( o(bottom) == oTest(south) )
  }

  "An orientable" should "rotate to the east" in {
    val o = oTest.turn(toEast)

    assert( o(top) == oTest(west) )
    assert( o(north) == oTest(north) )
    assert( o(east) == oTest(top) )
    assert( o(south) == oTest(south) )
    assert( o(west) == oTest(bottom) )
    assert( o(bottom) == oTest(east) )
  }

  "An orientable" should "stay the same rotated three times to west than once to east" in {
    val o1 = oTest.turn(toWest).turn(toWest).turn(toWest)
    val o2 = oTest.turn(toEast)

    assert( o1(top) == o2(top) )
    assert( o1(north) == o2(north) )
    assert( o1(east) == o2(east) )
    assert( o1(south) == o2(south) )
    assert( o1(west) == o2(west) )
    assert( o1(bottom) == o2(bottom) )
  }


  "An orientable" should "rotate twice to the south" in{
    val o = oTest.turn(toSouth).turn(toSouth)

    assert( o(top) == oTest(bottom) )
    assert( o(north) == oTest(south) )
    assert( o(east) == oTest(east) )
    assert( o(south) == oTest(north) )
    assert( o(west) == oTest(west) )
    assert( o(bottom) == oTest(top) )
  }

  "An orientable" should "have same state after rotating twice to the south or twice to the north" in {
    val o1 = oTest.turn(toSouth).turn(toSouth)
    val o2 = oTest.turn(toNorth).turn(toNorth)

    assert( o1(top) == o2(top) )
    assert( o1(north) == o2(north) )
    assert( o1(east) == o2(east) )
    assert( o1(south) == o2(south) )
    assert( o1(west) == o2(west) )
    assert( o1(bottom) == o2(bottom) )
  }

  "An orientable" should "stay the same rotated four times in every direction" in{
    for( t <- Turn.values ){
      val o = oTest.turn(t).turn(t).turn(t).turn(t)

      assert( o(top) == oTest(top) )
      assert( o(north) == oTest(north) )
      assert( o(east) == oTest(east) )
      assert( o(south) == oTest(south) )
      assert( o(west) == oTest(west) )
      assert( o(bottom) == oTest(bottom) )
    }
  }
}
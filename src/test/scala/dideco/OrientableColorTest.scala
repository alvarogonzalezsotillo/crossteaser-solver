package dideco


import dideco.OrientableColor.Color
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class OrientableColorTest extends FlatSpec {

  "The cache of pieces" should "contain 24 instances" in {
    val cache = OrientableColor.allPieces
    assert(cache.size == 24)
  }

  "The cache of pieces" should "contains 24 pieces for each combination for the two first colors" in {
    val colors = Color.values.toArray
    val pieces = for (c1 <- colors; c2 <- colors ) yield OrientableColor.fromColors(c1, c2)
    assert(pieces.flatten.size == 24)
  }

  "A piece" should "be the same instance after 4 rotations" in {
    for (p <- OrientableColor.allPieces; t <- Turn.values) {
      val rotated = p.turn(t).turn(t).turn(t).turn(t)
      assert(p eq rotated)
    }
  }

  "A piece" should "yield the same instance if rotated twice" in {
    val p = OrientableColor.originalOrientable
    val p1 = p.turn(Turn.toEast)
    val p2 = p.turn(Turn.toEast)
    assert(p1 eq p2)
  }

  "The original piece" should "be compatible with the original piece without cache" in{
    val o = OrientableColor.originalOrientableWithoutCache
    val oc = OrientableColor.originalOrientable

    assert( o.asIndexedSeq() == oc.asIndexedSeq() )
  }

  "The original piece" should "be compatible with the original piece without cache after one rotation" in{
    val o = OrientableColor.originalOrientableWithoutCache
    val oc = OrientableColor.originalOrientable

    for( t <- Turn.values ) {
      assert(o.turn(t).asIndexedSeq() == oc.turn(t).asIndexedSeq())
    }
  }


  "The original piece" should "be compatible with the original piece without cache after some rotations" in{
    val o = OrientableColor.originalOrientableWithoutCache
    val oc = OrientableColor.originalOrientable

    for( t1 <- Turn.values ; t2 <- Turn.values ; t3 <- Turn.values ) {
      assert(o.someTurns(t1,t2,t3).asIndexedSeq() == oc.someTurns(t1,t2,t3).asIndexedSeq())
    }
  }

}
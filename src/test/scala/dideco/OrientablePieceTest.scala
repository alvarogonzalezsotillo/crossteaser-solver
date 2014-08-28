package dideco

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class OrientablePieceTest extends FlatSpec {

  import dideco.OrientablePiece._

  "The cache of orientables" should "contain 24 instances" in {
    val cache = OrientablePiece.allOrientables
    assert(cache.size == 24)
  }

  "The cache of pieces" should "contain 24 instances" in {
    val cache = OrientablePiece.allPieces
    assert(cache.size == 24)
  }

  "The cache of pieces" should "contains 24 pieces for each combination for the two first colors" in {
    val pieces = for (c1 <- Color.values; c2 <- Color.values ) yield fromColors(c1, c2)
    assert(pieces.flatten.size == 24)
  }

  "A piece" should "be the same instance after 4 rotations" in {
    for (p <- OrientablePiece.allPieces; t <- Turn.values) {
      val rotated = p.turn(t).turn(t).turn(t).turn(t)
      assert(p eq rotated)
    }
  }

  "A piece" should "yield the same instance if rotated twice" in {
    val p = from(originalOrientable)
    val p1 = p.turn(Turn.toEast)
    val p2 = p.turn(Turn.toEast)
    assert(p1 eq p2)
  }



  "A piece" should "has the same colors than the associated orientable" in {
    for (o <- OrientablePiece.allOrientables) {
      val piece = from(o)
      assert(Orientation.values.forall(d => piece.orientable(d) == o(d)), o)
    }
  }

  "A piece" should "has the same colors than the associated orientable after a turn" in {
    for (o <- OrientablePiece.allOrientables; t <- Turn.values) {
      val orientable = o.turn(t)
      val piece = from(o).turn(t)

      assert(Orientation.values.forall(d => piece.orientable(d) == orientable(d)), o)
    }
  }

}
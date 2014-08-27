package dideco

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class OrientablePieceTest extends FlatSpec{

  import OrientablePiece._

  "The cache of orientables" should "contain 24 instances" in{
    val cache = OrientablePiece.orientableCache
    assert( cache.size == 24 )
  }
}
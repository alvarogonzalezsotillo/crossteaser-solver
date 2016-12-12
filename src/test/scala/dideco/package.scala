import scala.concurrent.{Await, Future}
import scala.util.Random



/**
  * Created by alvaro on 2/12/16.
  */
package object dideco {

  def createRandom(): Random = new Random(0)

}
package dideco

import java.util.logging.LogManager

/**
  * Created by alvaro on 7/12/16.
  */
object Logging {

  def configure() = {
    val in = getClass.getResourceAsStream("/java-logging.properties")
    LogManager.getLogManager.readConfiguration(in)
    in.close()
  }
}

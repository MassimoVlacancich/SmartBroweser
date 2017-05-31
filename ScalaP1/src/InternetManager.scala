import java.io.IOException
import java.net.{MalformedURLException, URL, URLConnection}

/**
  * Created by HP on 31/05/2017.
  */
abstract class InternetManager {

  def checkConnection(): Boolean = {
    try {
      val url: URL = new URL("http://www.google.com");
      val conn : URLConnection = url.openConnection();
      conn.connect();
      return true;
    } catch {
      case ioe: IOException => ioe.printStackTrace(); return false
      case e: MalformedURLException =>  e.printStackTrace(); return false
    }

  }
}

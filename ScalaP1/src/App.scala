import javafx.application.Application

/**
  * Created by HP on 31/05/2017.
  */
object App {

  def main(args: Array[String]) {

    val eMan = new InternetManager{};
    println("the connection is: " + eMan.checkConnection())

    Application.launch(classOf[MainGUI], args: _*)
  }
}

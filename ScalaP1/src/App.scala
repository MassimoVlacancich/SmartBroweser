import javafx.application.Application

object App {

  def main(args: Array[String]) {

    val eMan = new InternetManager{};
    println("the connection is: " + eMan.checkConnection())

    Application.launch(classOf[MainGUI], args: _*)
  }
}

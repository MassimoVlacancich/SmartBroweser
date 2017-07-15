import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Alert.AlertType
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import javafx.scene.control.{Alert, Label}
import javafx.scene.web.WebView

import scalafx.geometry.Rectangle2D
import scalafx.scene.layout.{BorderPane, GridPane, HBox}
import scalafx.stage.Screen

class MainGUI() extends Application{

  private var TITLE:String = "Scala Web Browser"
  private var primStage: Stage = _
  @Override
  override def start(primaryStage: Stage) = {
    primStage = primaryStage;
    launchApp();
  }

  def initGUI(stage: Stage): Unit = {

    var xScreen : Double = 0
    var yScreen : Double = 0
    var widthScreen  : Double = 0
    var heightScreen : Double = 0

    new Theme {
      xScreen = getMinX()
      yScreen = getMinY()

      widthScreen  = getWidth()
      heightScreen = getHeight()
    }

    stage.setTitle(TITLE)
    //set screen to fit
    var screenSize : Rectangle2D = Screen.primary.visualBounds
    stage.setX(xScreen)
    stage.setY(yScreen)
    stage.setWidth(widthScreen)
    stage.setHeight(heightScreen)

    val mainPanel : BorderPane = new BorderPane()

    val scene : Scene = new Scene(mainPanel, 900, 600)

    val browser = new Browser()
    browser.setURL("http://www.google.com")
    val view = browser.getWebView()

    val controls:HBox = browser.getControls()

    mainPanel.setTop(controls)
    mainPanel.setCenter(view)

    scene.setRoot(mainPanel)

    stage.setScene(scene)
    stage.show()
  }


  def launchApp() = {
    initGUI(primStage)
  }


}

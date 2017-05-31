import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import javafx.scene.control.Label

class MainGUI() extends Application{

  private var TITLE:String = "Scala Web Browser"

  @Override
  override def start(primaryStage: Stage) = {
    initGUI(primaryStage)
  }

  def initGUI(stage: Stage): Unit ={
    stage.setTitle(TITLE)
    val root = new StackPane
    root.getChildren.add(new Label("Hello world!"))

    stage.setScene(new Scene(root, 300, 300))
    stage.show()
  }



}

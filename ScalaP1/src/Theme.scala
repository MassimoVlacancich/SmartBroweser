import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color

import scalafx.geometry.Rectangle2D
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.stage.Screen

/**
  * Created by HP on 09/06/2017.
  */
abstract class Theme {


  private var minX : Double = 0
  private var minY : Double = 0
  private var screenWidth  : Double = 0
  private var screenHeight : Double = 0

  getScreenVariables()


  def setEffect(element : HBox) : Unit ={

    var shadow:DropShadow = new DropShadow()
    shadow.setOffsetY(3.0);
    shadow.setOffsetX(3.0);
    shadow.setColor(Color.BLACK);

    element.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler[MouseEvent] {
      def handle(event: MouseEvent) {
        element.setEffect(shadow)
      }
    })

    element.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler[MouseEvent] {
      def handle(event: MouseEvent) {
        element.setEffect(null)
      }
    })

  }

  private def getScreenVariables() : Unit= {
    var screenSize : Rectangle2D = Screen.primary.visualBounds
    minX = screenSize.getMinX
    minY = screenSize.getMinY
    screenWidth  = screenSize.getWidth
    screenHeight = screenSize.getHeight
  }

  def getMinX() : Double = {
    return minX
  }

  def getMinY() : Double = {
    return minY
  }

  def getWidth() : Double = {
    return screenWidth
  }

  def getHeight() : Double = {
    return screenHeight
  }


}

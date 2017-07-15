import java.awt.event.{ActionEvent, ActionListener}
import java.util
import java.util._
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.image.ImageView
import javafx.scene.input.{KeyCode, MouseEvent}
import javafx.scene.web.WebView
import javax.swing.{JButton, JDialog}

import scala.collection.mutable
import scalafx.event
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.effect.DropShadow
import scalafx.scene.input.KeyEvent
import scalafx.scene.layout.HBox
import scalafx.scene.web.WebEngine

/**
  * Created by HP on 09/06/2017.
  */
class Browser {

  private val browser    = new WebView()
  private val engine = browser.getEngine()
  private val controls = new HBox()
  private var urlInput: TextField = _

  //internal use variables
  private var currentAddress:String = _

  //sizes
  private var iconsSize : Double = 32
  private var controlsHeight : Double = 100
  private var urlInputField  : Double = 300

  //navigation
  private var backwardStack  = mutable.Stack[String]()
  private var forwardStack   = mutable.Stack[String]()


  //call on create
  println("BROWSER:: new browser initialized")
  initControls()


  def getWebView() : WebView = {
    return browser
  }

  def setURL(urlAddress: String): Unit ={

    var isConnected = false
    new InternetManager { isConnected = checkConnection()}

    if(isConnected){
      println("BROWSER:: valid connection on request for:  " + urlAddress)
      loadURL(urlAddress)
      setURLBar(currentAddress)
      currentAddress = urlAddress
    }else{
      var alert:Alert =new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setHeaderText("Look, an Error Dialog");
      alert.setContentText("Ooops, there was an error!");
      alert.showAndWait();
    }
  }


  def initControls() : Unit ={

    controls.setStyle("-fx-background-color: #ebebe0;")
    controls.setMaxHeight(controlsHeight)
    controls.setAlignment(Pos.CENTER)

    //REFRESH
    val refresh:HBox = new HBox()
    val refreshIcon = new ImageViewResizable("Images/icons/refresh.png")
    refreshIcon.resize(iconsSize, iconsSize)
    refresh.getChildren.add(refreshIcon)


    refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler[MouseEvent] {
      def handle(event: MouseEvent) {
//        if(currentAddress.contains("www")){
//          engine.reload()
//        }
        printStacks()
      }
    })

    new Theme {setEffect(refresh)}

    //BACK
    val back:HBox = new HBox()
    val backIcon = new ImageViewResizable("Images/icons/backward.png")
    backIcon.resize(iconsSize,iconsSize)
    back.getChildren.add(backIcon)


    back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler[MouseEvent] {
      def handle(event: MouseEvent) {
        if(!backwardStack.isEmpty){
          var url = backwardStack.pop()
          forwardStack.push(currentAddress)
          setURL(url)
        }
      }
    })

    new Theme {setEffect(back)}

    //FORWARD
    val forward:HBox = new HBox()
    val forwardIcon = new ImageViewResizable("Images/icons/forward.png")
    forwardIcon.resize(iconsSize, iconsSize)
    forward.getChildren.add(forwardIcon)


    forward.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler[MouseEvent] {
      def handle(event: MouseEvent) {
       if(!forwardStack.isEmpty){
         var url = forwardStack.pop()
         backwardStack.push(url)
         setURL(url)
       }
      }
    })

    new Theme {setEffect(forward)}


    urlInput = new TextField()
    urlInput.setPromptText("Type URL here")
    var urlInputWidth : Double = 0 ; new Theme{urlInputWidth = getWidth()}
    urlInput.setMinWidth(urlInputWidth*0.7)


    urlInput.setOnKeyPressed(event => {
      if (event.getCode() == KeyCode.ENTER) {
        val url = urlInput.getText()
        setURL(url)
      }
    });

    controls.getChildren.addAll(back,forward,refresh, urlInput)

  }

  def getControls() : HBox = {
    return controls
  }


  def loadURL(urlText : String) : Unit ={
    if(urlInput != null){
      if(isValidURL(urlText)){
        backwardStack.push(currentAddress)
        if(!urlText.contains("http://")){
          val newUrl = "http://" + urlText
          currentAddress = newUrl
        }else{
          currentAddress = urlText
        }
        engine.load(currentAddress)
      }
    }
  }

  def printStacks() : Unit = {
    if(!backwardStack.isEmpty){
      println("BROWSER:: backwards stack")
      var a = 0;
      for( a <- 0 to backwardStack.size - 1) {
        println(backwardStack(a))
      }
    }

    if(!forwardStack.isEmpty){
      println("BROWSER:: forwards stack")
      var b = 0;
      for( b <- 0 to forwardStack.size - 1) {
        println(forwardStack(b))
      }
    }
  }

  def setURLBar(urlText : String): Unit = {
    if(urlInput != null){
      if(isValidURL(urlText)){
        urlInput.setText(urlText)
      }
    }
  }

  def isValidURL(urlTest : String) : Boolean = {
    if(urlTest.contains("www")){
      return true
    }else{
      return false
    }
  }


}

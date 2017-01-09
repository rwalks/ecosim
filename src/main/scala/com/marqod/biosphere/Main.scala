package com.marqod.biosphere

import com.marqod.biosphere.engine._
import com.marqod.biosphere.graphics._

import scala.swing._
import scala.swing.BorderPanel.Position._
import event._
import com.marqod.biosphere.utils._

object GameApp extends SimpleSwingApplication with Config {

  var currentTime = System.currentTimeMillis()

  def top = new MainFrame {
    title = "Biosphere Sim"

    val gameEngine = new GameEngine()
    val messenger = new MessageCenter()

    val canvas = new Canvas {
      preferredSize = new Dimension(CANVAS_SIZE.x.toInt, CANVAS_SIZE.y.toInt)
    }

    val graphicsEngine = new GraphicsEngine(gameEngine, canvas)
    canvas.graphicsEngine = graphicsEngine

    contents = new BorderPanel {
      layout(canvas) = Center
    }
    size = new Dimension(WINDOW_SIZE.x.toInt, WINDOW_SIZE.y.toInt)
    /*
    menuBar = new MenuBar {
      contents += new Menu("File") {
        contents += new MenuItem(Action("Exit") {
          sys.exit(0)
        })
      }
    }
   */
    listenTo(canvas.mouse.clicks)
    listenTo(canvas.mouse.wheel)
    listenTo(canvas.keys)

    reactions += {
      case MouseClicked(_, point, _, _, _) => {
        println("CLICKA")
        println(point)
      }
      case KeyPressed(_,ch,_,side) => {
        messenger.keyPress(ch.hashCode())
      }
      case KeyReleased(_,ch,_,side) => {
        messenger.keyRelease(ch.hashCode())
      }
      case MouseWheelMoved(_, point, horiz, vert) => {
        graphicsEngine.scrollEvent(Vector2(point.x, point.y), vert)
      }
    }

      Timer(1000 / FPS) {
          val tempTime = System.currentTimeMillis()
     //   println(tempTime - currentTime)
          currentTime = tempTime
          gameEngine.update(messenger)
          graphicsEngine.draw()
      }

  }
}


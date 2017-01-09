package com.marqod.biosphere.graphics

import com.marqod.biosphere.art.ArtHolder
import com.marqod.biosphere.engine.{Camera, GameEngine}
import com.marqod.biosphere.utils.{Colors, Config, Vector2}

import scala.swing.Graphics2D

/**
  * Created by ryan.walker on 10/29/16.
  */
class GraphicsEngine(engine: GameEngine, canvas: Canvas) extends Config with ArtHolder {

  val camera = new Camera()

  def draw(): Unit = {
    canvas.draw()
  }

  def drawScene(g: Graphics2D) = {
    camera.update(engine.gameState.controlState)
    //water
  //  g.setColor(Colors.brightBlue)
  //  g.fillRect(0,0,CANVAS_SIZE.x.toInt,CANVAS_SIZE.y.toInt)
    //island
    drawTiles(g)
    //entities
    engine.gameState.beasts.foreach { i =>
      unitArt.draw(g,i,camera)
    }

  }

  def scrollEvent(screenPos: Vector2, scroll: Int) = {
    camera.setScroll(screenPos, scroll)
  }

  def drawTiles(g: Graphics2D) = {
    val tsX = TILE_SIZE.x * camera.zoom.zoom
    val tsY = TILE_SIZE.y * camera.zoom.zoom
    val ox: Int = Math.floor(camera.offset.x / tsX).toInt
    val dx: Int = ox + Math.ceil(CANVAS_SIZE.x / tsX).toInt
    val oy: Int = Math.floor(camera.offset.y / tsY).toInt
    val dy: Int = oy + Math.ceil(CANVAS_SIZE.y / tsY).toInt
    ox to dx foreach { x =>
      if ( x < engine.gameState.tiles.length) {
        oy to dy foreach { y =>
          if ( y < engine.gameState.tiles(x).length) {
            tileArt.draw(g, engine.gameState.tiles(x)(y), camera)
          }
        }
      }
    }
  }

}

package com.marqod.biosphere.art

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

import com.marqod.biosphere.models.{Tile, TileState}
import com.marqod.biosphere.utils.{Colors, Config, Vector2}

import scala.swing.Graphics2D

/**
  * Created by ryan.walker on 1/1/17.
  */
class TileArt extends Config {


  val size = TILE_SIZE

  def draw(g: Graphics2D, tile: Tile) = {
    drawClass(g, tile)
  }

  def drawClass(g: Graphics2D, tile: Tile) = {
    val dx = (tile.x * size.x).toInt
    val dy = (tile.y * size.y).toInt
    tile.state match {
      case TileState.water => drawRect(dx,dy,g,waterColor(tile))
      case TileState.grass => drawRect(dx,dy,g,grassColor(tile))
      case TileState.beach => drawRect(dx,dy,g,Colors.brightYellow)
      case _ => drawRect(dx,dy,g,Colors.black)
    }
  }

  def drawRect(dx: Int, dy: Int, g: Graphics2D, color: Color) = {
    g.setColor(color)
    g.fillRect(dx.toInt,dy.toInt,size.x.toInt,size.y.toInt)

  }

  def grassColor(tile: Tile): Color = {
    val rPer = tile.resource.resourceAmount.percentFull()
    val r = 160 - (rPer * 160)
    val g = 82 + (173 * rPer)
    val b = 45 - (rPer * 45)
    new Color(r.toInt, g.toInt, b.toInt)
  }

  def waterColor(tile: Tile): Color = {
    val rPer = tile.resource.resourceAmount.percentFull()
    val r = (rPer * 12)
    val g = (120 * rPer)
    val b = 255 - (rPer * 159)
    new Color(r.toInt, g.toInt, b.toInt)
  }

}

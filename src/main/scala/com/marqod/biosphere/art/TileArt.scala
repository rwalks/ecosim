package com.marqod.biosphere.art

import com.marqod.biosphere.engine.Camera
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
    val dx = tile.x * size.x
    val dy = tile.y * size.y
    val color = tile.state match {
      case TileState.water => Colors.brightBlue
      case TileState.grass => Colors.brightGreen
      case TileState.beach => Colors.brightYellow
      case _ => Colors.black
    }
    g.setColor(color)
    g.fillRect(dx.toInt,dy.toInt,size.x.toInt,size.y.toInt)
  }

}

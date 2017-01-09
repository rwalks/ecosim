package com.marqod.biosphere.art

import com.marqod.biosphere.engine.Camera
import com.marqod.biosphere.models.{Tile, TileState}
import com.marqod.biosphere.utils.{Colors, Config, Vector2}

import scala.swing.Graphics2D

/**
  * Created by ryan.walker on 1/1/17.
  */
class TileArt extends Config {

  def draw(g: Graphics2D, tile: Tile, camera: Camera) = {
    val tsX = TILE_SIZE.x * camera.zoom.zoom
    val tsY = TILE_SIZE.y * camera.zoom.zoom
    val dx = (tile.x * tsX) - camera.offset.x
    val dy = (tile.y * tsY) - camera.offset.y
    val color = tile.state match {
      case TileState.water => Colors.brightBlue
      case TileState.grass => Colors.brightGreen
      case TileState.beach => Colors.brightYellow
      case _ => Colors.black
    }
    g.setColor(color)
    g.fillRect(dx.toInt,dy.toInt,tsX.toInt,tsY.toInt)
  }

}

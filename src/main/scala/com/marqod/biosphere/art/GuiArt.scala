package com.marqod.biosphere.art

import java.awt.Color

import com.marqod.biosphere.engine.Gui
import com.marqod.biosphere.models.{Entity, Tile}
import com.marqod.biosphere.utils.{Colors, Config}

import scala.swing.Graphics2D

/**
  * Created by ryan.walker on 1/25/17.
  */
class GuiArt extends Config {

  val hoverColor: Color = Colors.brightMagenta
  val selectColor: Color = Colors.brightRed

  def draw(g: Graphics2D, gui: Gui) = {
    gui.hoverEntity match {
      case Some(e: Entity) =>
        drawEntitySelect(g, e, hoverColor)
      case None =>
        gui.hoverTile match {
          case Some(t: Tile) =>
            drawTileSelect(g, t, hoverColor)
          case None => {}
        }
    }
    gui.targetEntity match {
      case Some(e: Entity) =>
        drawEntitySelect(g, e, selectColor)
      case None =>
        gui.targetTile match {
          case Some(t: Tile) =>
            drawTileSelect(g, t, selectColor)
          case None => {}
        }
    }
  }

  def drawTileSelect(g: Graphics2D, tile: Tile, color: Color) = {
    g.setColor(color)
    g.drawRect(tile.position.x.toInt,tile.position.y.toInt,TILE_SIZE.x.toInt,TILE_SIZE.y.toInt)
  }

  def drawEntitySelect(g: Graphics2D, entity: Entity, color: Color) = {
    g.setColor(color)
    val pos = entity.topLeft()
    g.drawRect(pos.x.toInt,pos.y.toInt,entity.size.x.toInt,entity.size.y.toInt)
  }

}

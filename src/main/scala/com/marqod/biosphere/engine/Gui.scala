package com.marqod.biosphere.engine

import com.marqod.biosphere.models.{Entity, Tile}
import com.marqod.biosphere.utils.{EntityPosition, Vector2}

/**
  * Created by ryan.walker on 1/25/17.
  */
class Gui() {

  var hoverTile: Option[Tile] = None
  var hoverEntity: Option[Entity] = None
  var targetTile: Option[Tile] = None
  var targetEntity: Option[Entity] = None

  def setHovers(mousePos: Vector2, gameState: GameState, camera: Camera) = {
    val tx = (mousePos.x + camera.offset.x) / camera.zoom.zoom
    val ty = (mousePos.y + camera.offset.y) / camera.zoom.zoom
    val tPos = EntityPosition(tx,ty)
    val tile = gameState.getTile(tPos)
    hoverTile = Some(tile)
    var entSet = false
    tile.entities foreach { case (k: Entity,v: Int) =>
      if (k.pointInsideOf(tPos)) {
        hoverEntity = Some(k)
        entSet = true
      }
    }
    if (!entSet) {
      hoverEntity = None
    }
  }

  def click(clickPos: Vector2) = {
    targetTile = hoverTile
    targetEntity = hoverEntity
  }

}

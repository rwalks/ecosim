package com.marqod.biosphere.engine

import com.marqod.biosphere.utils.{Config, EntityPosition, Vector2}
import com.marqod.biosphere.models.{Fish, _}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by ryan.walker on 10/29/16.
  */
class GameState extends Config {

  val controlState = ControlState()

  val island = new Island()
  val tiles: Array[Array[Tile]] = island.generateTiles()

  var beasts = 1 to 8000 map { _ =>
    new Fish(getWaterSpawn())
  }

  def getTile(pos: EntityPosition): Tile = {
    tiles(Math.floor(pos.x / TILE_SIZE.x).toInt)(Math.floor(pos.y / TILE_SIZE.y).toInt)
  }

  def getWaterSpawn(): EntityPosition = {
    val pos = EntityPosition(
      WORLD_SIZE.x * Math.random(),
      WORLD_SIZE.y * Math.random()
    )
    return { if (getTile(pos).state == TileState.water) pos else getWaterSpawn() }
  }

}


case class ControlState(
                         var up: Boolean = false,
                         var down: Boolean = false,
                         var left: Boolean = false,
                         var right: Boolean = false
                       )

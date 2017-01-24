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
  val tiles: Array[ArrayBuffer[Tile]] = island.generateTiles()

  val fishCount = 400
  var fish = 1 to fishCount map { _ =>
    new Fish(getWaterSpawn())
  }

  val sheepCount = 400
  var sheep = 1 to sheepCount map { _ =>
    new Sheep(getLandSpawn())
  }


  var beasts = fish ++ sheep

  def getTile(pos: EntityPosition): Tile = {
    tiles(Math.floor(pos.x / TILE_SIZE.x).toInt)(Math.floor(pos.y / TILE_SIZE.y).toInt)
  }

  def getTileOpt(x: Int, y: Int): Option[Tile] = {
    tiles.lift(x) match {
      case Some( xRow: ArrayBuffer[Tile] ) => xRow.lift(y)
      case _ => None
    }
  }

  def getWaterSpawn(): EntityPosition = {
    val pos = EntityPosition(
      WORLD_SIZE.x * Math.random(),
      WORLD_SIZE.y * Math.random()
    )
    return { if (getTile(pos).state == TileState.water) pos else getWaterSpawn() }
  }

  def getLandSpawn(): EntityPosition = {
    val pos = EntityPosition(
      WORLD_SIZE.x * Math.random(),
      WORLD_SIZE.y * Math.random()
    )
    return { if (getTile(pos).state != TileState.water) pos else getLandSpawn() }
  }

}


case class ControlState(
                         var up: Boolean = false,
                         var down: Boolean = false,
                         var left: Boolean = false,
                         var right: Boolean = false
                       )

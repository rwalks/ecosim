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

  val f1 = new Fish(EntityPosition(WORLD_SIZE.x/2,WORLD_SIZE.y/2))
  val f2 = new Fish(EntityPosition(420,300))
  //var beasts = List[Entity](f1, f2)
  var beasts = 1 to 20 map { _ =>
    new Fish(EntityPosition(WORLD_SIZE.x/2,WORLD_SIZE.y/2))
  }

  val island = new Island()
  val tiles: Array[Array[Tile]] = island.generateTiles()

}


case class ControlState(
                         var up: Boolean = false,
                         var down: Boolean = false,
                         var left: Boolean = false,
                         var right: Boolean = false
                       )

package com.marqod.biosphere.engine

import com.marqod.biosphere.utils.{Config, EntityPosition, Vector2}
import com.marqod.biosphere.models._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by ryan.walker on 10/29/16.
  */
class GameState extends Config {

  val controlState = ControlState()

  var beasts = List[Entity]()

  val island = new Island()
  val tiles: Array[Array[Tile]] = island.generateTiles()

  def getDrawTargets(offset: Vector2): List[Entity] = {
    beasts
  }

}


case class ControlState(
                         var up: Boolean = false,
                         var down: Boolean = false,
                         var left: Boolean = false,
                         var right: Boolean = false
                       )

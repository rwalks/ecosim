package com.marqod.biosphere.models

import com.marqod.biosphere.engine.GameState
import com.marqod.biosphere.utils.{Gauge, Vector2}

/**
  * Created by ryan.walker on 1/20/17.
  */
abstract class TileResource {
  val resource: ResourceType.Value
  val maxResource: Double
  val resourceRegen: Double
  val resourceAmount: Gauge

  val node: Boolean = Math.random() < 0.5

  def update(x: Int, y: Int, gameState: GameState) = {
    if (!resourceAmount.empty()) {
      if (node && resourceAmount.full()) {
        spread(x, y, gameState)
      }
      resourceAmount.step()
    }
  }

  def spread(x: Int, y: Int, gameState: GameState) = {
    val dir = TileDirections.getRandom()
    gameState.getTileOpt(x + dir.x.toInt, y + dir.y.toInt) match {
      case Some(t: Tile) => {
        if (t.resource.resource == resource && t.resource.resourceAmount.empty()) {
          t.resource.resourceAmount.step()
        } else {
          false
        }
      }
      case _ => false
    }
  }
}

case class AlgaeResource() extends TileResource {
  val resource = ResourceType.algae
  val maxResource = if (node) 100.0 else 20.0 + Math.random() * 60
  val resourceRegen = 0.01
  val resourceAmount = new Gauge(0,0,maxResource,resourceRegen)
}

case class GrassResource() extends TileResource {
  val resource = ResourceType.grass
  val maxResource = if (node) 100.0 else 20.0 + Math.random() * 60
  val resourceRegen = 0.01
  val resourceAmount = new Gauge(0,0,maxResource,resourceRegen)
}

case class NoResource() extends TileResource {
  val resource = ResourceType.none
  val maxResource = 0.0
  val resourceRegen = 0.0
  val resourceAmount = new Gauge(1,0,maxResource,resourceRegen)
}

object ResourceType extends Enumeration {
  val algae, none, grass = Value
}

object TileDirections {
  val directions: List[Vector2] = List(
    Vector2(-1,-1),Vector2(-1,0),Vector2(-1,1),
    Vector2(0,-1),Vector2(0,1),
    Vector2(1,-1),Vector2(1,0),Vector2(1,1)
  )

  def getRandom(): Vector2 = {
    return directions(Math.floor(Math.random() * directions.length).toInt)
  }
}

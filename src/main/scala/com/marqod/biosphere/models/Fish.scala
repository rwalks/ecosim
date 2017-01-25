package com.marqod.biosphere.models

import com.marqod.biosphere.engine.GameState
import com.marqod.biosphere.utils.{CapVector, EntityPosition, EntityRotation, Gauge}

/**
  * Created by ryan.walker on 1/8/17.
  */
class Fish(pos: EntityPosition) extends WaterEntity(pos) {
  val maxSpeed = 1.0
  val velocity: CapVector = new CapVector(0,0,0,maxSpeed)

  val food = ResourceType.algae
  val energyCost = 0.1
  val restingEnergy: Double = 0.01

  val targetRange = 200

  def setTarget(gameState: GameState) = {
    target.set(
      position.x + (-targetRange/2 + (Math.random() * targetRange)),
      position.y + (-targetRange/2 + (Math.random() * targetRange))
    )
  }
}

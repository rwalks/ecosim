package com.marqod.biosphere.models

import com.marqod.biosphere.engine.GameState
import com.marqod.biosphere.utils.{CapVector, EntityPosition, EntityRotation, Vector2}

/**
  * Created by ryan.walker on 10/29/16.
  */
abstract class Entity(val position: EntityPosition) {
  val maxSpeed: Double
  val rotation: EntityRotation = new EntityRotation(0)
  val velocity: CapVector

  def update(gameState: GameState): Boolean

  def setVelocity(dx: Double, dy: Double) = {
    val scaler = (Math.abs(dx) + Math.abs(dy)) / maxSpeed
    if ( scaler > 1) {
      this.velocity.set(dx / scaler, dy / scaler)
    } else {
      this.velocity.set(dx,dy)
    }
  }
}

case class EntityReturn(alive: Boolean)


package com.marqod.biosphere.models

import com.marqod.biosphere.engine.GameState
import com.marqod.biosphere.utils._

/**
  * Created by ryan.walker on 10/29/16.
  */
abstract class Entity(val position: EntityPosition) {
  val maxSpeed: Double
  val rotation: EntityRotation = new EntityRotation(0)
  val velocity: CapVector
  val target = EntityPosition(0,0)
  var hasMission: Boolean = false
  val minTargetD = 1

  val energyCost: Double
  val food: ResourceType.Value
  val hunger: Gauge
  val lastFood: EntityPosition = new EntityPosition(position.x,position.y)
  val hungerThreshold: Double = 0.75
  val eatAmount: Double = 6.0
  val restingEnergy: Double = 0.1

  def update(gameState: GameState): Boolean = {
    if ( hasMission == true) {
      if (position.distance(target) <= minTargetD) {
        hasMission = false
      } else {
        val dx = this.target.x - this.position.x
        val dy = this.target.y - this.position.y
        setVelocity(dx, dy)
      }
    } else {
      if (hunger.percentFull() < hungerThreshold) {
        eat(gameState)
      } else {
        setTarget(gameState)
      }
      if (validTarget(gameState)) {
        hasMission = true
      }
    }
    energyToMove()
    isAlive()
  }

  def isAlive(): Boolean = {
    !hunger.empty()
  }

  def energyToMove() = {
    val energyRec = Math.max(restingEnergy, velocity.magnitude() * energyCost)
    if (hunger.drain(energyRec)) {
      position.move(velocity)
    }
  }

  def eat(gameState: GameState) = {
    val tile = gameState.getTile(position)
    if (tile.resource.resource == food) {
      if (tile.resource.resourceAmount.drain(eatAmount)) {
        hunger.fill(eatAmount)
        lastFood.set(position)
      }
    } else {
     // if (target.magnitude() != 0) {
     //   target.set(lastFood)
    //  } else {
        setTarget(gameState)
    //  }
    }
  }

  def setTarget(gameState: GameState): Any

  def validTarget(gameState: GameState): Boolean

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


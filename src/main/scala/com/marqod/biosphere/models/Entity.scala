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
  val target = EntityPosition(position.x,position.y)
  var hasMission: Boolean = false
  val minTargetD = 1

  var count = (Math.random() * 100).toInt

  val energyCost: Double
  val food: ResourceType.Value
  val hunger = new Gauge(100,0,100,0)
  val eatThreshold: Double = 0.9
  val eatAmount: Double = 20.0
  val eatFactor: Double = 0.5
  val restingEnergy: Double

  def update(gameState: GameState): Boolean = {
    count += 1
    findFollowTarget(gameState)
    eat(gameState)
    if (validTarget(gameState)) {
      hasMission = true
    } else {
      hasMission = false
    }
    energyToMove()
    isAlive()
  }

  def findFollowTarget(gameState: GameState) = {
    if ( hasMission == true) {
      if (position.distance(target) <= minTargetD) {
        setTarget(gameState)
      } else {
        val dx = this.target.x - this.position.x
        val dy = this.target.y - this.position.y
        setVelocity(dx, dy)
      }
    } else {
      setTarget(gameState)
    }
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
    if (hunger.percentFull() < eatThreshold) {
      val tile = gameState.getTile(position)
      if (tile.resource.resource == food && tile.resource.resourceAmount.drain(eatAmount)) {
        hunger.fill(eatAmount * eatFactor)
        target.set(position)
      }
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


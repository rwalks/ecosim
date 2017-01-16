package com.marqod.biosphere.models

import com.marqod.biosphere.engine.GameState
import com.marqod.biosphere.utils.{CapVector, EntityPosition, EntityRotation}

/**
  * Created by ryan.walker on 1/8/17.
  */
class Fish(pos: EntityPosition) extends Entity(pos) {
  val maxSpeed = 4.0
  val target = EntityPosition(0,0)
  var hasMission: Boolean = false

  val targetRange = 10
  val minTargetD = 1

  def update(gameState: GameState): Boolean = {
    if ( hasMission == true) {
      if (position.distance(target) <= minTargetD) {
        hasMission = false
      } else {
        val dx = this.target.x - this.position.x
        val dy = this.target.y - this.position.y
        this.setVelocity(dx, dy)
      }
    } else {
      target.set(
        position.x + (Math.random() * targetRange),
        position.y + (Math.random() * targetRange)
      )
      hasMission = true
    }
    true
  }
}

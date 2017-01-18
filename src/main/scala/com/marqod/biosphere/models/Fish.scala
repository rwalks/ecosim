package com.marqod.biosphere.models

import com.marqod.biosphere.engine.GameState
import com.marqod.biosphere.utils.{CapVector, EntityPosition, EntityRotation}

/**
  * Created by ryan.walker on 1/8/17.
  */
class Fish(pos: EntityPosition) extends Entity(pos) {
  val maxSpeed = 1.0
  val velocity: CapVector = new CapVector(0,0,0,maxSpeed)
  val target = EntityPosition(0,0)
  var hasMission: Boolean = false

  val targetRange = 100
  val minTargetD = 1

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
      target.set(
        position.x + (-targetRange/2 + (Math.random() * targetRange)),
        position.y + (-targetRange/2 + (Math.random() * targetRange))
      )
      hasMission = true
    }
    //println(this.velocity.x + " : " + this.velocity.y)
    position.move(velocity)
    true
  }
}

package com.marqod.biosphere.models

import com.marqod.biosphere.utils.{EntityPosition, EntityRotation}

/**
  * Created by ryan.walker on 1/8/17.
  */
class Fish(pos: EntityPosition) extends Entity(pos) {

  val rotation = EntityRotation(0)

  def update(): Boolean = {
    true
  }
}

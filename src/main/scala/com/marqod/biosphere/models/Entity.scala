package com.marqod.biosphere.models

import com.marqod.biosphere.utils.{EntityPosition, EntityRotation, Vector2}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by ryan.walker on 10/29/16.
  */
abstract class Entity(val position: EntityPosition) {
  val rotation: EntityRotation
  val velocity: Vector2 = new Vector2(1,0)

  def update(): Boolean
}

case class EntityReturn(alive: Boolean)


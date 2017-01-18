package com.marqod.biosphere.art

import com.marqod.biosphere.engine.Camera
import com.marqod.biosphere.models.Entity
import com.marqod.biosphere.utils.Vector2

import scala.swing.Graphics2D

/**
  * Created by ryan.walker on 11/12/16.
  */
abstract class Art[T <: Entity] {

  val size: Vector2

  def draw(g: Graphics2D, entity: T) = {
    drawClass(g, entity)
  }

  def drawClass(g: Graphics2D, entity: T): Unit

}

trait ArtHolder {
  val unitArt = new UnitArt()
  val tileArt = new TileArt()
}

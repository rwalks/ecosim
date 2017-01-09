package com.marqod.biosphere.art

import com.marqod.biosphere.engine.Camera
import com.marqod.biosphere.models.Entity
import com.marqod.biosphere.utils.Vector2

import scala.swing.Graphics2D

/**
  * Created by ryan.walker on 11/12/16.
  */
abstract class Art[T <: Entity] {

  def draw(g: Graphics2D, entity: T, camera: Camera) = {
    val dX = entity.position.x - camera.offset.x
    val dY = entity.position.y - camera.offset.y

    val gCon: Graphics2D = g.create().asInstanceOf[Graphics2D]
    gCon.translate(dX,dY)
    drawClass(gCon, entity)
    gCon.dispose()
  }

  def drawClass(g: Graphics2D, entity: T): Unit

}

trait ArtHolder {
  val unitArt = new UnitArt()
  val tileArt = new TileArt()
}

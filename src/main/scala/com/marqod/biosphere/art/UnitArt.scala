package com.marqod.biosphere.art

import com.marqod.biosphere.models.{Entity}
import com.marqod.biosphere.engine.Camera
import com.marqod.biosphere.utils.{Colors, Config, Vector2}

import scala.swing.Graphics2D

/**
  * Created by ryan.walker on 11/12/16.
  */
class UnitArt extends Art[Entity] with Config {

  val size = Vector2(6,6)

  def drawClass(g: Graphics2D, p: Entity) = {
    val dX = p.position.x
    val dY = p.position.y
    g.setColor(Colors.red)
    g.fillRect(dX.toInt,dY.toInt,size.x.toInt,size.y.toInt)
  }

}

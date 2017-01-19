package com.marqod.biosphere.art

import com.marqod.biosphere.models.{Entity}
import com.marqod.biosphere.engine.Camera
import com.marqod.biosphere.utils.{Colors, Config, Vector2}
import java.awt.Color

import scala.swing.Graphics2D

/**
  * Created by ryan.walker on 11/12/16.
  */
abstract class UnitArt extends Art[Entity] with Config {

  val size: Vector2
  val color: Color

  def drawClass(g: Graphics2D, p: Entity) = {
    val dX = p.position.x
    val dY = p.position.y
    g.setColor(color)
    g.fillRect(dX.toInt,dY.toInt,size.x.toInt,size.y.toInt)
  }

}

class FishArt extends UnitArt {
  val size: Vector2 = Vector2(6,6)
  val color: Color = Colors.white
}

class SheepArt extends UnitArt {
  val size: Vector2 = Vector2(8,8)
  val color: Color = Colors.red
}

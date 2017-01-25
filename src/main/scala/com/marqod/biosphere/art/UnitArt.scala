package com.marqod.biosphere.art

import java.awt.image.BufferedImage

import com.marqod.biosphere.models.Entity
import com.marqod.biosphere.engine.Camera
import com.marqod.biosphere.utils.{Colors, Config, Vector2}
import java.awt.{Color, Image}
import java.io.File
import javax.imageio.ImageIO

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
  val fishSheet: BufferedImage = ImageIO.read(new File("images/fishAnim3.png"));
  val fishAnim1: Image = fishSheet.getSubimage(0,0,32,32)
  val fishAnim2: Image = fishSheet.getSubimage(0,32,32,32)

  val size: Vector2 = Vector2(6,6)
  val color: Color = Colors.white

  override def drawClass(g: Graphics2D, p: Entity) = {
    val dX = p.position.x
    val dY = p.position.y
    val img = if (p.count % 40 > 20) {
      fishAnim1
    } else {
      fishAnim2
    }
    g.drawImage(img,dX.toInt,dY.toInt,16,16,null)
  }
}

class SheepArt extends UnitArt {
  val sheepImage: Image = ImageIO.read(new File("images/sheep2.png"));

  val sheepSheet: BufferedImage = ImageIO.read(new File("images/sheepSheet1.png"));
  val sheepAnim1: Image = sheepSheet.getSubimage(0,0,16,16)
  val sheepAnim2: Image = sheepSheet.getSubimage(0,16,16,16)
  val sheepAnim3: Image = sheepSheet.getSubimage(0,32,16,16)
  val size: Vector2 = Vector2(8,8)
  val color: Color = Colors.red

  override def drawClass(g: Graphics2D, p: Entity) = {
    val dX = p.position.x
    val dY = p.position.y
    val f = p.count % 60
    val img = if (f < 20) {
      sheepAnim1
    } else if (f < 40) {
      sheepAnim2
    } else {
      sheepAnim3
    }
    g.drawImage(img,dX.toInt,dY.toInt,16,16,null)
  }
}

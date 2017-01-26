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
  def drawClass(g: Graphics2D, p: Entity)
}

class FishArt extends UnitArt {
  val fishSheet: BufferedImage = ImageIO.read(new File("images/fishAnim3.png"));
  val fishAnim1: Image = fishSheet.getSubimage(0,0,32,32)
  val fishAnim2: Image = fishSheet.getSubimage(0,32,32,32)

  def drawClass(g: Graphics2D, p: Entity) = {
    val topLeft = p.topLeft()
    val dX = topLeft.x.toInt
    val dY = topLeft.y.toInt
    val img = if (p.count % 40 > 20) {
      fishAnim1
    } else {
      fishAnim2
    }
    g.drawImage(img,dX,dY,p.size.x.toInt,p.size.y.toInt,null)
  }
}

class SheepArt extends UnitArt {
  val sheepImage: Image = ImageIO.read(new File("images/sheep2.png"));

  val sheepSheet: BufferedImage = ImageIO.read(new File("images/sheepSheet1.png"));
  val sheepAnim1: Image = sheepSheet.getSubimage(0,0,16,16)
  val sheepAnim2: Image = sheepSheet.getSubimage(0,16,16,16)
  val sheepAnim3: Image = sheepSheet.getSubimage(0,32,16,16)

  def drawClass(g: Graphics2D, p: Entity) = {
    val topLeft = p.topLeft()
    val dX = topLeft.x
    val dY = topLeft.y
    val f = p.count % 60
    val img = if (f < 20) {
      sheepAnim1
    } else if (f < 40) {
      sheepAnim2
    } else {
      sheepAnim3
    }
    g.drawImage(img,dX.toInt,dY.toInt,p.size.x.toInt,p.size.y.toInt,null)
  }
}

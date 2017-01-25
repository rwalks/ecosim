package com.marqod.biosphere.art

import java.awt.{Color, Image}
import java.io.File
import javax.imageio.ImageIO

import com.marqod.biosphere.models.{Tile, TileState}
import com.marqod.biosphere.utils.{Colors, Config, Vector2}

import scala.swing.Graphics2D

/**
  * Created by ryan.walker on 1/1/17.
  */
class TileArt extends Config {


  val sandImage: Image = ImageIO.read(new File("images/sandTile.png"));
  val dirtImage: Image = ImageIO.read(new File("images/grassTile/dirtTile.png"))
  val grass1Image: Image = ImageIO.read(new File("images/grassTile/grassTile1.png"));
  val grass2Image: Image = ImageIO.read(new File("images/grassTile/grassTile2.png"));
  val grass3Image: Image = ImageIO.read(new File("images/grassTile/grassTile3.png"));
  val grass4Image: Image = ImageIO.read(new File("images/grassTile/grassTile4.png"));
  val grass5Image: Image = ImageIO.read(new File("images/grassTile/grassTile5.png"));
  val grass6Image: Image = ImageIO.read(new File("images/grassTile/grassTile6.png"));
  val grass7Image: Image = ImageIO.read(new File("images/grassTile/grassTile7.png"));
  val grass8Image: Image = ImageIO.read(new File("images/grassTile/grassTile8.png"));
  val grass9Image: Image = ImageIO.read(new File("images/grassTile/grassTile9.png"));
  val grass10Image: Image = ImageIO.read(new File("images/grassTile/grassTile10.png"));
  val grassImages = Seq(dirtImage, grass1Image, grass2Image, grass3Image, grass4Image, grass5Image, grass6Image, grass7Image, grass8Image, grass9Image, grass10Image)
  val size = TILE_SIZE

  def draw(g: Graphics2D, tile: Tile) = {
    drawClass(g, tile)
  }

  def drawClass(g: Graphics2D, tile: Tile) = {
    val dx = (tile.x * size.x).toInt
    val dy = (tile.y * size.y).toInt
    tile.state match {
      case TileState.water => drawRect(dx,dy,g,waterColor(tile))
      case TileState.grass => drawGrass(dx,dy,g, tile)
      case TileState.beach => drawImage(dx,dy,g,sandImage)//drawRect(dx,dy,g,Colors.brightYellow)
      case _ => drawRect(dx,dy,g,Colors.black)
    }
  }

  def drawGrass(dX:Int, dY: Int, g: Graphics2D, tile: Tile) = {
    val gPercent = tile.resource.resourceAmount.current / 100
    val gIndex = Math.floor(gPercent * 10).toInt
    val img = grassImages(gIndex)
    drawImage(dX, dY, g, img)

  }

  def drawRect(dx: Int, dy: Int, g: Graphics2D, color: Color) = {
    g.setColor(color)
    g.fillRect(dx,dy,size.x.toInt,size.y.toInt)
  }

  def drawImage(dX: Int, dY: Int, g: Graphics2D, img: Image) = {
    g.drawImage(img,dX,dY,size.x.toInt,size.y.toInt,null)
  }

  def waterColor(tile: Tile): Color = {
    val rPer = tile.resource.resourceAmount.current / 100
    val r = (rPer * 12)
    val g = (120 * rPer)
    val b = 255 - (rPer * 159)
    new Color(r.toInt, g.toInt, b.toInt)
  }

}

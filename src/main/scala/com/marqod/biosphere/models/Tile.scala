package com.marqod.biosphere.models

import com.marqod.biosphere.engine.GameState

/**
  * Created by ryan.walker on 1/1/17.
  */
abstract class Tile(val x: Int, val y: Int) {

  val state: TileState.Value
  val resource: TileResource

  def update(gameState: GameState) = {
    resource.update(x,y,gameState)
  }


}

case class BeachTile(tx: Int, ty: Int) extends Tile(tx, ty) {
  val state = TileState.beach
  val resource = NoResource()
  //skip all updates for no resource
  override def update(gameState: GameState) = { }

}

case class GrassTile(tx: Int, ty: Int) extends Tile(tx, ty) {
  val state = TileState.grass
  val resource = GrassResource()
}

case class WaterTile(tx: Int, ty: Int) extends Tile(tx, ty) {
  val state = TileState.water
  val resource = AlgaeResource()
}

object TileState extends Enumeration {
  val water, beach, grass, rock = Value
}

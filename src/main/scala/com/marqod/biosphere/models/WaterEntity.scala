package com.marqod.biosphere.models

import com.marqod.biosphere.engine.GameState
import com.marqod.biosphere.utils.{CapVector, EntityPosition, EntityRotation, Vector2}

/**
  * Created by ryan.walker on 10/29/16.
  */
abstract class WaterEntity(pos: EntityPosition) extends Entity(pos) {

  def validTarget(gameState: GameState): Boolean = {
    gameState.getTile(target).state == TileState.water
  }

}



package com.marqod.biosphere.models

import com.marqod.biosphere.utils.Vector2

/**
  * Created by ryan.walker on 1/1/17.
  */
class Tile(val x: Int, val y: Int, var state: TileState.Value) {

}

object TileState extends Enumeration {
  val water, beach, grass, rock = Value
}

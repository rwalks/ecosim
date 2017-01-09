package com.marqod.biosphere.models

import com.marqod.biosphere.utils.Config

/**
  * Created by ryan.walker on 1/1/17.
  */
class Island extends Config{

  val islandBaseSize = 40
  val maxDepth = 200

  def generateTiles(): Array[Array[Tile]] = {
    val tx = WORLD_SIZE.x / TILE_SIZE.x
    val ty = WORLD_SIZE.y / TILE_SIZE.y

    generateLand(
      (0 to tx.toInt map { x =>
      (0 to ty.toInt map { y =>
        new Tile(x,y,TileState.water)
      }).toArray[Tile]
    }).toArray[Array[Tile]])

  }

  def generateLand(tiles: Array[Array[Tile]]): Array[Array[Tile]] = {
    val midX = Math.floor(tiles.length / 2).toInt
    val midY = Math.floor(tiles(0).length / 2).toInt

    val islandBaseRad = islandBaseSize / 2
/*
    (midX - islandBaseRad).toInt to (midX + islandBaseRad).toInt foreach { x =>
      (midY - islandBaseRad).toInt to (midY + islandBaseRad).toInt foreach { y =>
        tiles(x)(y).state = TileState.grass
      }
    }
    */
    recurseLand(midX, midY, 0, tiles)

    paintBeach(tiles)
  }

  def recurseLand(x: Int, y: Int, depth: Int, tiles: Array[Array[Tile]]): Boolean = {
    if (x < 0 || x >= tiles.length || y < 0 || y >= tiles(0).length) { return true }
    if (tiles(x)(y).state != TileState.water) { return true }
    if (depth > maxDepth) { return true }
    var prob = 1d
    if (depth == 0) {
      tiles(x)(y).state = TileState.grass
    } else {
      if ( depth > (maxDepth * 0.1)) {
        prob = 0.2 + (Math.random() * 0.8)
      } else {
        prob = Math.random() * 1
      }
    }
    if ( prob > 0.4) {
      tiles(x)(y).state = TileState.grass
      recurseLand(x+1, y, depth+1, tiles)
      recurseLand(x-1, y, depth+1, tiles)
      recurseLand(x, y+1, depth+1, tiles)
      recurseLand(x, y-1, depth+1, tiles)
      recurseLand(x+1, y+1, depth+2, tiles)
      recurseLand(x-1, y+1, depth+2, tiles)
      recurseLand(x+1, y-1, depth+2, tiles)
      recurseLand(x-1, y-1, depth+2, tiles)
    }
    true
  }

  def paintBeach(tiles: Array[Array[Tile]]): Array[Array[Tile]] = {
    0 to tiles.length-1 foreach { x =>
      0 to tiles(x).length-1 foreach { y =>
        if (isBeach(x,y,tiles)) {
          tiles(x)(y).state = TileState.beach
        }
      }
    }
    tiles
  }

  def isBeach(x: Int, y: Int, tiles: Array[Array[Tile]]): Boolean = {
    x > 0 && x < (tiles.length - 1) && y > 0 && y < (tiles.length - 1) &&
    tiles(x)(y).state != TileState.water &&
      (
        tiles(x + 0)(y + 1).state == TileState.water ||
        tiles(x + 0)(y - 1).state == TileState.water ||
        tiles(x + 1)(y + 0).state == TileState.water ||
        tiles(x + 1)(y + 1).state == TileState.water ||
        tiles(x + 1)(y - 1).state == TileState.water ||
        tiles(x - 1)(y + 0).state == TileState.water ||
        tiles(x - 1)(y + 1).state == TileState.water ||
        tiles(x - 1)(y - 1).state == TileState.water
        )
  }

}

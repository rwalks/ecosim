package com.marqod.biosphere.models

import com.marqod.biosphere.utils.Config

import scala.collection.mutable.ArrayBuffer

/**
  * Created by ryan.walker on 1/1/17.
  */
class Island extends Config{

  val islandBaseSize = 40
  val maxDepth = 200

  def generateTiles(): Array[ArrayBuffer[Tile]] = {
    val tx = WORLD_SIZE.x / TILE_SIZE.x
    val ty = WORLD_SIZE.y / TILE_SIZE.y

    generateLand(
      (0 to tx.toInt map { x =>
        (0 to ty.toInt map { y =>
        new WaterTile(x,y)
      }).toArray[Tile].to[ArrayBuffer]
    }).toArray[ArrayBuffer[Tile]])

  }

  def generateLand(tiles: Array[ArrayBuffer[Tile]]): Array[ArrayBuffer[Tile]] = {
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
    //resource seeding
    val algaeSeeds = 10
    spreadAlgae(tiles, algaeSeeds)
    val grassSeeds = 10
    spreadGrass(tiles, grassSeeds)
    //
    tiles
  }

  def recurseLand(x: Int, y: Int, depth: Int, tiles: Array[ArrayBuffer[Tile]]): Boolean = {
    if (x < 0 || x >= tiles.length || y < 0 || y >= tiles(0).length) { return true }
    if (tiles(x)(y).state != TileState.water) { return true }
    if (depth > maxDepth) { return true }
    var prob = 1d
    if (depth == 0) {
      tiles(x)(y) = GrassTile(x,y)
    } else {
      if ( depth > (maxDepth * 0.1)) {
        prob = 0.2 + (Math.random() * 0.8)
      } else {
        prob = Math.random() * 1
      }
    }
    if ( prob > 0.4) {
      tiles(x)(y) = GrassTile(x,y)
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

  def paintBeach(tiles: Array[ArrayBuffer[Tile]]): Array[ArrayBuffer[Tile]] = {
    0 to tiles.length-1 foreach { x =>
      0 to tiles(x).length-1 foreach { y =>
        if (isBeach(x,y,tiles)) {
          tiles(x)(y) = BeachTile(x,y)
        }
      }
    }
    tiles
  }

  def randomTile(tiles: Array[ArrayBuffer[Tile]]): Tile = {
    val tx = (Math.random() * (WORLD_SIZE.x / TILE_SIZE.x)).toInt
    val ty = (Math.random() * (WORLD_SIZE.y / TILE_SIZE.y)).toInt
    tiles(tx)(ty)
  }

  def spreadAlgae(tiles: Array[ArrayBuffer[Tile]], count: Int): Unit = {
    val t = randomTile(tiles)
    if (count <= 0) {
      return
    } else if (t.resource.resource == ResourceType.algae && t.resource.resourceAmount.empty()) {
      t.resource.resourceAmount.step()
      spreadAlgae(tiles, count - 1)
    } else {
      spreadAlgae(tiles, count)
    }
  }

  def spreadGrass(tiles: Array[ArrayBuffer[Tile]], count: Int): Unit = {
    val t = randomTile(tiles)
    if (count <= 0){
      return
    } else if (t.resource.resource == ResourceType.grass && t.resource.resourceAmount.empty()) {
      t.resource.resourceAmount.step()
      spreadGrass(tiles, count - 1)
    } else {
      spreadGrass(tiles, count)
    }
  }

  def isBeach(x: Int, y: Int, tiles: Array[ArrayBuffer[Tile]]): Boolean = {
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

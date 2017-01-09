package com.marqod.biosphere.utils

trait Config {
  val WINDOW_SIZE: Vector2 = Vector2(800,600)
  val CANVAS_SIZE: Vector2 = Vector2(800,600)
  val WORLD_SIZE: Vector2 = Vector2(2000,2000)
  val FPS: Int = 60

  val TILE_SIZE = Vector2(20,20)
  val CAMERA_SPEED = Vector2(2,2)
}

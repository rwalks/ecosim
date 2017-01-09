package com.marqod.biosphere.engine

import com.marqod.biosphere.utils.{CameraPosition, CameraZoom, Config, Vector2}

/**
  * Created by ryan.walker on 1/1/17.
  */
class Camera extends Config{

  val offset: CameraPosition = CameraPosition(WORLD_SIZE.x/2,WORLD_SIZE.y/2)

  val zoom: CameraZoom = CameraZoom(1.0)

  def update(controls: ControlState) = {
    val dx = ((controls.left match { case true => 1; case false => 0 }) * -CAMERA_SPEED.x) +
             ((controls.right match { case true => 1; case false => 0 }) * CAMERA_SPEED.x)
    val dy = ((controls.up match { case true => 1; case false => 0 }) * -CAMERA_SPEED.y) +
              ((controls.down match { case true => 1; case false => 0 }) * CAMERA_SPEED.y)
    offset.move(dx,dy)

  }

  def setScroll(point: Vector2, dir: Int) = {
    dir match {
      case 1 => zoom.update(0.01)
      case _ => zoom.update(-0.01)
    }
    offset.updateZoom(zoom)
  }



}

package com.marqod.biosphere.utils

class Vector2(var x: Double, var y: Double) {

  def set(tx: Double, ty: Double): Vector2 = {
    x = tx
    y = ty
    this
  }

  def set(target: Vector2): Vector2 = {
    this.set(target.x, target.y)
    this
  }

  def move(dx: Double, dy: Double): Vector2 = {
    this.set(dx+x,dy+y)
    this
  }

  def move(v2: Vector2): Vector2 = {
    set(x+v2.x, y+v2.y)
    this
  }

  def subtract(v2: Vector2): Vector2 = {
    set(x - v2.x, y - v2.y)
    this
  }

  def magnitude(): Double = {
    Math.abs(x) + Math.abs(y)
  }

  def rotate(theta: Double): Vector2 = {
    val cos = Math.cos(theta);
    val sin = Math.sin(theta);
    val ox = x
    val oy = y
    x = cos * ox - sin * oy
    y = sin * ox + cos * oy
    this
  }

  def scale(d: Double): Vector2 = {
    set(x * d, y * d)
  }

  override def clone(): Vector2 = {
    new Vector2(x,y)
  }

  def distance(ox: Double, oy: Double): Double = {
    Math.sqrt(Math.pow((ox - x),2)+Math.pow((oy - y),2))
  }

  def distance(v2: Vector2): Double = {
    Math.sqrt(Math.pow((v2.x - x),2)+Math.pow((v2.y - y),2))
  }
}

object Vector2 {
  def apply(x: Double, y: Double): Vector2 = {
    new Vector2(x,y)
  }
}

class EntityPosition(ox: Double, oy: Double) extends Vector2(ox,oy) with Utils with Config {

  override def set(tx: Double, ty: Double): Vector2 = {
    x = clamp(tx, 0, WORLD_SIZE.x)
    y = clamp(ty, 0, WORLD_SIZE.y)
    this
  }

  def onBorder(): Boolean = {
    x <= 0 || x >= WORLD_SIZE.x || y <= 0 || y >= WORLD_SIZE.y
  }

  override def clone(): EntityPosition = {
    new EntityPosition(x,y)
  }

}

object EntityPosition {
  def apply(x: Double, y: Double): EntityPosition = {
    new EntityPosition(x,y)
  }
}

case class CameraPosition(ox: Double, oy: Double) extends EntityPosition(ox, oy) {

  var zoom: Double = 1d

  override def set(tx: Double, ty: Double): Vector2 = {
    val zFactor = 1 + (1 - zoom)
    x = clamp(tx, 0, WORLD_SIZE.x - (CANVAS_SIZE.x * zoom))
    y = clamp(ty, 0, WORLD_SIZE.y - (CANVAS_SIZE.y * zoom))
    this
  }

  def updateZoom(camZoom: CameraZoom): Unit = {
    zoom = camZoom.zoom
    this.set(x, y)
  }

}

case class EntityRotation(t: Double) {
  var theta = t

  def set(t: Double): Unit = {
    theta = t
  }

  def rotate(t: Double) = {
    theta += t
    if (theta < 0) {
      theta = Math.PI * 2 + theta
    }
    theta = theta % (Math.PI * 2)
  }
}

object Timer {
  def apply(interval: Int, repeats: Boolean = true)(op: => Unit) {
    val timeOut = new javax.swing.AbstractAction() {
      def actionPerformed(e : java.awt.event.ActionEvent) = op
    }
    val t = new javax.swing.Timer(interval, timeOut)
    t.setRepeats(repeats)
    t.start()
  }
}

trait Utils {
  def clamp(x: Double, min: Double, max: Double): Double = {
    Math.min(max,Math.max(x,min))
  }
}

case class CameraZoom(init: Double = 0.0) extends Utils {
  var zoom: Double = init

  def update(d: Double) = {
    zoom = clamp(zoom + d, 0.1, 1.0)
  }
}

case class CapVector(vx: Double, vy: Double, min: Double, max: Double) extends Vector2(vx,vy) with Utils {
  override def set(tx: Double, ty: Double): Vector2 = {
    x = clamp(Math.abs(tx), min, max) * (if (tx >= 0) 1 else -1)
    y = clamp(Math.abs(ty), min, max) * (if (ty >= 0) 1 else -1)
    this
  }
}


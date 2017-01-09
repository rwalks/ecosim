package com.marqod.biosphere.engine

import com.marqod.biosphere.utils._

/**
  * Created by ryan.walker on 10/29/16.
  */
class GameEngine extends Config{
  val gameState = new GameState()

  def update(messenger: MessageCenter): Unit = {
    messenger.queue.dequeueAll(_ => true).foreach {
      case msg: InputMessage => setInput(msg)
      case _ =>
    }
    updateEntities()
  }

  def setInput(msg: InputMessage): Unit = {
    msg.input match {
      case ControlInput.UP => gameState.controlState.up = msg.active
      case ControlInput.DOWN => gameState.controlState.down = msg.active
      case ControlInput.LEFT => gameState.controlState.left = msg.active
      case ControlInput.RIGHT => gameState.controlState.right = msg.active
      case _ =>
    }
  }

  def updateEntities() = {
    gameState.beasts = gameState.beasts.filter { a =>
      a.update() == true
    }
  }

}

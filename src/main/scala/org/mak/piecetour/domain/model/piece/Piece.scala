package org.mak.piecetour.domain.model.piece

import org.mak.piecetour.domain.model.board.TilePosition

trait Piece {
  def moveTo(position: TilePosition)
  def setCurrentPoisition(position: TilePosition)
  def startTour()
}

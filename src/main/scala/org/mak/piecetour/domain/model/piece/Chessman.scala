package org.mak.piecetour.domain.model.piece

import org.mak.piecetour.domain.model.board.TilePosition

trait Chessman{
  def updateNextPosition(position: TilePosition)
  def copy(currentPosition: TilePosition): Chessman
  def startTour() : Unit
  def updateVisitedPositions(visitedPosition: TilePosition): Unit
  def isPositionAlreadyVisited(nextRowIndexToOccupy: Int, nextColumnIndexToOccupy: Int): Boolean
  def nextPosition() : TilePosition
  def currentPosition() : TilePosition
  def updateCurrentPosition(tilePosition: TilePosition)
  def listOfMovementRules() : List[MovementRule]
}
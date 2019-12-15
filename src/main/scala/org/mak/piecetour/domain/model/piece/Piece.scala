package org.mak.piecetour.domain.model.piece

import org.mak.piecetour.algo.HeuristicAlgorithm
import org.mak.piecetour.domain.model.board.TilePosition

case class Piece(var currentPosition: TilePosition,
                 var nextPosition: TilePosition,
                 var listOfMovementRules: List[MovementRule],
                 var positionsVisited : Map[Int, (Int,Int)],
                 private val tourAlgorithm: HeuristicAlgorithm){

  def startTour(): Unit = {
    tourAlgorithm.findTourPath(this)
  }

  def updateVisitedPositions(visitedPosition: TilePosition): Unit = {
    positionsVisited = positionsVisited + (visitedPosition.visited -> (visitedPosition.rowIndex,visitedPosition.columnIndex))
  }

  def isPositionAlreadyVisited(nextRowIndexToOccupy: Int, nextColumnIndexToOccupy: Int): Boolean = {
    positionsVisited.values.exists(_ == (nextRowIndexToOccupy,nextColumnIndexToOccupy))
  }
}

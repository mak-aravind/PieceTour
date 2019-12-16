package org.mak.piecetour.domain.model.piece

import com.sun.xml.internal.ws.api.pipe.NextAction
import org.mak.piecetour.algo.HeuristicAlgorithm
import org.mak.piecetour.domain.model.board.TilePosition

case class Piece(var currentPosition: TilePosition,
                 var nextPosition: TilePosition,
                 var listOfMovementRules: List[MovementRule],
                 var positionsVisited : Map[Int, (Int,Int)],
                 private val tourAlgorithm: HeuristicAlgorithm) extends Chessman {

  def startTour(): Unit = {
    tourAlgorithm.findTourPath(this)
  }

  def updateVisitedPositions(visitedPosition: TilePosition): Unit = {
    positionsVisited = positionsVisited + (visitedPosition.visited -> (visitedPosition.rowIndex,visitedPosition.columnIndex))
  }

  def isPositionAlreadyVisited(nextRowIndexToOccupy: Int, nextColumnIndexToOccupy: Int): Boolean = {
    positionsVisited.values.exists(_ == (nextRowIndexToOccupy,nextColumnIndexToOccupy))
  }

  override def updateNextPosition(nextPosition: TilePosition): Unit = this.nextPosition = nextPosition

  override def updateCurrentPosition(currentPosition: TilePosition): Unit = this.currentPosition = currentPosition

  override def copy(currentPosition: TilePosition): Chessman = Piece(currentPosition,nextPosition,listOfMovementRules,positionsVisited,tourAlgorithm)
}

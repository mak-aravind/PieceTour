package org.mak.piecetour.algo

import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{Chessman, MovementRule, Piece}
import org.mak.piecetour.exception.PositionOutOfBoardException

import scala.annotation.tailrec

class HeuristicAlgorithm(val chessBoard: ChessBoard) extends TourAlgorithm {

  override def findTourPath(piece: Chessman): Unit = {
    if (isNextPositionWithinLimits(piece.nextPosition())) {
      chessBoard.move(piece)
    }else{
      throw  new PositionOutOfBoardException
    }
    navigate(piece)
  }

  @tailrec
  final def navigate(piece: Chessman): Unit = {
    val unMarkedTiles = findPossibleUnmarkedTiles(piece)
    if (unMarkedTiles.nonEmpty && piece.currentPosition().visited <= chessBoard.rowSize * chessBoard.columnSize) {
      piece.updateNextPosition(findNextPositionByRank(unMarkedTiles,piece))
      chessBoard.move(piece)
      navigate(piece)
    }
  }

  def findPossibleUnmarkedTiles(piece: Chessman) : List[TilePosition] = {
    val currentPosition = piece.currentPosition()
    val occupiedRowIndex = currentPosition.rowIndex
    val occupiedColumnIndex = currentPosition.columnIndex
    for (movementRule <- piece.listOfMovementRules()
          if canMove(piece, movementRule)) yield
      TilePosition(occupiedRowIndex + movementRule.rowIndex,
        occupiedColumnIndex + movementRule.columnIndex)
  }

  def findNextPositionByRank(unMarkedTiles: List[TilePosition], piece: Chessman): TilePosition = {
    unMarkedTiles.map(tilePosition => tilePosition.copy(rank = findPossibleUnmarkedTiles(piece.copy(tilePosition)).size)).sortWith(_.rank < _.rank).head
  }

  def canMove(piece: Chessman, movementRule: MovementRule): Boolean = {
    val currentPosition = piece.currentPosition()
    val occupiedRowIndex = currentPosition.rowIndex
    val occupiedColumnIndex = currentPosition.columnIndex
    val nextRowIndexToOccupy = occupiedRowIndex + movementRule.rowIndex
    val nextColumnIndexToOccupy = occupiedColumnIndex + movementRule.columnIndex
    val canMove = isNextPositionWithinLimits(TilePosition(nextRowIndexToOccupy,
                                                       nextColumnIndexToOccupy)) &&
                  (nextRowIndexToOccupy,nextColumnIndexToOccupy) != (occupiedRowIndex,occupiedColumnIndex) &&
                  ! piece.isPositionAlreadyVisited(nextRowIndexToOccupy, nextColumnIndexToOccupy)
    canMove
  }

  def isNextPositionWithinLimits(position: TilePosition): Boolean = {
    val bounded = (position.rowIndex >= 0 &&
      position.rowIndex <= chessBoard.rowSize - 1) &&
      (position.columnIndex >= 0 &&
        position.columnIndex <= chessBoard.columnSize - 1)
    bounded
  }
}
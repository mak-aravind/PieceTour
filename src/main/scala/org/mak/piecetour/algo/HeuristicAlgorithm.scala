package org.mak.piecetour.algo

import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{MovementRule, Piece}
import org.mak.piecetour.exception.PositionOutOfBoardException

import scala.annotation.tailrec

trait TourAlgorithm {
    def findTourPath(piece: Piece)
}

class HeuristicAlgorithm(val chessBoard: ChessBoard) extends TourAlgorithm {

  override def findTourPath(piece: Piece): Unit = {
    if (chessBoard.isNextPositionWithinLimits(piece.nextPosition)) {
      chessBoard.move(piece)
    }else{
      throw  new PositionOutOfBoardException
    }
    navigate(piece)
  }

  @tailrec
  final def navigate(piece: Piece): Unit = {
    val unMarkedTiles = findPossibleUnmarkedTiles(piece)
    if (unMarkedTiles.nonEmpty && piece.currentPosition.visited <= chessBoard.rowSize * chessBoard.columnSize) {
      piece.nextPosition = findNextPositionByRank(unMarkedTiles,piece)
      chessBoard.move(piece)
      navigate(piece)
    }
  }

  def findPossibleUnmarkedTiles(piece: Piece) : List[TilePosition] = {
    val currentPosition = piece.currentPosition
    val occupiedRowIndex = currentPosition.rowIndex
    val occupiedColumnIndex = currentPosition.columnIndex
    for (movementRule <- piece.listOfMovementRules
          if canMove(piece, movementRule)) yield
      TilePosition(occupiedRowIndex + movementRule.rowIndex,
        occupiedColumnIndex + movementRule.columnIndex)
  }

  def findNextPositionByRank(unMarkedTiles: List[TilePosition], piece: Piece): TilePosition = {
    unMarkedTiles.foreach(tilePosition => tilePosition.rank = {
      val unMarkedTiles = findPossibleUnmarkedTiles(piece.copy(tilePosition))
      if (unMarkedTiles.isEmpty) 0 else unMarkedTiles.size
    })
    unMarkedTiles.sortWith(_.rank < _.rank).head
  }

  def canMove(piece: Piece, movementRule: MovementRule): Boolean = {
    val currentPosition = piece.currentPosition
    val occupiedRowIndex = currentPosition.rowIndex
    val occupiedColumnIndex = currentPosition.columnIndex
    val nextRowIndexToOccupy = occupiedRowIndex + movementRule.rowIndex
    val nextColumnIndexToOccupy = occupiedColumnIndex + movementRule.columnIndex
    val canMove = chessBoard.isNextPositionWithinLimits(TilePosition(nextRowIndexToOccupy,
                                                       nextColumnIndexToOccupy)) &&
                  (nextRowIndexToOccupy,nextColumnIndexToOccupy) != (occupiedRowIndex,occupiedColumnIndex) &&
                  ! piece.isPositionAlreadyVisited(nextRowIndexToOccupy, nextColumnIndexToOccupy)
    canMove
  }
}
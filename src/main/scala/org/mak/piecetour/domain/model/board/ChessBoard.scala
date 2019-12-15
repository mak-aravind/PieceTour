package org.mak.piecetour.domain.model.board

import org.mak.piecetour.domain.model.piece.Piece

private case class Dimension(rows: Int, columns: Int)

class ChessBoard private (private val dimension: Dimension){

  def rowSize : Int = dimension.rows
  def columnSize: Int = dimension.columns
  var grid: Array[IndexedSeq[TilePosition]] = (for (rowIndex <- 0 until rowSize) yield
                                                for (columnIndex <- 0 until columnSize) yield
                                                  TilePosition(rowIndex,columnIndex)).toArray

  def move(piece: Piece): Unit = {
    val nextRowIndexToOccupy = piece.nextPosition.rowIndex
    val nextColumnIndexToOccupy = piece.nextPosition.columnIndex
    grid = occupy(nextRowIndexToOccupy, nextColumnIndexToOccupy,piece.currentPosition.visited)
    val visitedPosition = grid(nextRowIndexToOccupy)(nextColumnIndexToOccupy)
    piece.currentPosition = visitedPosition
    piece.updateVisitedPositions(visitedPosition)
  }

  private def occupy(nextRowIndexToVisit: Int, nextColumnIndexToVisit: Int, numberOfCellsVisited:Int) = {
    grid.map(row =>
      row.map(tilePosition =>
        if (tilePosition.rowIndex == nextRowIndexToVisit &&
          tilePosition.columnIndex == nextColumnIndexToVisit && tilePosition.visited == 0) {
          tilePosition.copy(visited = numberOfCellsVisited + 1)
        }
        else tilePosition
      )
    )
  }

  def isNextPositionWithinLimits(position: TilePosition): Boolean = {
    val bounded = (position.rowIndex >= 0 &&
      position.rowIndex <= rowSize - 1) &&
      (position.columnIndex >= 0 &&
        position.columnIndex <= columnSize - 1)
    bounded
  }

  def getPrintable(debug: Boolean = false): String = {
    val convertedTourGrid = grid.map(row => row.map(tilePosition => if (debug) tilePosition.toString else tilePosition.visited.toString))
    Tabulator.format(convertedTourGrid, isHeaderNeeded = false)
  }
}

object ChessBoard{
  def apply(gridSize : Int = 100) : ChessBoard = {
    import scala.math.{floor, sqrt}
    val dimension = sqrt(gridSize)
    val squareDimension = dimension - floor(dimension) == 0
    if (dimension < 8 || !squareDimension)
      new ChessBoard(Dimension(8,8))
    else
      new ChessBoard(Dimension(rows=dimension.toInt, columns = dimension.toInt))
  }
}

package org.mak.piecetour.domain.model.board

import org.mak.piecetour.domain.model.piece.Chessman
import scala.math.{sqrt,floor}
class ChessBoard private (private val dimension: Dimension){

  def rowSize : Int = dimension.rows
  def columnSize: Int = dimension.columns
  private var grid: Array[IndexedSeq[TilePosition]] = (for (rowIndex <- 0 until rowSize) yield
                                                        for (columnIndex <- 0 until columnSize) yield
                                                          TilePosition(rowIndex,columnIndex)).toArray

  def move(piece: Chessman): Unit = {
    val nextRowIndexToOccupy = piece.nextPosition().rowIndex
    val nextColumnIndexToOccupy = piece.nextPosition().columnIndex
    grid = occupy(nextRowIndexToOccupy, nextColumnIndexToOccupy,piece.currentPosition().visited)
    val visitedPosition = grid(nextRowIndexToOccupy)(nextColumnIndexToOccupy)
    piece.updateCurrentPosition(visitedPosition)
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

  def getTilePositionAt(rowIndex : Int, columnIndex : Int): TilePosition ={
    grid(rowIndex)(columnIndex)
  }

  def getPrintable(debug: Boolean = false): String = {
    val convertedTourGrid = grid.map(row => row.map(tilePosition => if (debug) tilePosition.toString else tilePosition.visited.toString))
    Tabulator.format(convertedTourGrid, isHeaderNeeded = false)
  }
}

object ChessBoard{
  def apply(gridSize : Int = 64) : ChessBoard = {
    val dimension = sqrt(gridSize)
    val squareDimension = dimension - floor(dimension) == 0
    if (dimension < 8 || !squareDimension)
      new ChessBoard(Dimension(8,8))
    else
      new ChessBoard(Dimension(rows=dimension.toInt, columns = dimension.toInt))
  }
}

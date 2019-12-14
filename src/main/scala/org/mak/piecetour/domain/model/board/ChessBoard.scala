package org.mak.piecetour.domain.model.board

import org.mak.piecetour.domain.model.piece.Piece

private case class Dimension(rows: Int, columns: Int)

class ChessBoard private (private val dimension: Dimension){

  def rowSize : Int = dimension.rows
  def columnSize: Int = dimension.columns
  var grid: Array[IndexedSeq[TilePosition]] = (for (rowIndex <- 0 until rowSize) yield
                                                for (columnIndex <- 0 until columnSize) yield
                                                  TilePosition(rowIndex,columnIndex)).toArray



  def getPrintable(debug: Boolean = false): String = {
    val convertedTourGrid = grid.map(row => row.map(tilePosition => if (debug) tilePosition.toString else tilePosition.visited.toString))
    Tabulator.format(convertedTourGrid, isHeaderNeeded = false)
  }

  def move(chessman: Piece) = {
    val rowIndexToVisit = chessman.getCurrentPosition.rowIndex
    val columnIndexToVisit = chessman.getCurrentPosition.columnIndex
    grid = markVisited(rowIndexToVisit, columnIndexToVisit)
  }

  private def markVisited(rowIndexToHost: Int, columnIndexToHost: Int) = {
    grid.map(row =>
      row.map(tilePosition =>
        if (tilePosition.rowIndex == rowIndexToHost &&
          tilePosition.columnIndex == columnIndexToHost)
          tilePosition.copy(visited = 1)
        else tilePosition
      )
    )
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

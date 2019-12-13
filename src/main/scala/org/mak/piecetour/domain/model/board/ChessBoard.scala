package org.mak.piecetour.domain.model.board

private case class Dimension(rows: Int, columns: Int)

class ChessBoard private (private val dimension: Dimension){
  def rowSize : Int = dimension.rows
  def columnSize: Int = dimension.columns

  var grid: Seq[IndexedSeq[TilePosition]] = for (rowIndex <- 0 until rowSize) yield
                for (columnIndex <- 0 until columnSize) yield
                  new TilePosition(rowIndex,columnIndex)

  def getPrintable(debug: Boolean = false): String = {

    println(grid.head(0))
    println("About to modify grid " + grid.head.updated(0, new TilePosition(10,10,visited = 1)))
    grid = grid.reverse
    //rid(0)(0) = new TilePosition(10,10,visited = 1)
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

package org.mak.piecetour.lab

import org.mak.piecetour.domain.model.board.TilePosition

object TryOuts extends App {

  val rowSize = 2
  val columnSize = 2

  private val grid = Array.ofDim[TilePosition](rowSize,columnSize)

  val reformedGrid = for (rowIndex <- grid.indices)
                          yield (rowIndex,
                                 for (columnIndex <- grid(rowIndex).indices)
                                      yield (columnIndex,
                                              grid(columnIndex)))

  val simpleGrid = for (rowIndex <- 0 until rowSize)
                          yield for (columnIndex <- 0 until columnSize)
                                  yield new TilePosition(rowIndex,columnIndex)

  val reformedGridAsList = simpleGrid.toList
  println(reformedGridAsList.mkString(" "))

  println(simpleGrid(0)(0))

}

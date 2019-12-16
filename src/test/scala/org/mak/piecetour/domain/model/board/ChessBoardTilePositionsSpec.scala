package org.mak.piecetour.domain.model.board

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.prop._
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ChessBoardTilePositionsSpec extends PropSpec with GivenWhenThen with TableDrivenPropertyChecks with Matchers{
  Given("a board of grid size 100 and test positions")
    val chessBoard: ChessBoard = ChessBoard(100)
    val testPositions: TableFor1[(Int, Int)] =
                    Table(
                      "testPositions",
                      (0,0),
                      (5,0),
                      (9,9),
                      (8,4)
                    )
  property("the grid indices should be populated in TilePosition. The rowindex and columnIndex of tile position got from chessboard should match test row Index and test column Index"){
    forAll(testPositions) {testPositions => {
                                                val tilePositionFromChessBoard = chessBoard.getTilePositionAt(testPositions._1,testPositions._2)
                                                assert(tilePositionFromChessBoard.rowIndex == testPositions._1)
                                                assert(tilePositionFromChessBoard.columnIndex == testPositions._2)
                                            }
    }
  }
}

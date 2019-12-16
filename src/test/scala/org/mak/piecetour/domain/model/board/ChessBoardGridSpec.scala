package org.mak.piecetour.domain.model.board

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ChessBoardGridSpec extends FeatureSpec with GivenWhenThen {
  info("The default grid size of a chess board is 8 * 8")
  info("The grid dimension of chessboard will be always square")

  feature("For a square dimension"){
    scenario("For the grid size 100 should have dimension of 10 * 10"){
      Given("a board of grid size 100")
        val chessBoard = ChessBoard(100)
      Then("the dimension should be 10 * 10")
        assert(chessBoard.rowSize == 10 && chessBoard.columnSize == 10)
    }

    scenario("For the grid size 49 less than standard size 64 should have dimension of 8 * 8"){
      Given("a board of grid size 49")
        val chessBoard = ChessBoard(49)
      Then("the dimension should default to 8 * 8")
        assert(chessBoard.rowSize == 8 && chessBoard.columnSize == 8)
    }
  }

  feature("For a non-square dimension"){
    scenario("For the grid size non-square 150 should default to standard size with dimension of 8 * 8"){
      Given("a board of grid size 150")
        val chessBoard = ChessBoard(150)
      Then("the dimension should default to 8 * 8")
        assert(chessBoard.rowSize == 8 && chessBoard.columnSize == 8)
    }
  }
}
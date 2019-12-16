package org.mak.piecetour.domain.model.board

import org.junit.runner.RunWith
import org.mak.piecetour.domain.model.piece.Chessman
import org.mockito.Mockito._
import org.scalatest.{FunSuite, GivenWhenThen}
import org.scalatestplus.junit.JUnitRunner
import org.scalatestplus.mockito.MockitoSugar

@RunWith(classOf[JUnitRunner])
class ChessBoardPieceMovementSpec extends FunSuite with GivenWhenThen with MockitoSugar {
  test("move to a position 5,5 check whether 5,5 is visited") {
        Given("next position as 5,5")
        val mockPiece = mock[Chessman]
        val mockCurrentPosition = mock[TilePosition]

        val chessBoard = ChessBoard()
        val nextPosition = TilePosition(5,5)

        when(mockPiece.nextPosition()).thenReturn(nextPosition)
        when(mockPiece.currentPosition()).thenReturn(mockCurrentPosition)
        assert(chessBoard.getTilePositionAt(5,5).visited == 0)
        When("Move called on chessboard")
            chessBoard.move(mockPiece)
        Then("the position 5,5 will be vistied")
        assert(chessBoard.getTilePositionAt(5,5).visited == 1)
    }
}

package org.mak.piecetour.algo

import org.junit.runner.RunWith
import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{MovementRule, Piece}
import org.scalatest.{BeforeAndAfter, FeatureSpec, GivenWhenThen}
import org.scalatestplus.junit.JUnitRunner
import org.scalatestplus.mockito.MockitoSugar

import scala.collection.immutable.HashMap

@RunWith(classOf[JUnitRunner])
class NavigationSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with MockitoSugar {
  val chessBoard: ChessBoard = ChessBoard(100)
  val heuristicAlgorithm = new HeuristicAlgorithm(chessBoard)
  val positionsVisited: HashMap[Int, (Int, Int)] = HashMap[Int, (Int, Int)]()
  val listOfLegalMoves: List[(String, Int, Int)]= ("N", -3, 0) ::
    ("NW", -2, -2) ::
    ("W", 0, -3) ::
    ("SW", 2, -2) ::
    ("S", 3, 0) ::
    ("SE", 2, 2) ::
    ("E", 0, 3) ::
    ("NE", -2, 2) :: Nil

  feature("A piece starting with a position 2,2 should have 6 possible moves") {
    scenario("get next possible move from position 2,2 by ranking the 6 possible moves") {
      Given("a board of grid size 100")
      val mockNextPosition = TilePosition(3,8)
      val currentPosition = TilePosition(3, 8)
      val piece = Piece(currentPosition = currentPosition,
        nextPosition = mockNextPosition,
        MovementRule.buildRules(listOfLegalMoves),
        positionsVisited,
        heuristicAlgorithm)
      Then("the position next to move will be ranked as 2 provided the position already 2,2 visited")
        piece.startTour()
        assert(piece.currentPosition.visited == 100)
        assert(piece.positionsVisited.size == 100)
    }
  }
}
package org.mak.piecetour.algo

import org.junit.runner.RunWith
import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{MovementRule, Piece}
import org.scalatest.{BeforeAndAfter, FeatureSpec, GivenWhenThen}
import org.scalatestplus.junit.JUnitRunner
import org.scalatestplus.mockito.MockitoSugar

import scala.collection.immutable.HashMap

@RunWith(classOf[JUnitRunner])
class FindPossibleUnMarkedTilesSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with MockitoSugar {
  val chessBoard: ChessBoard = ChessBoard()
  val heuristicAlgorithm = new HeuristicAlgorithm(chessBoard)
  val positionsVisited: HashMap[Int, (Int, Int)] = HashMap[Int, (Int, Int)]()
  val listOfLegalMoves : List[(String, Int, Int)]= ("N", -3, 0) ::
    ("NW", -2,-2 ) ::
    ("W", 0, -3) ::
    ("SW", 2, -2) ::
    ("S", 3, 0 ) ::
    ("SE", 2, 2) ::
    ("E",0,3) ::
    ("NE", -2, 2) :: Nil

  feature("A piece starting with a position 2,2 should have 6 possible moves") {
    scenario("tracing possible next moves from position 2,2") {
      Given("a board of grid size 100")
      val mockNextPosition = mock[TilePosition]
      val currentPosition = TilePosition(2, 2)
      val piece = Piece(currentPosition = currentPosition,
        nextPosition = mockNextPosition,
        MovementRule.buildRules(listOfLegalMoves),
        //positionsVisited + (1 -> (2, 5)),
        positionsVisited,
        heuristicAlgorithm)
      Then("the possible moves with all legal rules from position 2,2 should be 6")
        assert(heuristicAlgorithm.findPossibleUnmarkedTiles(piece).size == 6)
    }
  }

  feature("A piece starting with a position 2,2 should have 5 possible moves when east(2,5) already visited") {
    scenario("tracing possible next moves from position 2,2 marking (2,5) already visited") {
      Given("a board of grid size 100")
      val mockNextPosition = mock[TilePosition]
      val currentPosition = TilePosition(2, 2)
      val piece = Piece(currentPosition = currentPosition,
        nextPosition = mockNextPosition,
        MovementRule.buildRules(listOfLegalMoves),
        positionsVisited + (1 -> (2, 5)),
        //positionsVisited,
        heuristicAlgorithm)
      Then("the possible moves with all legal rules from position 2,2 should be 6")
      assert(heuristicAlgorithm.findPossibleUnmarkedTiles(piece).size == 5)
    }
  }
}

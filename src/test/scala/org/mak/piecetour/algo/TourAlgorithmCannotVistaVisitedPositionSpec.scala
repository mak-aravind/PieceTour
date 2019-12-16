package org.mak.piecetour.algo

import org.junit.runner.RunWith
import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{MovementRule, Piece}
import org.scalatest.{BeforeAndAfter, FeatureSpec, GivenWhenThen}
import org.scalatestplus.junit.JUnitRunner
import org.scalatestplus.mockito.MockitoSugar

import scala.collection.immutable.HashMap

@RunWith(classOf[JUnitRunner])
class TourAlgorithmCannotVistaVisitedPositionSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with MockitoSugar {

  val chessBoard: ChessBoard = ChessBoard()
  val heuristicAlgorithm = new HeuristicAlgorithm(chessBoard)
  val positionsVisited: HashMap[Int, (Int, Int)] = HashMap[Int, (Int, Int)]()

  feature("A piece starting with a position 2,2 cannot move East since the position already visited") {
    scenario("trying to move 3 steps right from position 2,2") {
      Given("a board of grid size 100")
      val mockNextPosition = mock[TilePosition]
      val currentPosition = TilePosition(2, 2)
      val piece = Piece(currentPosition = currentPosition,
        nextPosition = mockNextPosition,
        MovementRule.buildRules(Nil),
        positionsVisited + (1 -> (2, 5)),
        heuristicAlgorithm)
      val eastMovementRule = new MovementRule("N", 0, 3)
      Then("cannot move east from position 2,2, since the position already visited")
      assert(!heuristicAlgorithm.canMove(piece, eastMovementRule))
    }
  }

  feature("A piece starting with a position 2,2 can move south east") {
    scenario("trying to move 2 steps down diagnolly right from position 2,2") {
      Given("a board of grid size 100")
      val mockNextPosition = mock[TilePosition]
      val currentPosition = TilePosition(2, 2)
      val piece = Piece(currentPosition = currentPosition,
        nextPosition = mockNextPosition,
        MovementRule.buildRules(Nil),
        positionsVisited + (1 -> (2, 5)),
        heuristicAlgorithm)
      val soutEastMovementRule = new MovementRule("SE", 2, 2)
      Then("can move sout east from position 2,2 ")
      assert(heuristicAlgorithm.canMove(piece, soutEastMovementRule))
    }
  }

  feature("A piece starting with a position 2,2 cannot move south east since the position visited") {
      scenario("trying to move 2 steps down diagnolly right from position 2,2") {
        Given("a board of grid size 100")
        val mockNextPosition = mock[TilePosition]
        val currentPosition = TilePosition(2, 2)
        val piece = Piece(currentPosition = currentPosition,
          nextPosition = mockNextPosition,
          MovementRule.buildRules(Nil),
          positionsVisited + (1 -> (2, 5)) + (2 -> (4, 4)),
          heuristicAlgorithm)
        val southEastMovementRule = new MovementRule("SE", 2, 2)
        Then("cannot move south east from position 2,2 since the position 4,4 already visited")
        assert(!heuristicAlgorithm.canMove(piece, southEastMovementRule))
      }
  }
}



package org.mak.piecetour.algo

import org.junit.runner.RunWith
import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{MovementRule, Piece}
import org.mak.piecetour.exception.PositionOutOfBoardException
import org.scalatest.{BeforeAndAfter, FeatureSpec, GivenWhenThen}
import org.scalatestplus.junit.JUnitRunner
import org.scalatestplus.mockito.MockitoSugar

import scala.collection.immutable.HashMap

@RunWith(classOf[JUnitRunner])
class TourAlgorithmFindingTourSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with MockitoSugar{

  val chessBoard: ChessBoard =  ChessBoard()
  val heuristicAlgorithm = new HeuristicAlgorithm(chessBoard)
  val positionsVisited: HashMap[Int, (Int, Int)] = HashMap[Int, (Int, Int)]()


  feature("A piece starting with a position out of grid") {
    scenario("trying to move row Index as 11 and column Index as 9") {
      Given("a board of grid size 100")
      val nextPosition = TilePosition(11, 9)
      val mockCurrentPosition = mock[TilePosition]
      val piece = Piece(currentPosition = mockCurrentPosition,
                        nextPosition = nextPosition,
                        MovementRule.buildRules(Nil),
                        positionsVisited,
                        heuristicAlgorithm)
      Then("the position 11,9 is out of Board")
        intercept[PositionOutOfBoardException] {piece.startTour()}
    }
  }

  feature("A piece starting with a position 2,2 cannot move North") {
    scenario("trying to move 3 steps up from position 2,2") {
      Given("a board of grid size 100")
        val mockNextPosition = mock[TilePosition]
        val currentPosition = TilePosition(2, 2)
        val piece = Piece(currentPosition = currentPosition,
          nextPosition = mockNextPosition,
          MovementRule.buildRules(Nil),
          positionsVisited,
          heuristicAlgorithm)
        val northMovementRule = new MovementRule("N", -3, 0)
      Then("cannot move north from position 2,2")
        assert(!heuristicAlgorithm.canMove(piece, northMovementRule))
    }
  }

  feature("A piece starting with a position 2,2 can move East") {
    scenario("trying to move 3 steps Right from position 2,2") {
      Given("a board of grid size 100")
      val mockNextPosition = mock[TilePosition]
      val currentPosition = TilePosition(2, 2)
      val piece = Piece(currentPosition = currentPosition,
        nextPosition = mockNextPosition,
        MovementRule.buildRules(Nil),
        positionsVisited,
        heuristicAlgorithm)
      val eastMovementRule = new MovementRule("E", 0, 3)
      Then("can move east from position 2,2")
      assert(heuristicAlgorithm.canMove(piece, eastMovementRule))
    }
  }

  feature("A piece starting with a position 2,2 can move South") {
    scenario("trying to move 3 steps down from position 2,2") {
      Given("a board of grid size 100")
      val mockNextPosition = mock[TilePosition]
      val currentPosition = TilePosition(2, 2)
      val piece = Piece(currentPosition = currentPosition,
        nextPosition = mockNextPosition,
        MovementRule.buildRules(Nil),
        positionsVisited,
        heuristicAlgorithm)
      val southMovementRule = new MovementRule("S", 3, 0)
      Then("can move South from position 2,2")
      assert(heuristicAlgorithm.canMove(piece, southMovementRule))
    }
  }

}

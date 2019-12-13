package org.mak.piecetour.domain.model.piece

import org.mak.piecetour.algo.HeuristicAlgorithm
import org.mak.piecetour.domain.model.board.TilePosition

import scala.collection.immutable.HashMap

case class Chessman(var currentPosition: TilePosition, private val listOfMovementRules: List[MovementRule], private val heuristicAlgorithm: HeuristicAlgorithm) extends Piece{

  protected var positionsVisited: Map[Int, TilePosition] = HashMap[Int, TilePosition]()

  def getCurrentPosition : TilePosition = currentPosition
  def getLegalMoves : List[MovementRule] = listOfMovementRules

  override def moveTo(position: TilePosition): Unit = ???

  //override def setCurrentPoisition(position: TilePosition): Unit = ???
  override def setCurrentPoisition(position: TilePosition): Unit = ???

  override def startTour(): Unit = {

  }
}

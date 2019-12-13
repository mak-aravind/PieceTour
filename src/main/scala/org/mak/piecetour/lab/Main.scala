package org.mak.piecetour.lab

import org.mak.piecetour.algo.HeuristicAlgorithm
import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{Chessman, MovementRule}

object Main extends App {
  val chessBoard = ChessBoard()
  println("Furnishing Chess Board: ")
  println(chessBoard.getPrintable(true))
  println(chessBoard.getPrintable())
  println("The size of the chessboard grid")
  println("ROW SIZE:" + chessBoard.rowSize + " " + "COLUMN SIZE:" + chessBoard.columnSize)

  val listOfLegalMoves = ("N", -3, 0) ::
                         ("NW", -2,-2 ) ::
                         ("W", 0, -3) ::
                         ("SW", 2, -2) ::
                         ("S", 3, 0 ) ::
                         ("SE", 2, 2) ::
                         ("E",0,3) ::
                         ("NE", -2, 2) :: Nil

  val randomRowIndex = scala.util.Random.nextInt(chessBoard.rowSize)
  val randomColumnIndex = scala.util.Random.nextInt(chessBoard.columnSize)
  val startPosition = new TilePosition(randomRowIndex, randomColumnIndex)
  val heuristicAlgorithm = new HeuristicAlgorithm(chessBoard)
  val chessman = Chessman(startPosition, MovementRule.buildMovementRules(listOfLegalMoves), heuristicAlgorithm)
}

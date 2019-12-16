package org.mak.piecetour.app

import org.mak.piecetour.algo.HeuristicAlgorithm
import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{MovementRule, Piece}

import scala.collection.immutable.HashMap

object Main extends App {

  println("Type grid size : ")
  val userGridSize = scala.io.StdIn.readLine()

  val chessBoard = ChessBoard(gridSize = userGridSize.toInt)
  println("<DEBUG>Furnishing Chess Board before tour: ")
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
  val startPosition = TilePosition(randomRowIndex, randomColumnIndex)
  val heuristicAlgorithm = new HeuristicAlgorithm(chessBoard)
  val positionsVisited: HashMap[Int, (Int, Int)] = HashMap[Int, (Int, Int)]()
  val chessman = Piece(currentPosition = startPosition,
                          nextPosition = startPosition,
                          MovementRule.buildRules(listOfLegalMoves),
                          positionsVisited,
                          heuristicAlgorithm
                          )
  println(chessman.toString)
  chessman.startTour()
  println("<DEBUG>Furnishing Chess Board after tour: ")
  println(chessBoard.getPrintable(true))
  println("<RESULT>Furnishing Chess Board after tour: ")
  println(chessBoard.getPrintable())
  println("Number of Positions visited " + chessman.positionsVisited.size)
  println("started with Row:" + randomRowIndex + "~~Column: " + randomColumnIndex)
}

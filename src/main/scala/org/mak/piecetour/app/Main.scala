package org.mak.piecetour.app

import org.mak.piecetour.algo.HeuristicAlgorithm
import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{MovementRule, Piece}

import scala.collection.immutable.HashMap

object Main extends App {
  println("Type grid size (Ex: 100 will be of 10 * 10): ")
  val userGridSize = scala.io.StdIn.readLine()
  val userGridSizeInt : Int = if (userGridSize != null)  userGridSize.toInt else 100
  println("Run in Debug Mode (Y or N): ")
  val debugMode = scala.io.StdIn.readLine()
  val userDebugMode = if (debugMode != null && debugMode.equalsIgnoreCase("yes") || debugMode.equals("y")) true else false
  val chessBoard = ChessBoard(gridSize = userGridSizeInt)
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

  private def printDebugDetails(): Unit = {
    println("<DEBUG>Furnishing Chess Board before tour: ")
    println(chessBoard.getPrintable(true))
    println("<DEBUG>Furnishing details of piece about to navigate: ")
    println(chessman.toString)
  }
  if (userDebugMode) printDebugDetails()
  println("Furnishing Chess Board before tour: ")
  println(chessBoard.getPrintable())
  println("The size of the chessboard grid")
  println("ROW SIZE:" + chessBoard.rowSize + " " + "COLUMN SIZE:" + chessBoard.columnSize)
  chessman.startTour()
  if (userDebugMode) {
    printDebugDetails()
  }
  println("<RESULT>Furnishing Chess Board after tour: ")
  println(chessBoard.getPrintable())
  println("Number of Positions visited " + chessman.positionsVisited.size)
  println("started with Row:" + randomRowIndex + "~~Column: " + randomColumnIndex)
}

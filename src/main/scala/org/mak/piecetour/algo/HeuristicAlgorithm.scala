package org.mak.piecetour.algo

import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{Chessman, Piece}
import org.mak.piecetour.exception.PositionOutOfBoardException

import scala.collection.immutable.HashMap

trait TourAlgo {
    def findTourPath(piece: Piece)
}

class HeuristicAlgorithm(val chessBoard: ChessBoard) extends TourAlgo {

  var possibleMoves: Map[Chessman, Set[TilePosition]] = HashMap[Chessman, Set[TilePosition]]()

  def start(chessman: Piece): Unit = {
    val isCurrentPositionWithinLimits = chessman.getCurrentPosition.rowIndex >= 0 &&
                                          chessman.getCurrentPosition.rowIndex <= chessBoard.rowSize &&
                                            chessman.getCurrentPosition.columnIndex >= 0 &&
                                              chessman.getCurrentPosition.columnIndex <= chessBoard.columnSize
    if (isCurrentPositionWithinLimits)
      chessBoard.move(chessman)
    else
      throw new PositionOutOfBoardException
  }

  override def findTourPath(piece: Piece): Unit = {
      //1. Start from any tile and mark it as visited.
      //TODO
      start(piece)
      //2. To decide the next tile in path, look at all possible unmarked tiles based on moving rules.
      //TODO
      //3. Rak each possibility by the number of next moves from that tile.
      //TODO
      //4. Move to any tile with the lowest rank.
      //TODO
      //5. Add chosen tile to knight's tour path (i.e marked) and repeat the process from last chosen tile.
      //TODO

    }
}

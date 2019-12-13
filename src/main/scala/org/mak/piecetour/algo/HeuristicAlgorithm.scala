package org.mak.piecetour.algo

import org.mak.piecetour.domain.model.board.{ChessBoard, TilePosition}
import org.mak.piecetour.domain.model.piece.{Chessman, Piece}

import scala.collection.immutable.HashMap

trait TourAlgo {
    def findTourPath(piece: Piece)
}

class HeuristicAlgorithm(val chessBoard: ChessBoard) extends TourAlgo {

  var possibleMoves: Map[Chessman, Set[TilePosition]] = HashMap[Chessman, Set[TilePosition]]()

  def start(): Unit = {

  }

  override def findTourPath(piece: Piece): Unit = {
      //1. Start from any tile and mark it as visited.
      //TODO
      start()
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

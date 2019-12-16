package org.mak.piecetour.algo

import org.mak.piecetour.domain.model.piece.Piece

trait TourAlgorithm {
  def findTourPath(piece: Piece)
}
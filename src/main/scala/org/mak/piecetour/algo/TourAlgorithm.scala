package org.mak.piecetour.algo

import org.mak.piecetour.domain.model.piece.{Chessman, Piece}

trait TourAlgorithm {
  def findTourPath(piece: Chessman)
}
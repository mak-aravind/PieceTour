package org.mak.piecetour.domain.model.board

class Dimension(val rows: Int, val columns: Int)
object Dimension{
  def apply(rows: Int, columns: Int): Dimension = new Dimension(rows, columns)
}


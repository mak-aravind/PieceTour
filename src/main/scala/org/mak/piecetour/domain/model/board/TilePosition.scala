package org.mak.piecetour.domain.model.board

case class TilePosition(rowIndex:Int = -1, columnIndex:Int = -1, var visited: Int = 0, var rank: Int = 0){
  override def toString: String = {
      "ROW:" + rowIndex +
      "~COL:"+ columnIndex +
      "~VISIT:" + visited +
      "~RANK:" + rank
  }
}


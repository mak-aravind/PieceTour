package org.mak.piecetour.domain.model.board

class TilePosition(val rowIndex:Int = -1, val columnIndex:Int = -1, val visited: Int = 0, val rank: Int = 0){
  override def toString: String = {
    "ROW:" + rowIndex + "COL:"+ columnIndex + "VISIT:" + visited + "RANK:" + rank
  }
}


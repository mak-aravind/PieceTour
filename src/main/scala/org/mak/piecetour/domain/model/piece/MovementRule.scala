package org.mak.piecetour.domain.model.piece

case class MovementRule(direction: String, rowIndex: Int, columnIndex: Int)

object MovementRule{
  def buildRules(listOfLegalMoves: List[(String, Int, Int)]) :  List[MovementRule] = {
    listOfLegalMoves.map( item => MovementRule(item._1,item._2, item._3))
  }
}

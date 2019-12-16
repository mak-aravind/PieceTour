package org.mak.piecetour.domain.model.piece

import org.junit.runner.RunWith
import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MovementRuleBuilderSpec extends FeatureSpec with GivenWhenThen {
  feature("For a given list of legal rules"){
    scenario("build a list of Movement Rules from list of legal rules"){
      Given("a list")
        val listOfLegalMoves = ("N", -3, 0) ::
          ("NW", -2,-2 ) ::
          ("W", 0, -3) ::
          ("SW", 2, -2) ::
          ("S", 3, 0 ) ::
          ("SE", 2, 2) ::
          ("E",0,3) ::
          ("NE", -2, 2) :: Nil
      When("MovementRule buildRules called it should return list of Movement Rule")
          val listOfMovementRules = MovementRule.buildRules(listOfLegalMoves)
      Then("The Movement rule 4 should be South-West and rowIndex should be 2 and column Index should be -2")
          assert(listOfMovementRules(3).direction == "SW")
          assert(listOfMovementRules(3).rowIndex == 2)
          assert(listOfMovementRules(3).columnIndex == -2)
          assert(listOfLegalMoves.size == listOfMovementRules.size)
    }
  }
}
package es.interviewcake.exercises.coin

import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by guillermo on 24/03/15.
 */
class TestDynamicCoinDenomination extends FlatSpec with Matchers{

  "With amount of 7 and denomination [2,3,5]" should "return 3 coins to be used" in {

    val totalCoins = CoinDenominations.dynamicApproachWithNumberOfCoins(7, List(2,3,5))

    totalCoins should be(2)
  }


}

package es.interviewcake.exercises.coin

import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by guillermo on 24/03/15.
 */
class TestDynamicCoinDenomination extends FlatSpec with Matchers{

  "With amount of 7 and denomination [2,3,5]" should "return 2 coins to be used" in {

    val totalCoins = CoinDenominations.dynamicApproachGettingMinimumNumberOfCoinsRequired(7, List(2,3,5))

    totalCoins should be(2)
  }


  "With amount of 7 and denomination [2,3,5]" should "return related coin combinations" in {

    val combinations = CoinDenominations.dynamicApproachWithNumberOfCoins(7, List(2,3,5))

    combinations should have size 2
    combinations should contain only (List(3, 2, 2), List(5, 2))
  }

  "With amount of 6 and denomination [2,3,5]" should "return related coin combinations" in {

    val combinations = CoinDenominations.dynamicApproachWithNumberOfCoins(6, List(2,3,5))

    combinations should have size 2
    combinations should contain only (List(2, 2, 2), List(3, 3))
  }

  "With amount of 5 and denomination [2,3,5]" should "return related coin combinations" in {

    val combinations = CoinDenominations.dynamicApproachWithNumberOfCoins(5, List(2,3,5))

    combinations should have size 2
    combinations should contain only (List(3,2), List(5))
  }

  "With amount of 4 and denomination [2,3,5]" should "return related coin combinations" in {

    val combinations = CoinDenominations.dynamicApproachWithNumberOfCoins(4, List(2,3,5))

    combinations should have size 1
    combinations should contain only (List(2,2))
  }

  "With amount of 3 and denomination [2,3,5]" should "return related coin combinations" in {

    val combinations = CoinDenominations.dynamicApproachWithNumberOfCoins(3, List(2,3,5))

    combinations should have size 1
    combinations should contain only (List(3))
  }

  "With amount of 2 and denomination [2,3,5]" should "return related coin combinations" in {

    val combinations = CoinDenominations.dynamicApproachWithNumberOfCoins(2, List(2,3,5))

    combinations should have size 1
    combinations should contain only (List(2))
  }

  "With amount of 1 and denomination [2,3,5]" should "return related coin combinations" in {

    val combinations = CoinDenominations.dynamicApproachWithNumberOfCoins(1, List(2,3,5))

    combinations should have size 0
  }

  "With amount of 4 and denomination [1,2,3]" should "return unique lists of combinations" in {

    val denominations = CoinDenominations.dynamicApproachWithNumberOfCoins(4, List(1,2,3))

    denominations should have size 4
    denominations should contain only (List(1,1,1,1), List(2,2), List(1,3), List(1,1,2))
  }

}

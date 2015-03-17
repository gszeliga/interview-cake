package es.interviewcake.exercises.coin

import org.scalatest.{FlatSpec, Matchers};

/**
 * Created by guillermo on 16/03/15.
 */
class TestCoinDenominations extends FlatSpec with Matchers {

  "With amount of 2 and denomination [2]" should "return one single combination with amount only" in {

    val denominations = CoinDenominations(2, List(2))

    denominations should have size 1
    denominations should contain only (List(2))
  }

  "With amount of 4 and denomination [1]" should "return one single combination array using denomination" in {

    val denominations = CoinDenominations(4, List(1))

    denominations should have size 1
    denominations should contain only (List(1,1,1,1))
  }

  "With amount of 2 and denomination [1,2]" should "return two unique possible combinations" in {

    val denominations = CoinDenominations(2, List(1,2))

    denominations should have size 2
    denominations should contain only (List(1,1), List(2))
  }

  "With amount of 4 and denomination [1,2,3]" should "return unique lists of combinations" in {

    val denominations = CoinDenominations(4, List(1,2,3))

    println(denominations)

    denominations should have size 4
    denominations.map(_.sorted) should contain only (List(1,1,1,1), List(2,2), List(1,3), List(1,1,2))
  }

}

package es.interviewcake.exercises.two_eggs

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by guillermo on 1/09/15.
 */
class TestTwoEggs extends FlatSpec with Matchers {

  "A 15 floors building" should " drop at most 5 times" in {

    TwoEggs(15,14) shouldBe 5

  }

  "A 100 floors building" should " drop at most 14 times" in {

    TwoEggs(100,98) shouldBe 14

  }

}

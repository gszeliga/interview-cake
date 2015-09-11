package es.interviewcake.exercises.product

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by guillermo on 8/09/15.
 */
class TestProductOfOtherNumbers extends FlatSpec with Matchers {

  "The product of an array of size 1" should " do nothing" in {

    ProductOfOtherNumbers(Array(2)) shouldBe(Array(1))

  }

  "The product of an array of size 2" should " basically return a reverted array" in {

    ProductOfOtherNumbers(Array(2,4)) shouldBe(Array(4,2))

  }

  "The product of an array of size 4" should " properly return the multiplied values" in {

    ProductOfOtherNumbers(Array(1,7,3,4)) shouldBe(Array(84,12,28,21))

  }

}

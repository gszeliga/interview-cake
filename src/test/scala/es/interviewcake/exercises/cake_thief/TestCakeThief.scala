package es.interviewcake.exercises.cake_thief

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by guillermo on 24/09/15.
 */
class TestCakeThief extends FlatSpec with Matchers {

  val cakes = List(Cake(7,160),Cake(3,90),Cake(2,15), Cake(4,0))

  "Cake Thief" should "return accurate value when capacity is 0" in {
    CakeThief.maxDuffelBagValue(cakes,0) shouldBe 0
  }

  "Cake Thief" should "return accurate value when capacity is 2" in {
    CakeThief.maxDuffelBagValue(cakes,2) shouldBe 15
  }

  it should "return accurate value when capacity is 3" in {
    CakeThief.maxDuffelBagValue(cakes,3) shouldBe 90
  }

  it should "return accurate value when capacity is 4" in {
    CakeThief.maxDuffelBagValue(cakes,4) shouldBe 90
  }

  it should "return accurate value when capacity is 5" in {
    CakeThief.maxDuffelBagValue(cakes,5) shouldBe 105
  }

  it should "return accurate value when capacity is 6" in {
    CakeThief.maxDuffelBagValue(cakes,6) shouldBe 180
  }

  it should "return accurate value when capacity is 7" in {
    CakeThief.maxDuffelBagValue(cakes,7) shouldBe 180
  }

  it should "return accurate value when capacity is 8" in {
    CakeThief.maxDuffelBagValue(cakes,8) shouldBe 195
  }

  it should "return accurate value when capacity is 9" in {
    CakeThief.maxDuffelBagValue(cakes,9) shouldBe 270
  }

  it should "return accurate value when capacity is 20" in {
    CakeThief.maxDuffelBagValue(cakes,20) shouldBe 555
  }

}

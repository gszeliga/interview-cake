package es.interviewcake.exercises.reverse_words

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by guillermo on 9/03/15.
 */
class TestReverseWords extends FlatSpec with Matchers{

  "A single-word array" should "not be reverted" in {

    val outcome = ReverseWords.reverse("sample")

    outcome shouldBe "sample"

  }

  "A two-words array with same lenght" should "be reverted inline" in {

    val outcome = ReverseWords.reverse("mama papa")

    outcome shouldBe "papa mama"

  }

  "A three-words array with different length" should "be reverted inline" in {

    val outcome = ReverseWords.reverse("Cabezuelo Szeliga Guillermo")

    outcome shouldBe "Guillermo Szeliga Cabezuelo"

  }

  "Sample message" should "be reverted as suggested" in{
    val outcome = ReverseWords.reverse("find you will pain only go you recordings security the into if")

    outcome shouldBe "if into the security recordings you go only pain will you find"
  }

}

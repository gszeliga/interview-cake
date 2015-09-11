package es.interviewcake.exercises.duplicates

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by guillermo on 8/09/15.
 */
class TestDuplicates  extends FlatSpec with Matchers {

  "Duplicates" should " be found within an array of size 3" in {

    Duplicates.first(Array(2,1,2)) shouldBe(2)

  }

  "Duplicates" should " be found within an array of size 7" in {

    Duplicates.first(Array(1,4,3,5,7,3,2)) shouldBe(3)

  }

  "Multiple duplicates" should " found within an array of size 10" in {

    Duplicates.multiple(Array(6,2,3,4,5,6,7,5,1,3)) shouldBe(Set(5,6,3))

  }

}

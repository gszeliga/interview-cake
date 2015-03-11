package es.interviewcake.exercises.fib_number

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by guillermo on 11/03/15.
 */
class TestFibonacci extends FlatSpec with Matchers {

  "Fibonacci generator" should "return 0 when zero" in {
    Fibonacci(0) shouldBe  0
  }

  it should "return 1 when one" in {
    Fibonacci(1) shouldBe  1
  }

  it should "return 1 when 2" in {
    Fibonacci(2) shouldBe  1
  }

  it should "return 2 when 3" in {
    Fibonacci(3) shouldBe 2
  }

  it should "return 21 when 8" in {
    Fibonacci(8) shouldBe 21
  }

  "Fibonacci bottom-up approach" should "return 21 when 8" in {
    Fibonacci.bottomUp(8) shouldBe 21
  }

  it should "return 2 when 3" in {
    Fibonacci.bottomUp(3) shouldBe 2
  }

}

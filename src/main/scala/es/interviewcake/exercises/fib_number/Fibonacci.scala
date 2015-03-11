package es.interviewcake.exercises.fib_number

import scala.collection.immutable.HashMap

/**
 * Created by guillermo on 11/03/15.
 */
object Fibonacci {

  def apply(at: Int): Int = {

    def doFib(i: Int, memoization: HashMap[Int, Int]): (Int,HashMap[Int, Int]) = {

      val result = memoization.get(i)

      if(result.isEmpty)
      {
        if(i == 0) (0, memoization)
        else if(i == 1) (1, memoization)
        else {
          val (v1,memo_1) = doFib(i - 1, memoization)
          val (v2,memo_2) = doFib(i - 2, memo_1)
          val fib_v = v1 + v2

          (fib_v, memo_2 + (i -> fib_v))

        }
      }
      else
      {
        (result.get, memoization)
      }
    }

    val (fib,memo) = doFib(at, new HashMap[Int, Int])

    println(s"Memoization: ${memo}" )

    fib

  }

  //More space efficient
  def bottomUp(n: Int): Int = {

    if(n == 0) 0
    else if(n == 1) 1
    else {

      var prev = 0
      var prev2 = 1

      //Move upwards up to n. This way we're space efficient O(1)
      (2 to n).foreach(v => {
        val current = prev + prev2
        prev=prev2
        prev2=current
      })

      prev2
    }

  }

}

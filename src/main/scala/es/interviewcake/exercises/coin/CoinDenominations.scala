package es.interviewcake.exercises.coin

import scala.collection.immutable.HashMap

/**
 * Created by guillermo on 16/03/15.
 */
//source: https://www.interviewcake.com/question/coin
object CoinDenominations {


  //O(n * m)
  //Works but not that efficient approach
  def apply(amount: Int, denominations: List[Int]): List[List[Int]] = {

    def doCombinations(den: List[Int], remain: Int, partial: List[Int], result: List[List[Int]]): List[List[Int]] = {

      //Still some value to be evaluated?
      if(remain > 0) {
        //For each denomination
        den.foldLeft(result) ((r,d) => {
          if (remain >= d)
          //Awesome, can continue evaluating the list but with a reduced amount
            doCombinations(den, remain - d, d +: partial, r)
          else
          //Current denomination doesn't fit on remaining amount. Need to remove it from list
          //and keep evaluating the rest of the list of denominations
            doCombinations(den.filter(_ != d), remain, partial, r)
        })
      }
      //Remaining amount is zero, therefore, current combination is valid!
      else if(remain == 0) appendIfNew(result,partial)
      else List.empty[List[Int]] //Discard current denomination

    }

    def appendIfNew(target: List[List[Int]], source: List[Int]): List[List[Int]] = {

      //Sort source list
      val s = source.sorted

      //Verify that sorted source list does not exist within target
      val exists = target.exists(l =>
        if(l.length == s.length)
          l.sorted.zipWithIndex.forall({case (v,i) => v == s(i)}) //could use == too but this involves more fun
        else false
      )

      if(!exists) source +: target
      else target

    }

    doCombinations(denominations, amount, List(), List())

  }

  def bottomUpApproach(amount: Int, denominations: List[Int]):List[List[Int]] = ???

  def dynamicApproach(amount: Int, denominations: List[Int]): List[List[Int]] = {

    //O(n) space, where n is the size of the amount
    val waysOfDoingNCents = HashMap.empty[Int, List[List[Int]]]

  }

}

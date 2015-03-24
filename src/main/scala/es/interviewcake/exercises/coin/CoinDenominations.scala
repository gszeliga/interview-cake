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

  def dynamicApproachWithNumberOfCoins(targetAmount: Int, denominations: List[Int]): Int = {

    //X=denominations+1, Y=amount+1
    val b = Array.ofDim[Int](denominations.size+1, targetAmount+1)

    //First element must be zero
    b(0)(0)=0

    //There will never be more coind that available
    val infinitum=denominations.size+1

    //Initialize all elements on zero coins to max value (infinit)
    (1 to targetAmount) foreach(b(0)(_) = infinitum)

    (1 to denominations.size) foreach( coin => {

      val coinDenomination: Int = denominations(coin - 1)

      (0 to targetAmount) foreach(amount => {

        b(coin)(amount) = infinitum

        println(s"@[coin=$coin][amount=$amount][den=$coinDenomination]")

        (0 to Math.floorDiv(amount, coinDenomination)) foreach(k => {

          println(s"\tWith k=$k => b[$coin][$amount]>b[${coin-1}][${amount-k*coinDenomination}]")

          if(b(coin)(amount) > (b(coin-1)(amount-k*coinDenomination))+k)
          {
            b(coin)(amount) = b(coin-1)(amount-k*coinDenomination)+k

            println(s"\tUpdated b[$coin][$amount]=${b(coin)(amount)}")

          }

        })

      })

    })

    //Last element has the answer
    b(denominations.size)(targetAmount)

  }

}

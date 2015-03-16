package es.interviewcake.exercises.coin

/**
 * Created by guillermo on 16/03/15.
 */
//source: https://www.interviewcake.com/question/coin
object CoinDenominations {

  def apply(amount: Int, denominations: List[Int]): List[List[Int]] = {

    def doCombinations(den: List[Int], remain: Int, tmp: List[Int], result: List[List[Int]]): List[List[Int]] = {

      if(remain > 0) {
        den.flatMap(d => {
          if (remain >= d)
            //Awesome, can continue evaluating the list but with a reduced amount
            doCombinations(den, remain - d, d +: tmp, result)
          else
            //Current denomination doesn't fit on remaining amount. Need to remove it from list
            //and keep evaluating the rest of the list of denominations
            doCombinations(den.filter(_ != d), remain, tmp, result)
        })
      }
      //Remaining amount is zero, therefore, current combination is valid!
      else if(remain == 0) appendIfNew(result,tmp)
      else List.empty[List[Int]] //Discard current denomination

    }

    def appendIfNew(target: List[List[Int]], source: List[Int]): List[List[Int]] = ???

    doCombinations(denominations, amount, List(), List())

  }

}

package es.interviewcake.exercises.coin

import scala.annotation.tailrec
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

  def dynamicApproachGettingMinimumNumberOfCoinsRequired(targetAmount: Int, denominations: List[Int]): Int = {

    //X=denominations+1, Y=amount+1
    val b = Array.ofDim[Int](denominations.size+1, targetAmount+1)

    //First element must be zero
    b(0)(0)=0

    //Bad approach but works for now
    val infinity=denominations.size+1

    //Initialize all elements on zero coins to max value (infinity)
    (1 to targetAmount) foreach(b(0)(_) = infinity)

    (1 to denominations.size) foreach( coin => {

      val coinDenomination: Int = denominations(coin - 1)

      (0 to targetAmount) foreach(amount => {

        b(coin)(amount) = infinity

        println(s"@[coin=$coin][amount=$amount][den=$coinDenomination]")

        (0 to Math.floorDiv(amount, coinDenomination)) foreach(k => {

          println(s"\tWith k=$k => b[$coin][$amount]>b[${coin-1}][${amount-k*coinDenomination}]")

          //Check previous solutions with reducing the current amount k number
          //of times our current denomination (coin)
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


  def dynamicApproachWithNumberOfCoins(targetAmount: Int, denominations: List[Int]): List[List[Int]] = {

    trait CoinNode{
      def denomination: Int
      def times: Int
      def n: Int
      def next: Option[CoinNode]
    }

    case class CoinNodeRef(val denomination: Int, val next: Option[CoinNode], val n: Int,val times: Int) extends CoinNode

    case object EmptyCoinNode extends CoinNode {
      val denomination = Int.MinValue
      val times = 0
      val next = Option.empty
      val n = Int.MaxValue - denominations.size //Avoid overflow
    }

    def solve(amount: Int, denominations: List[Int]): Array[Array[CoinNode]] = {

      //Y=denominations+1, X=amount+1 (We revert matrix so that we can build up solution using amount)
      val b = Array.ofDim[CoinNode](targetAmount + 1,denominations.size + 1)

      //First element must be zero
      b(0)(0) = CoinNodeRef(0,Option.empty, 0,0)

      //Initialize all elements on zero coins to max value (infinity)
      (1 to targetAmount) foreach (b(_)(0) = EmptyCoinNode)

      (1 to denominations.size) foreach (coin => {

        val coinDenomination = denominations(coin - 1)

        (0 to targetAmount) foreach (amount => {

          b(amount)(coin) = EmptyCoinNode

          (0 to Math.floorDiv(amount, coinDenomination)) foreach (times => {

            //Check previous solutions with reducing the current amount times number
            //of times our current denomination (coin)
            if (b(amount)(coin).n > (b(amount - times * coinDenomination)(coin - 1)).n + times) {

              val numberOfCoins: Int = b(amount - times * coinDenomination)(coin - 1).n + times
              val previousSolution: Some[CoinNode] = Some(b(amount - times * coinDenomination)(coin - 1))

              b(amount)(coin) = CoinNodeRef(coinDenomination,previousSolution,numberOfCoins,times)
            }

          })

        })

      })

      b
    }

    @tailrec
    def representSolutionFrom(from: Option[CoinNode], solutions: Array[Array[CoinNode]], tmp: List[Int]): List[Int] = {

      from match{
        case None => tmp
        case Some(EmptyCoinNode) => tmp
        case Some(CoinNodeRef(denomination, None,_,times)) => tmp ++ List.fill(times)(denomination)
        case Some(CoinNodeRef(denomination, n @ Some(next), _ ,times)) =>{
          println(s"Adding 'COIN=$denomination' x '$times' to '$tmp'")
          representSolutionFrom(n, solutions, tmp ++ List.fill(times)(denomination))
        }
      }
    }

    val solutions = solve(targetAmount, denominations)

    solutions(targetAmount).foldLeft(List.empty[List[Int]])((acc, v) => {

      println(s"Using solution => $v")

      //Should we consider current solution? k=0 means no solution available for current coin
      if(v.times > 0)
      {
        val combination = representSolutionFrom(Some(v), solutions, List.empty)

        if(!combination.isEmpty)
          acc :+ combination
        else
          acc
      }
      else acc
    })

  }

}

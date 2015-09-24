package es.interviewcake.exercises.cake_thief

/**
 * Created by guillermo on 24/09/15.
 */

case class Cake(weight: Int, value: Int)


//We could identify cakes that couldn't possibly appear in our solutions: i.e. Those that weight more and worth less than
//other cakes (3,10) will never have preference over (2,15) => Dominance Relations

object CakeThief {

  //Space: O(N)
  //Time: O(N * M)
  def maxDuffelBagValue(cakes: List[Cake], capacity: Int): Int = {

    (1 to capacity).foldLeft(Array.fill(capacity+1)(0)){(monetaryValues,currentCapacity) => {

      cakes.foreach{cake =>

        if(cake.weight<= currentCapacity)
        {
          val remainingCapacity = currentCapacity - cake.weight
          val newValue = cake.value + monetaryValues(remainingCapacity)
          val currentValue = monetaryValues(currentCapacity)

          if(newValue > currentValue)
          {
            monetaryValues(currentCapacity) = newValue
          }
          else if(currentValue == 0)
          {
            val bestPreviousSolution = currentCapacity - 1
            monetaryValues(currentCapacity) = monetaryValues(bestPreviousSolution)
          }
        }

      }

      monetaryValues

    }}(capacity)
  }

}

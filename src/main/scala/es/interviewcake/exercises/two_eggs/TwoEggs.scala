package es.interviewcake.exercises.two_eggs

/**
 * Created by guillermo on 31/08/15.
 */
object TwoEggs {

  def apply(totalNumberOfFloors: Int, highestNonBreakingFloor: Int): Int = {

    // quadratic equation of a triangular series, if we find the N value of the series,
    // then we've got the decrementing sequence
    val nFloorsToSkip = Math.ceil((-1 + math.sqrt(1 + 4*(totalNumberOfFloors*2))) / 2).toInt

    println(s"# Number of floors to skip => '${nFloorsToSkip}'")

    def dropUntilBreaks(lastFloor: Int, drops: Int): (Int, Int, Int) = {

      val nFloorsUp = nFloorsToSkip - drops
      val currentFloor = lastFloor + nFloorsUp

      println(s"--> Dropping from floor ${currentFloor}")

      if(currentFloor > highestNonBreakingFloor)
        (lastFloor, currentFloor, drops+1)
      else dropUntilBreaks(currentFloor, drops+1)

    }

    val (from, to, drops) = dropUntilBreaks(0, 0)


    println(s"# Egg broke between floors [$from-$to] after '${drops}' drops")

    //Return the maximum number of drop we need to find out up to which floor the egg won't break
    (from + 1 until to).takeWhile(floor => floor <= highestNonBreakingFloor).foldLeft(drops)((acc,v) => acc + 1)

  }

}

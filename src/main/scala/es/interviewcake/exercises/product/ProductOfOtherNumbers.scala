package es.interviewcake.exercises.product

/**
 * Created by guillermo on 8/09/15.
 */
object ProductOfOtherNumbers {

  //This approach offers an O(n) cost since we need to traverse only twice the original array
  def apply(numbers:Array[Int]): Array[Int] = {

    //Initialize all positions with 1 since it's the multiplication 'unit' value
    val tmp = Array.fill(numbers.length){1}

    val (left, _) = (0 to numbers.length - 1).foldLeft((tmp, 1)) {
      case ((values, partial_total), current_index) => {
        values(current_index) = values(current_index) * partial_total
        (values, partial_total * numbers(current_index))
      }
    }

    println("Left? => " + left.mkString(","))

    val (right, _) = (numbers.length - 1 to 0 by -1).foldLeft((left, 1)) {
      case ((values, partial_total), current_index) => {
        values(current_index) = values(current_index) * partial_total
        (values, partial_total * numbers(current_index))
      }
    }

    println("Right? => " + right.mkString(","))

    right
  }

}

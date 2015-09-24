package es.interviewcake.exercises.duplicates

/**
 * Created by guillermo on 8/09/15.
 */
object Duplicates {

  //Space: O(1)
  //Time: O(n log n)
  def first(numbers: Array[Int]): Int = {

    var floor = 1
    var ceiling = numbers.length - 1 //Minus 1 represents our duplicate, we assume a single duplicate

    while(floor < ceiling) {

      println(s"Evaluating range => [floor: $floor, ceiling: $ceiling]")

      val midpoint = floor + ((ceiling -floor) / 2)

      val lower_floor = floor
      val lower_ceilling = midpoint

      val higher_floor = midpoint+1
      val higher_ceilling = ceiling

      val lower_distinct_expected_number_of_elements = (lower_ceilling - lower_floor) + 1
      val lower_available_elements = numbers.count(v => v >= lower_floor && v <= lower_ceilling)

      if(lower_available_elements > lower_distinct_expected_number_of_elements)
      {
        ceiling = lower_ceilling
        floor = lower_floor //redundant
      }
      else {
        floor = higher_floor
        ceiling = higher_ceilling //redundant
      }
    }

    floor

  }

  def multiple(numbers: Array[Int]): Set[Int] = {

    def first(numbers: Array[Int], registry: Set[Int]): (Int,Set[Int]) = {

      var floor = 1
      var ceilling = numbers.length - (registry.size  + 1) //Minus 1 represents at least one duplicate, we assume a single duplicate

      while(floor < ceilling) {

        println(s"Evaluating range => [floor: ${floor}, ceilling: ${ceilling}]")

        val midpoint = floor + ((ceilling -floor) / 2)

        val lower_floor = floor
        val lower_ceilling = midpoint

        val higher_floor = midpoint+1
        val higher_ceilling = ceilling

        val lower_distinct_expected_number_of_elements = (lower_ceilling - lower_floor) + 1
        val lower_available_elements = numbers.count(v => v >= lower_floor && v <= lower_ceilling && !registry.contains(v))

        if(lower_available_elements > lower_distinct_expected_number_of_elements)
        {
          ceilling = lower_ceilling
          floor = lower_floor //redundant
        }
        else {
          floor = higher_floor
          ceilling = higher_ceilling //redundant
        }
      }

      (floor,registry)

    }

    var n_of_duplicates_found = -1
    var duplicates= Set.empty[Int]

    while(duplicates.size > n_of_duplicates_found)
    {
      n_of_duplicates_found = duplicates.size

      val (value, registry) = first(numbers,duplicates)

      println(s"Duplicate verification result [value: ${value}, registry: ${registry}")

      duplicates=registry+value
    }

    duplicates

  }

}

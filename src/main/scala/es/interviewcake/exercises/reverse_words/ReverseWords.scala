package es.interviewcake.exercises.reverse_words

import scala.util.control.Breaks.{break,breakable}

/**
 * Created by guillermo on 9/03/15.
 */

//https://www.interviewcake.com/question/reverse-words
object ReverseWords {
  def reverse(s: String): String = {

    def swap(source: Array[Char], from: Int, to: Int) = {

      var f=from
      var t=to
      var tmp = ' '

      while(f < t)
      {
        tmp = source(f)
        source(f) = source(t)
        source(t) = tmp

        f = f +1
        t = t -1

      }

      println(s"Swap result ${new String(source)}")

    }

    def spacePosition(source: Array[Char], from: Int, to: Int): Option[Int] = {

      var tmp = from

      println(s"Seeking space [$from - $to]")

      breakable {
        while (tmp <= to) {
          if (source(tmp) == ' ') break
          tmp = tmp + 1
        }
      }

      if(tmp > to) Option.empty
      else Option(tmp)
    }

    val source = s.toCharArray
    var nextWord=0;
    var space = spacePosition(source, nextWord, source.length-1)


    if(space.isDefined) {

      println(s"Space found at $space")

      //Swap the whole array at first
      swap(source, 0, source.length - 1)

      //Check first space with new swapped result
      space = spacePosition(source, nextWord, source.length-1)

      while (space.isDefined) {
        swap(source, nextWord, space.get - 1)

        nextWord = space.get + 1

        space = spacePosition(source,nextWord, source.length - 1)

        println(s"Space found at $space")

      }

      swap(source, nextWord, source.length - 1)
    }

    new String(source)

  }

}

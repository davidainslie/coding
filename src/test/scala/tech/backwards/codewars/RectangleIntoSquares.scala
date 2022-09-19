package tech.backwards.codewars

import scala.annotation.tailrec
import tech.backwards.codewars.SqInRectTest._
import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
 * x x x x x
 * x x x x x
 * x x x x x
 * [3, 2, 1, 1]
 *
 * x x x x x x
 * x x x x x x
 * x x x x x x
 * [3, 3]
 */
object SqInRect {
  def sqInRect(a: Int, b: Int): Array[Int] =
    if (a == b) {
      Array.emptyIntArray
    } else {
      @tailrec
      def go(min: Int, max: Int, acc: Vector[Int]): Vector[Int] =
        if (min == max)
          acc.appended(min)
        else {
          val nextA = max - min
          val nextB = ((min * max) - (min * min)) / nextA

          val (nextMin, nextMax) = if (nextB < nextA) nextB -> nextA else nextA -> nextB

          go(nextMin, nextMax, acc.appended(min))
        }

      val (min, max) = if (a < b) a -> b else b -> a

      go(min, max, Vector.empty).toArray
    }
}

object SqInRectOther1 {
  @tailrec
  def sqInRect(min: Int, max: Int, squares: Array[Int] = Array()): Array[Int] = {
    val Array(width, length) = Array(min, max).sorted

    if (width == length)
      if (squares.isEmpty) Array() else (width +: squares).reverse
    else
      sqInRect(length-width, width, width +: squares)
  }
}

object SqInRectOther2 {
  def sqInRect(min: Int, max: Int): Array[Int] = {
    if (min == max || min == 0 || max == 0)
      Array()
    else if (min > max)
      sqInRect(max, min)
    else
      Array.fill(max / min)(min) ++ sqInRect(max % min, min)
  }
}

class SqInRectTest extends AnyFlatSpec with Matchers {
  it should "pass basic tests" in {
    testing(5, 3, Array(3, 2, 1, 1))
    testing(3, 5, Array(3, 2, 1, 1))
    testing(5, 5, Array())
  }
}

object SqInRectTest {
  private def testing(min: Int, max: Int, expect: Array[Int]): Unit = {
    println("MIN: " + min + " MAX: " + max)
    // val actual: Array[Int] = SqInRect.sqInRect(min, max)
    val actual: Array[Int] = SqInRectOther2.sqInRect(min, max)
    println("Actual: " + actual.mkString(", "))
    println("Expect: " + expect.mkString(", "))
    println("-")
    assertResult(expect){actual}
  }
}

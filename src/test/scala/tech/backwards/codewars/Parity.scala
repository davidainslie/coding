package tech.backwards.codewars

/**
 * You are given an array (which will have a length of at least 3, but could be very large) containing integers.
 * The array is either entirely comprised of odd integers or entirely comprised of even integers except for a single integer N.
 * Write a method that takes the array as an argument and returns this "outlier" N.
 *
 * Examples:
 * {{{
 *   [2, 4, 0, 100, 4, 11, 2602, 36]
 *   Should return: 11 (the only odd number)
 *
 *   [160, 3, 1719, 19, 11, 13, -21]
 *   Should return: 160 (the only even number)
 * }}}
 */
object Parity {
  def findOutlier(integers: List[Int]): Int = {
    def go: List[Int] => Int = {
      case x1 :: x2 :: x3 :: rest =>
        List(x1, x2, x3).partition(_ % 2 == 0) match {
          case (List(x), _) => x
          case (_, List(x)) => x
          case _ => go(x2 :: x3 :: rest)
        }
    }

    go(integers)
  }
}

import org.scalatest.flatspec._
import org.scalatest.matchers.should._

class ParityTest extends AnyFlatSpec with Matchers {
  val tests: List[(List[Int], Int)] =
    List(
      (List(2, 4, 6, 8, 10, 3), 3),
      (List(2, 4, 0, 100, 4, 11, 2602, 36), 11),
      (List(160, 3, 1719, 19, 11, 13, -21), 160)
    )

  tests.foreach {
    case (input, expected) =>
      s"findOutlier($input)" should s"return $expected" in {
        Parity.findOutlier(input) should be (expected)
      }
  }
}
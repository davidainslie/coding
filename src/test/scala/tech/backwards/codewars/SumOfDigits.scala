package tech.backwards.codewars

/**
 * Digital root is the recursive sum of all the digits in a number.
 *
 * Given n, take the sum of the digits of n.
 * If that value has more than one digit, continue reducing in this way until a single-digit number is produced.
 * The input will be a non-negative integer.
 *
 * Examples:
 * {{{
 *   16       -->  1 + 6 = 7
 *   942      -->  9 + 4 + 2 = 15  -->  1 + 5 = 6
 *   132189   -->  1 + 3 + 2 + 1 + 8 + 9 = 24  -->  2 + 4 = 6
 *   493193   -->  4 + 9 + 3 + 1 + 9 + 3 = 29  -->  2 + 9 = 11  -->  1 + 1 = 2
 * }}}
 */
object SumOfDigits {
  def digitalRoot(n: Int): Int = {
    @scala.annotation.tailrec
    def go(n: Int, sum: Int): Int = {
      if (n > 9)
        go(n / 10, sum + n % 10)
      else if (sum + n > 9)
        go(sum + n, 0)
      else
        sum + n
    }

    go(n, 0)
  }
}

object SumOfDigitsOthers {
  def digitalRoot(n: Int): Int =
    (n - 1) % 9 + 1
}

import org.scalatest.flatspec._
import org.scalatest.matchers.should._

class SumOfDigitsTest extends AnyFlatSpec with Matchers {
  s"digitalRoot(0)" should s"return 0" in {
    SumOfDigitsOthers.digitalRoot(0) shouldBe 0
  }

  s"digitalRoot(5)" should s"return 5" in {
    SumOfDigitsOthers.digitalRoot(5) shouldBe 5
  }

  s"digitalRoot(10)" should s"return 1" in {
    SumOfDigitsOthers.digitalRoot(10) shouldBe 1
  }

  s"digitalRoot(16)" should s"return 7" in {
    SumOfDigitsOthers.digitalRoot(16) shouldBe 7
  }

  s"digitalRoot(456)" should s"return 6" in {
    SumOfDigitsOthers.digitalRoot(456) shouldBe 6
  }
}
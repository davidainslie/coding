package tech.backwards.codewars

import scala.annotation.tailrec
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

object Sol {
  def divisors(n: Int): Int = {
    @tailrec
    def go(divisor: Int, divisors: List[Int]): List[Int] =
      if (divisor == 0)
        divisors
      else if (n % divisor == 0)
        go(divisor - 1, divisor :: divisors)
      else
        go(divisor - 1, divisors)

    go(n, Nil).length
  }
}

object SolByOthers {
  def divisors(n: Int): Int =
    (1 to n).count(n % _ == 0)
}

class BasicTests extends AnyFlatSpec with Matchers {
  "divisors(1)" should "return 1" in {
    Sol.divisors(1) should be (1)
  }

  "divisors(10)" should "return 4" in {
    Sol.divisors(10) should be (4)
  }

  "divisors(11)" should "return 2" in {
    Sol.divisors(11) should be (2)
  }

  "divisors(36)" should "return 9" in {
    Sol.divisors(36) should be (9)
  }

  "divisors(54)" should "return 8" in {
    Sol.divisors(54) should be (8)
  }
}
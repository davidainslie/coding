package tech.backwards.codewars

import scala.annotation.tailrec
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor3}

/**
 * Given a list and a number, create a new list that contains each number of list at most N times, without reordering.
 * E.g. if the input number is 2,
 *  and the input list is [1,2,3,1,2,1,2,3],
 *  you take [1,2,3,1,2],
 *  drop the next [1,2] since this would lead to 1 and 2 being in the result 3 times,
 *  and then take 3, which leads to [1,2,3,1,2,3].
 *
 * With list [20,37,20,21] and number 1, the result would be [20,37,21].
 */
object EnoughIsEnough {
  def deleteNth(elements: List[Int], maxOccurrences: Int): List[Int] = {
    @tailrec
    def go(xs: List[Int], counts: Map[Int, Int], acc: List[Int]): List[Int] =
      xs match {
        case Nil =>
          acc

        case x :: xs =>
          val count: Int =
            counts.getOrElse(x, 0) + 1

          go(xs, counts + (x -> count), acc ::: (if (count <= maxOccurrences) List(x) else Nil))
      }

    go(elements, Map.empty[Int, Int], Nil)
  }
}

object EnoughIsEnoughOther1 {
  def deleteNth(elements: List[Int], max: Int): List[Int] =
    elements.foldLeft(List.empty[Int]) {
        case (xs, x) if xs.count(_ == x) < max =>
          x :: xs
        case (xs, _) =>
          xs
      }.reverse
}

object EnoughIsEnoughOther2 {
  def deleteNth(elements: List[Int], maxOccurrences: Int): List[Int] =
    elements.zipWithIndex
      .groupBy(_._1)
      .values
      .flatMap(_.take(maxOccurrences))
      .toList
      .sortBy(_._2)
      .map(_._1)
}

object EnoughIsEnoughOther3 {
  def deleteNth(elements: List[Int], maxOccurrences: Int): List[Int] =
    elements.foldLeft(List.empty[Int]) { (acc, x) =>
      if (acc.count(_ == x) < maxOccurrences) acc :+ x else acc
    }
}

class EnoughIsEnoughSpec extends AnyFlatSpec with TableDrivenPropertyChecks with Matchers {
  import EnoughIsEnoughOther3._

  it should "fixed tests" in {
    val fixedTests: TableFor3[List[Int], Int, List[Int]] =
      Table[List[Int], Int, List[Int]](
        ("elements",                                          "n",  "expected"),
        (List(20, 37, 20, 21),                                1,    List(20, 37, 21)),
        (List(1, 1, 3, 3, 7, 2, 2, 2, 2),                     3,    List(1, 1, 3, 3, 7, 2, 2, 2)),
        (List(1, 2, 3, 1, 1, 2, 1, 2, 3, 3, 2, 4, 5, 3, 1),   3,    List(1, 2, 3, 1, 1, 2, 2, 3, 3, 4, 5)),
        (List(1, 1, 1, 1, 1),                                 5,    List(1, 1, 1, 1, 1)),
        (List(),                                              5,    List())
      )

    forAll(fixedTests)(deleteNth(_, _) shouldBe _)
  }
}
package tech.backwards.codewars

/**
 * The Morse code encodes every character as a sequence of "dots" and "dashes".
 * For example,
 *  - letter A is coded as ·−,
 *  - letter Q is coded as −−·−,
 *  - digit 1 is coded as ·−−−−
 *
 * The Morse code is case-insensitive, traditionally capital letters are used.
 * When the message is written in Morse code, a single space is used to separate the character codes and 3 spaces are used to separate words.
 * For example,
 *  - the message HEY JUDE in Morse code is ···· · −·−−   ·−−− ··− −·· ·
 *
 * In addition to letters, digits and some punctuation, there are some special service codes, the most notorious of those is the international distress signal SOS (that was first issued by Titanic),
 *  - that is coded as ···−−−···
 *
 * These special codes are treated as single special characters, and usually are transmitted as separate words.
 */
object MorseDecoder {
  import MorseCodes.morseCodes

  val space: String = " "

  def decode(msg: String): String =
    encodedWords(msg.trim).map(encodedWord).map(_.map(morseCodes).mkString).mkString(space)

  def encodedWords(s: String): List[String] =
    s.trim.split(space * 3).toList

  def encodedWord(s: String): List[String] =
    s.trim.split(space).toList
}

object MorseCodes {
  val morseCodes: Map[String, String] =
    Map(
      "---..." -> ":",
      "...-..-" -> "$",
      "...." -> "H",
      ".-." -> "R",
      ".-.-." -> "+",
      "-...." -> "6",
      ".--." -> "P",
      "-.--.-" -> ")",
      "..--.." -> "?",
      "-.-." -> "C",
      "--.-" -> "Q",
      "..-." -> "F",
      ".-..." -> "&",
      ".--" -> "W",
      "---.." -> "8",
      "..-" -> "U",
      "-." -> "N",
      "-----" -> "0",
      "-....-" -> "-",
      "." -> "E",
      "--..--" -> ",",
      "-..-" -> "X",
      ".----" -> "1",
      "..--.-" -> "_",
      "--." -> "G",
      "-.--." -> "(",
      "-..-." -> "/",
      ".----." -> "'",
      "...-" -> "V",
      "-" -> "T",
      ".--.-." -> "@",
      "---" -> "O",
      ".-" -> "A",
      "....-" -> "4",
      "-.-.-." -> ";",
      "-.." -> "D",
      ".." -> "I",
      ".-..-." -> "\"",
      "-.--" -> "Y",
      "--.." -> "Z",
      "...--" -> "3",
      "..." -> "S",
      "....." -> "5",
      "----." -> "9",
      "--" -> "M",
      ".-.-.-" -> ".",
      "..---" -> "2",
      "...---..." -> "SOS",
      "-.-" -> "K",
      ".-.." -> "L",
      ".---" -> "J",
      "-...-" -> "=",
      "-..." -> "B",
      "--..." -> "7",
      "-.-.--" -> "!"
    )
}

import org.scalatest._
import flatspec._

class ExampleTest extends AnyFlatSpec {
  import MorseDecoder.decode

  val testCases: List[(String, String, String)] =
    List( //(description, input, expected)
      ("the example from the description", ".... . -.--   .--- ..- -.. .", "HEY JUDE"),
      ("the example from the description", ".", "E")
      //add your test cases here
    )

  testCases.foreach {
    case (description, input, expected) =>
      s"$description" should s"return \"$expected\"" in {
        assertResult(expected) {decode(input)}
      }
  }
}
import scala.util.{Try, Success, Failure}

object Main extends App {
  /*
    To run from the CLI:
    $ scalac path/to/Main.scala
  */

  println("//")
  //options()
  //println("probability occurrence " + probability_occurrence(1, 2))
  //exceptions()
  //either()
  //pattern_matching()
  case_class_pattern_matching()

  def case_class_pattern_matching(): Unit = {
    case class Book(title: String, year: Int)
    val book1 = Book("Programming in Scala 3rd ed.", 2016)
    val book2 = Book("Scala Cookbook", 2013)
    val book3 = Book("Functional Programming in Scala", 2014)
  }

  def pattern_matching(): Unit = {
    /*
      "object to match" match {
        case "pattern1" => "statements"
        case "pattern2" => "statements"
        ...
      }
    */
    val numbers = List(1,2,3)

    //-- VERBOSE_WAY --\\ "similar to a switch statement in Java or a series or if-else statements
    if(numbers.nonEmpty && numbers.size >= 2) println(numbers(1)) else println("Empty List")

    val num: Int = 99
    // the pattern match is an expression
    val r = num match {
      case 2 => "base 2"
      case 4 => "base 4"
      case 6 => "base 6"
      case _ => "nothing matched" // without this a scala.MatchError java.lang.Integer exception is thrown
    }
    println(r)
  }

  def exceptions(): Unit = {
    /*
      Attributes:
      - Try()
      - .isSuccess
      - .isFailure
      - Success()
      - Failure()
      -- .getMessage
    */
    val outcome = Try(1 / 0)
    println(outcome)
    println(outcome.isSuccess)
    println(outcome.isFailure)

    // pattern matching syntax
    outcome match {
      case Success(value) => println("__>> Success ^_^")
      case Failure(e) => println("__>> Failure )': " + e.getMessage)
    }
  }

  def either(): Unit = {
    def str2int(in: String): Either[String, Int] = {
      try {
        Right(in.toInt)
      }
      catch {
        case e: NumberFormatException => Left("ERROR: " + e.getMessage)
      }
    }

    val one2int = str2int("1")
    val hi2int = str2int("hi")
    println("one2int = ", one2int)
    println("hi2int = ", hi2int)
  }


  def options(): Unit = {
    /*
      Attributes:
      - .get
      - .find()
      - .isDefined
      - .getOrElse()
    */
    val default_msg = "Employee not Found"
    val programmers = Set("Loc", "Nate", "Julius", "Travis", "Josh")

    // the lambda is referred to as a predicate
    val prog1 = programmers.find(_ == "Brian").getOrElse(default_msg)
    val prog2 = programmers.find(_ == "Julius").getOrElse(default_msg)

    println(prog1)
    println(prog2)
  }

  def probability_occurrence(numerator: Float, denominator: Float) = numerator / denominator


}

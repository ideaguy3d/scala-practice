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
  //case_class_pattern_matching()
  //sequence_pattern_matching()
  //type_pattern_matching()
  guarded_pattern_matching()

  def guarded_pattern_matching(): Unit = {
    case class Email(from: String, body: String)

    val important_emails = Set("hello@world.com", "hi@universe.io")
    val important_email = Email("hi@universe.io", "Hi Universe!")
    val regular_email = Email("sales@marketing.com", "Learn this strange trick")

    def alert_or_not(email: Email): Unit = email match {
      case Email(from, _) if important_emails.contains(from)=> println("This is important")
      case Email(_,_) => println("NOT important")
    }

    alert_or_not(important_email)
    alert_or_not(regular_email)
  }

  def type_pattern_matching(): Unit = {
    case class Trip(to: String)
    case class Car(model: String)
    case class Cash(amount: Int)
    case class NoPrize()

    val raffle = List(NoPrize(), Car("Ford"), NoPrize(), Trip("New Zealand"), Cash(10000), NoPrize(), NoPrize())

    import scala.util.Random

    Random.shuffle(raffle).take(1).head match {
      // if var t is of type "Trip"
      case t: Trip => println(s"You have won a trip to ${t.to}")
      case ca: Cash => println(s"You have won ${ca.amount} cash")
      case c: Car => println(s"You have won a ${c.model}")
      case n: NoPrize => println("Sorry! No Prize")
    }
  }

  def sequence_pattern_matching(): Unit = {
    var numbers = List(1, 2, 3)

    var r1 = numbers match {
      case List(a, b, c) => s"elem b = $b"
      case _ => "elem not found"
    }

    println(r1)

    r1 = numbers match {
      case List(_, _, c) => s"elem c = $c"
      case _ => "elem :c NOT found"
    }

    println(r1)

    numbers = List(1, 2, 3, 11, 22, 33)

    // match not knowing the size of the list
    r1 = numbers match {
      case List(_, elem2, _*) => s"The second elem = $elem2"
      case _ => "elem2 not found :/"
    }

    println(r1)
  }

  def case_class_pattern_matching(): Unit = {
    case class Book(title: String, year: Int)

    val book1 = Book("Programming in Scala 3rd ed.", 2016)
    val book2 = Book("Scala Cookbook", 2013)
    val book3 = Book("Functional Programming in Scala", 2014)

    // match all the fields
    var r1 = book1 match {
      case Book(title, year) => s"$title was printed in $year"
      case _ => "Book not found"
    }

    println(r1)

    // match a few of the fields
    r1 = book1 match {
      case Book(_, year) => s"year $year"
      case _ => "No year found"
    }

    println(r1)
  }

  def pattern_matching(): Unit = {
    /*
      "object to match" match {
        case "pattern1" => "statements"
        case "pattern2" => "statements"
        ...
      }
    */
    val numbers = List(1, 2, 3)

    //-- VERBOSE_WAY --\\ "similar to a switch statement in Java or a series or if-else statements
    if (numbers.nonEmpty && numbers.size >= 2) println(numbers(1)) else println("Empty List")

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

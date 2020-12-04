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
  //guarded_pattern_matching()
  //collection_list()
  //collection_set()
  //collection_map_tuple()
  //numeric_collections()
  //filter_size_convert_collection()
  map_collection_transform()


  def flatmap_collection_transform(): Unit = {

  }

  def map_collection_transform(): Unit = {
      
  }

  def filter_size_convert_collection(): Unit = {
    val page_load_times = List(2, 1, 4, 1, 5, 11, 33, 45, 2, 4, 21)

    val more_than_10secs = page_load_times.filter(sec => sec >= 10)
    val more_than_10secs_compact = page_load_times.filter(_ >= 10)
    val lowest_time_greater_10 = page_load_times.filter(_ >= 10).min
    val worst_time_greater_10 = page_load_times.filter(_ >= 10).max

    var list = List(1, 2, 3)
    var set = Set(1, 2, 3)
    val map = Map(1 -> "Sunday", 2 -> "Monday", 3 -> "Tuesday")

    // there are consistent props & methods for collections
    println(list.isEmpty, list.nonEmpty, set.isEmpty, set.nonEmpty, map.isEmpty, map.nonEmpty)

    list = List(4, 5, 11, 3, 22, 22)
    set = Set(1,2,3,4)
    val list_to_set = list.toSet
    val set_to_list = set.toList

  }

  def numeric_collections(): Unit = {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val sum = numbers.sum
    val product = numbers.product
    val max = numbers.max
    val min = numbers.min
  }

  def collection_map_tuple(): Unit = {
    val map = Map(1 -> "Sunday", 2 -> "Monday", 3 -> "Tuesday")

    val monday = map(2)
    val map_add = map + (4 -> "Wednesday")
    val map_subtract = map - 1

    // iterate over
    map.foreach(entry => println(s"Key; ${entry._1}, Value: ${entry._2}"))

    val tuple = (11, 22, 33)
    val tuple_pos1 = tuple._1 // 11
    val tuple_pos2 = tuple._2 // 22
    val tuple_pos3 = tuple._3 // 33

    val map_join = map ++ Map(4 -> "Wednesday", 5 -> "Thursday")
  }

  def collection_set(): Unit = {
    val set = Set(1, 1, 2, 2, 3, 3, 4, 4)

    val set_add = set + 6
    val set_remove = set - 1
    val set_merge = set ++ Set(99, 34)
  }

  def collection_list(): Unit = {
    /*
      "Attributes"
      .head
      .tail
      .init
      .last
      :+
      +:
      ++
      .drop()
      .dropRight()
      .dropWhile()
    */
    val linked_list = List(1, 2, 3, 4)

    val head = linked_list.head
    val tail = linked_list.tail // all elems except the first elem
    val init = linked_list.init // all elems except the last elem
    val last = linked_list.last

    // creates a copy of the orig linked list
    val append_list = linked_list :+ 5
    val prepend_list = 0 +: linked_list
    val post_merge_list = linked_list ++ List(5, 6, 7)
    val pre_merge_list = List(-2, -1, 0) ++ linked_list
    val drop_from_left = linked_list.drop(1)
    val drop_from_right = linked_list.dropRight(1)
    // higher order function to drop all elems less than 3
    val drop_while = linked_list.dropWhile(_ < 3)

    println(
      "\n" + head + "\n", tail + "\n", init + "\n", last + "\n", append_list + "\n", prepend_list + "\n",
      post_merge_list + "\n", pre_merge_list + "\n", drop_from_left + "\n", drop_from_right + "\n",
      drop_while + "\n"
    )

    /* (
        1
        ,List(2, 3, 4)
        ,List(1, 2, 3)
        ,4
        ,List(1, 2, 3, 4, 5)
        ,List(0, 1, 2, 3, 4)
        ,List(1, 2, 3, 4, 5, 6, 7)
        ,List(2, 3, 4)
        ,List(1, 2, 3)
        ,List(3, 4)
      ) */
  }

  def guarded_pattern_matching(): Unit = {
    case class Email(from: String, body: String)

    val important_emails = Set("hello@world.com", "hi@universe.io")
    val important_email = Email("hi@universe.io", "Hi Universe!")
    val regular_email = Email("sales@marketing.com", "Learn this strange trick")

    def alert_or_not(email: Email): Unit = email match {
      case Email(from, _) if important_emails.contains(from) => println("This is important")
      case Email(_, _) => println("NOT important")
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

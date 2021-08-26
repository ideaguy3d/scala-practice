import java.util.Date
import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}


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
	//map_collection_transform()
	//flatmap_collection_transform()
	//option_flatmap_collection()
	//future_execution_context()
	//filter_collect_futures()
	//other_asynchronous_ops()
	//future_failures()
	cheats()
	
	/*
		~ Cheat Sheet ~
		1. evaluation rules
		2. higher order functions
		3. curring
		4. classes
		5. operators
		6. class hierarchies
		7. class organization
		8. type params
		9. variance
		10. pattern matching
		11. collections
		12. pairs/tuples
		13. ordering
		14. for-comprehensions
	*/
	
	def cheats(): Unit = {
		
		print("\nEvaluation rules\n")
		
		def e1 = 2 // evaluated when called
		val e2 = 2 // evaluated immediately
		lazy val e3 = 2 // evaluated once, when needed
		def sq1(x: Int): Int = x * x // call by val
		def sq2(x: => Int): Int = x * x // call by ref
		def w(x: Int*): String = s"a sequence of ints that can be variable length ${x.length}"
		
		print("Higher Order Functions")
		
		
	}
	
	def future_failures(): Unit = {
		val failed_future = Future { 1 / 0 }
		println(failed_future.value)
		val failed_exception = failed_future.failed
		println(failed_exception.value)
		
		val fallback_future = Future.successful(100)
		// the fallbackTo() has to get chained. It is part of the expression
		val computation = Future { 1 / 0 } fallbackTo (fallback_future)
		println(computation)
		
		val resultFuture = Future { 1 / 0 }
		println(resultFuture)
		// pattern matching
		val future_recover = resultFuture recover { case e: ArithmeticException => 0 }
		println(future_recover)
		
		/*
		  None
		  None
		  Future(Success(100))
		*/
	}
	
	def other_asynchronous_ops(): Unit = {
		// wrapping future values
		val future_1 = Future.successful("success")
		val future_2 = Future.failed(new Exception("fail"))
		
		// using a promise
		val promise = Promise[Int]
		println(promise.future)
		println(promise.success(3710))
		println(promise.future)
		println(promise.future.value)
		
		/*
		  Future(<not completed>)
		  Future(Success(3710))
		  Future(Success(3710))
		  Some(Success(3710))
		*/
	}
	
	def filter_collect_futures(): Unit = {
		val salary = Future {
			Thread.sleep(2)
			250
		}
		
		val lgSalary = salary.filter(_ > 200)
		
		// apply filter & transformation in one operation with pattern match & pattern match guard
		val salaryIncrement = salary.collect { case salary if salary < 5000 => salary + 100 }
		
		Thread.sleep(5)
		
		println(lgSalary)
		println(salaryIncrement)
		
		/*
		  Future(Success(250))
		  Future(Success(350))
		*/
	}
	
	def future_transformations(): Unit = {
		val salary = Future {
			Thread.sleep(2);
			100
		}
		val salaryWithBonus = salary.map(value => value + 500)
		
		println(s"\n\nSalary with Bonus $salaryWithBonus")
		for (elem <- Array(1, 2, 3)) println(elem)
		println(s"Salary with Bonus $salaryWithBonus")
		/*
		  Salary with Bonus Future(<not completed>)
		  1
		  2
		  3
		  Salary with Bonus Future(Success(600))
		*/
		
		
		// Loop over a Future
		val productPrice = Future {
			Thread.sleep(2);
			150
		}
		val productTax = Future {
			Thread.sleep(2);
			150
		}
		val finalPrice = for {
			price <- productPrice
			tax <- productTax
		} yield price + tax
		
		finalPrice.onComplete({
			case Success(finalPrice) => println("\n\n final price success === " + finalPrice)
			case Failure(e) => println(s"\n\n final price failure === $e")
		})
		
		Thread.sleep(4)
		println(finalPrice)
		
		/*
			final price success === 300
			Future(Success(300))
		*/
	}
	
	def future_execution_context(): Unit = {
		
		val future_x = Future {
			Thread.sleep(5);
			21 * 2
		}
		
		println(future_x.isCompleted)
		println(new Date())
		println(new Date())
		println(future_x.isCompleted)
		println(future_x.value + "\n\n")
		
		future_x.onComplete({
			case Success(result) => println(s"on complete result = $result")
			case Failure(e) => println(s"on complete failure = $e")
		})
		
		/*
		  false
		  Thu Dec 03 18:36:04 PST 2020
		  Thu Dec 03 18:36:04 PST 2020
		  true
		  Some(Success(42))
		  on complete result = 42
		*/
		
	}
	
	def option_flatmap_collection(): Unit = {
		def j_toInt(s: String): Option[Int] = {
			try {
				Some(s.toInt)
			}
			catch {
				case e: NumberFormatException => None
			}
		}
		
		val params = List("10", "eight", "5", "four", "3", "20", "one")
		val args_map = params.map(j_toInt)
		println(args_map)
		val args_flatmap = params.flatMap(j_toInt)
		println(args_flatmap)
		val argsMapSum = params.map(j_toInt).flatten.sum
		println(argsMapSum)
		val argsFlatmapSum = params.flatMap(j_toInt).sum
		println(argsFlatmapSum)
		
		/*
		  List(Some(10), None, Some(5), None, Some(3), Some(20), None)
		  List(10, 5, 3, 20)
		  38
		  38
		*/
	}
	
	def flatmap_collection_transform(): Unit = {
		val nested_list = List(List(1, 2, 3, 4), List(4, 5, 6, 7))
		
		val map_map = nested_list.map(inner_list => inner_list.map(_ + 1))
		println(map_map)
		val map_map_flatten = nested_list.map(inner_list => inner_list.map(_ + 1)).flatten
		println(map_map_flatten)
		val flatmap = nested_list.flatMap(inner_list => inner_list.map(_ + 1))
		println(flatmap)
	}
	
	def map_collection_transform(): Unit = {
		case class Event(location: String, dayOfWeek: String, sessionTime: Int, source: String)
		
		val event1 = Event(location = "US", dayOfWeek = "Sun", sessionTime = 10, source = "Twitter")
		val event2 = Event(location = "China", dayOfWeek = "Mon", sessionTime = 15, source = "WeChat")
		val event3 = Event(location = "New Zealand", dayOfWeek = "Sun", sessionTime = 30, source = "Twitter")
		val event4 = Event(location = "US", dayOfWeek = "Tue", sessionTime = 5, source = "Facebook")
		val event5 = Event(location = "US", dayOfWeek = "Thu", sessionTime = 24, source = "LinkedIn")
		val event6 = Event(location = "US", dayOfWeek = "Sat", sessionTime = 60, source = "Facebook")
		
		val events = List(event1, event2, event3, event4, event5, event6)
		
		val locations = events.map(event => event.location)
		val locations_compact = events.map(_.location)
		println(locations) // List(US, China, New Zealand, US, US, US)
		
		val sources = events.map(_.source)
		val days = events.map(_.dayOfWeek)
		println(sources, days) // (List(Twitter, WeChat, Twitter, Facebook, LinkedIn, Facebook),List(Sun, Mon, Sun, Tue, Thu, Sat))
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
		set = Set(1, 2, 3, 4)
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

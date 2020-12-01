import java.io

/*
import scala.List
import scala.Array
*/

// to run from the CLI relative to "scala-practice":
// $ :load src/Hello.scala
// $ val/var hello = new Hello
// $ hello.some_method_name()
class Hello(
             var numbers: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
             var num_arr: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
             val numbers_set: Set[Int] = Set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
             val num_list: List[Int] = List(1, 2, 3),
             alpha_list: List[String] = List("a", "b", "c")
           ) {

  // re-watch AND work through the "Loops" vid
  def generator_expressions_one(lim: Int = 0): Unit = {

    val lambda_one = for {
      num <- num_list
      letter <- alpha_list
      // deprecated way: num + "=>" + letter
    } yield s"$letter => $num"


    println(lambda_one)


    // 1. VERBOSE_WAY
    for (num <- numbers) {
      if (num % 2 == 0) {
        // num + " is even,"
        println(s"$num is even, ")
      }
    }
    // 2. expressive way
    for (num <- numbers if (num % 2 == 0)) println(s"$num is even, ")
    // 3. multiple branch's
    for (
      num <- num_arr
      if (num % 2 == 0)
      if (num > 4)
    ) println(s"$num is greater than 4 & is even. ")

  }

  def generator_expressions_two(lim: Int): Unit = {

    // 1. VERBOSE_WAY
    for (num <- num_list) {
      for (letter <- alpha_list) {
        println(letter + " =>" + num)
      }
    }
    // 2. expressive way
    for {
      num <- num_list
      letter <- alpha_list
    } println(letter + " _> " + num)


    val gen_one = for (num <- numbers_set) yield num ^ 2
    println(gen_one)

    val gen_two = for (num <- num_arr if (num % 3 == 3)) yield num * 2
    println(gen_two.mkString("Array(", ", ", ")"))


    // loop inner loop syntax
    println("Loop with inner Loop")
    val gen_thr = for {
      num <- num_list if (num % 2 == 0)
      letter <- alpha_list if (num > 1)
    } yield (letter + " => " + num)
    println(gen_thr)


    // anonymous function
    val plus_five = (x: Int, y: Int) => x + y + 5
  }

  // This doesn't work at the moment :(
  def practice_data_one: Map[String, Object] = {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    val numbers_arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    val numbers_set = Set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    val num_list = List(1, 2, 3)
    val alpha_list = List(1, 2, 3)

    //for (num <- numbers) println(num + " num works fine.")

    Map(
      ("numbers", numbers), "numbers_arr" -> numbers_arr,
      "numbers_set" -> numbers_set, "num_list" -> num_list,
      "alpha_list" -> alpha_list
    )
  }
}

/*
class HelloStatus extends App {
  private val msg_status = "Ready to test"
}*/

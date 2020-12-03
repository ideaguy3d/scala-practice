// :load src/Hello.scala

println("Hello World !! ^_^/")
//TODO: re-watch "Higher Order Functions" after leaning about case classes


case class Course(title: String, subject: String)


def case_class(): Unit = {
  val course1 = Course("Scala Programming", "Data Engineering")
  val course2 = Course("Scala Programming", "Data Engineering")
  val course3 = Course("Spark App Development", "Distributed Computing")
  // using .copy()
  val course4 = course1.copy(subject = "Functional Programming")
  println("course1 == course2 ", course1 == course2)
  println("course4 ", course4)
}





// Practicing Scala basics and functions
def basics_and_functions(): Unit = {

  val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
  val numbers_arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
  val numbers_set = Set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
  val num_list = List(1, 2, 3)
  val alpha_list = List(1, 2, 3)


  val lambda_one = for {
    num <- num_list
    letter <- alpha_list
  } yield num + "=>" + letter


  println(lambda_one)

  // for-if
  // 1. VERBOSE_WAY
  for (num <- numbers) {
    if (num % 2 == 0) {
      println(num + " is even,")
    }
  }
  // 2. expressive way
  for (num <- numbers if (num % 2 == 0)) println(num + " is even,")
  // 3. multiple branch's
  for (
    num <- numbers_arr
    if (num % 2 == 0)
    if (num > 4)
  ) println(num + " is greater than 4 & is even. ")



  // for-inner-for
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

  val gen_two = for (num <- numbers_arr if (num % 3 == 3)) yield num * 2
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



















//
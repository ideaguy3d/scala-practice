object Main extends App {
  /*
    To run from the CLI:
    $ scalac
  */

  println("//")
  options()
  println("probability occurrence " + probability_occurrence(1, 2))

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

class Programmer(firstName: String, lastName: String) {
  def getName: String = s"$firstName $lastName"
}

object Programmer {
  // the apply() makes using "new" not needed.
  def apply(firstName: String, lastName: String) = new Programmer(firstName, lastName)
}

/* From the CLI:

scala> :paste
// Entering paste mode (ctrl-D to finish)
...
// Exiting paste mode, now interpreting.

class Programmer
object Programmer

scala> val j = Programmer("J", "Hernandez")
val j: Programmer = Programmer@6121a7dd

scala> j.getName
val res0: String = J Hernandez

*/

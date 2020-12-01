
println("Hello World !! ^_^/")

val numbers = List(1,2,3,4,5,6,7,8,9,10,11,12)
val numbers_arr = Array(1,2,3,4,5,6,7,8,9,10,11,12)
val numbers_set = Set(1,2,3,4,5,6,7,8,9,10,11,12)
val num_list = List(1,2,3)
val alpha_list = List(1,2,3)

val lambda_one = for {
  num <- num_list
  letter <- alpha_list
} yield num + "=>" + letter


println(lambda_one)


//
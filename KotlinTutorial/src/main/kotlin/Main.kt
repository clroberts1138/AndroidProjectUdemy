fun main(args: Array<String>) {
    println("Hello World")
    println("My first Kotlin program")

    var tim: String
    tim = "Time Buchalka"
    println(tim)

    var timsWeeklySalary: Int = 32
    var timsMonthlySalary: Int = timsWeeklySalary * 4
    println(timsWeeklySalary)
    println(timsMonthlySalary)

    println("Tim's weekly salary is: $${timsWeeklySalary}")
    println("Tim's monthly salary is: $${timsWeeklySalary} * 4 = $${timsWeeklySalary * 4}")
    println()

    val apples : Int = 6
    println("There are a total of ${apples} apples")
    val oranges : Int = 5
    println("There are a total of ${oranges}")
  //  val fruit: Int = apples - oranges

    println("If you take ${apples} apples away from ${oranges}, you get ${apples - oranges} piece of fruit")
 //   println(fruit)

    println("A quarter of the apples is ${apples / 4}")

    println()

    val weeks: Int = 130
    val years: Double = weeks / 52.0
    println("${weeks} weeks is ${years} years")
    println("My name is ${tim}")

}
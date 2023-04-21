package medium

object Main {
  def main(args: Array[String]): Unit = {
    val just       = Just(200)
    val justResult = just.map(_ + 100)

    val nothing       = Nothing
    val nothingResult = nothing.map[Int, Int](_ + 100)

    println(justResult)
    println(nothingResult)
  }
}

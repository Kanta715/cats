package base

object Main {

  def main(args: Array[String]): Unit = {
    println(new OEq[String].eqv("a", "b"))
  }
}

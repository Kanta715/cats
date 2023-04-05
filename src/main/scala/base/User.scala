package base

case class User(name: String, age: Int) extends cats.Eq[User] {

  /**
   * Eq 型クラスを利用することで独自の等価性を定義できる
   * この場合は name が等しい場合に等価とする
   */
  def eqv(x: User, y: User): Boolean = x.name == y.name
}

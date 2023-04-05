package base

class OEq[A] extends cats.Eq[A] {
  def eqv(x: A, y: A): Boolean = x == y
}

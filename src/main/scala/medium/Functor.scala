/**
 * Functor is a type class that defines a single function fmap.
 */

package medium

trait Functor[F[_]] {
  def fmap[A, B](f: A => B)(fa: F[A]): F[B]
}

/**
 * Functor abstract class: Maybe
 */
sealed abstract class Maybe[+A] extends Functor[Maybe] {
  def fmap[A, B](f: A => B)(fa: Maybe[A]): Maybe[B] = fa match {
    case Just(a) => Just(f(a))
    case Nothing => Nothing
  }
}

/**
 * Functor concrete class: Just
 */
case class Just[A](v: A) extends Maybe[A] {
  def map[B](f: A => B): Maybe[B] = fmap(f)(this)
}

/**
 * Functor concrete class: Nothing
 */
case object Nothing extends Maybe[Nothing] {
  def map[A, B](f: A => B): Maybe[B] = fmap(f)(this)
}

package functor

case class NB[A](private val v: A) {
  def getValue: A = v
}

// implicits class はクラスを拡張する
object NBSyntax {
  implicit class NBApplicativeOps[A](fa: NB[A]) extends cats.Applicative[NB] {

    override def pure[A](x: A): NB[A] = NB(x)

    override def ap[A, B](ff: NB[A => B])(fa: NB[A]): NB[B] = NB(ff.getValue(fa.getValue))

    override def map2[A, B, Z](fa: NB[A], fb: NB[B])(f: (A, B) => Z): NB[Z] = NB(f(fa.getValue, fb.getValue))

    def map[B](f: A => B): NB[B] = NB(f(fa.getValue))

    def toGmail: String = s"${fa.getValue}@gmail.com"
  }
}

// implicits def は型を変換する
object NBInstances extends cats.Applicative[NB] {
  implicit def findKind[A](nb: NB[A]): String = nb.getValue match {
    case _: Int    => "Int"
    case _: String => "String"
    case _         => "Other"
  }

  implicit def p[A](x: A) = pure(x)

  implicit def apply[A, B](ff: NB[A => B])(fa: NB[A]) = ap(ff)(fa)

  implicit def mapTwo[A, B, Z](fa: NB[A], fb: NB[B])(f: (A, B) => Z) = map2(fa, fb)(f)

  override def pure[A](x: A): NB[A] = NB(x)

  override def ap[A, B](ff: NB[A => B])(fa: NB[A]): NB[B] = NB(ff.getValue(fa.getValue))

  override def map2[A, B, Z](fa: NB[A], fb: NB[B])(f: (A, B) => Z): NB[Z] = NB(f(fa.getValue, fb.getValue))
}

object OptionInstance extends cats.Applicative[Option] {
  override def pure[A](x: A): Option[A] = Option(x)

  override def ap[A, B](ff: Option[A => B])(fa: Option[A]): Option[B] = ff.flatMap(f => fa.map(f))

  override def map2[A, B, Z](fa: Option[A], fb: Option[B])(f: (A, B) => Z): Option[Z] = (fa, fb) match {
    case (Some(a), Some(b)) => Some(f(a, b))
    case _                  => None
  }

  def applyTwice[A](f: A => A)(opt: Option[A]): Option[A] = opt match {
    case Some(a) => Some(f(f(a)))
    case None    => None
  }
}


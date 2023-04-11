package functor

import NBSyntax._
import NBInstances._
import OptionInstance._

object Main {

  def main(args: Array[String]): Unit = {

    // implicits class, def もあまりやれることは変わらないが、class は特定のクラスの関数を拡張する

    /**
     * class はクラスのみにしか使わない拡張関数を定義できる
     * ドメインクラスに関係のない処理などで特定の時にあったら便利な関数とかを定義？
     */

    // NB クラスにはないメソッドを追加できる
    // class
    val nb       = NB[Int](1)
    val mapValue = nb.map[Int](_ + 1)

    val pure     = nb.pure(1000)

    val nb2      = NB[String]("nbTest")
    val gmail    = nb2.toGmail

    println(mapValue)
    println(pure)
    println(gmail)

    println

    // -------------------------------------

    /**
     * def は型変換を汎用的に使いたいものを定義する
     * 使い道がありすぎてどういう時というのは限定できないが、
     */
    // クラスから生えているメソッドとして扱われない
    // 勝手に求めている型に合った implicit def を探して適用してくれる
    // 同じ名前の implicit def があるとコンパイルエラーになる
    // def
    val pure2:  NB[Int] = p(100)
    val apply2: NB[Int] = apply(NB[Int => Int](_ + 100))(pure2)
    val map2:   NB[Int] = mapTwo(pure2, apply2)(_ + _)

    println(pure2)
    println(apply2)
    println(map2)

    println

    // -------------------------------------
    // def は既存の型に便利なメソッドを生やすのもあり
    val kind: String = findKind(nb)
    println(kind)

    val twice = applyTwice[Int](_ + 100)(Some(100))
    println(twice)
  }
}

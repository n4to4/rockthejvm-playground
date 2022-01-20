object MonoidsInCategoryOfEndofunctors {
  // "monoid in the category of T[_]"
  trait MonoidInCategoryK2[T[_], ~>[_[_], _[_]], U[_], P[_]] {
    def unit: U ~> T // same as ~>[U, T]
    def combine: P ~> T
  }

  // "monoid in a monoidal category" = "monoid in the category of T"
  trait MonoidInCategory[T, ~>[_, _], U, P] {
    def unit: U ~> T // same as ~>[U, T]
    def combine: P ~> T
  }

  trait GeneralMonoid[T, U, P] extends MonoidInCategory[T, Function1, U, P] {
    // def unit: U => T
    // def combine: P => T
  }

  trait FunctionalMonoid[T] extends GeneralMonoid[T, Unit, (T, T)] {
    // def unit: Unit => T
    // def combine: ((T, T)) => T
  }

  // monoid
  trait Monoid[T] extends FunctionalMonoid[T] {
    // "public" API
    def empty: T
    def combine(a: T, b: T): T

    // "hidden" API
    def unit = _ => empty
    def combine = t => combine(t._1, t._2)
  }

  object IntMonoidAdd extends Monoid[Int] {
    override def empty: Int = 0
    override def combine(a: Int, b: Int): Int = a + b
  }

  // endofunctors
  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  given functorList: Functor[List] with {
    def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)
  }

  // functor transformations
  trait MyFunction1[-A, +B] {
    def apply(a: A): B
  }

  trait FunctorNatTransformation[-F[_], +G[_]] {
    def apply[A](fa: F[A]): G[A]
  }

  object ListToOptionTrans extends FunctorNatTransformation[List, Option] {
    override def apply[A](fa: List[A]): Option[A] = fa.headOption
  }

  // .toList, .toOption, .toEither, .toTry

  // the id functor
  type Id[A] = A

  given idFunctor: Functor[Id] with {
    def map[A, B](fa: A)(f: A => B): B = f(fa)
  }

  def main(args: Array[String]): Unit = {
    println("main")
  }
}

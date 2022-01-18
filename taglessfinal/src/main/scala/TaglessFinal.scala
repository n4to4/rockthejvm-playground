object TaglessFinal {
  // expression problem
  object ExpressionProblem {
    trait Expr
    case class B(boolean: Boolean) extends Expr
    case class Or(left: Expr, right: Expr) extends Expr
    case class And(left: Expr, right: Expr) extends Expr
    case class Not(expr: Expr) extends Expr

    val aGiantBoolean: Expr = Or(And(B(true), B(false)), B(false))

    def eval(expr: Expr): Boolean = expr match {
      case B(b)      => b
      case Or(a, b)  => eval(a) || eval(b)
      case And(a, b) => eval(a) && eval(b)
      case Not(e)    => !eval(e)
    }
  }

  def main(args: Array[String]): Unit = {}
}

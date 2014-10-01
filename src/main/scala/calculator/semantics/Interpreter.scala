package calculator

import calculator.ir._

package object semantics {
  def eval(ast: AST): Int = ast match {
    case Num(i) ⇒ i
  }
}
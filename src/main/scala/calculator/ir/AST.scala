package calculator.ir

/**
 * -----------
 * Grammar
 * -----------
 * 
 *                   n ∈ 𝒵 
 * 
 *       e ∈ Expr ::= n | e + e
 *  
 */

sealed abstract class AST
sealed abstract class Expr extends AST

case class Num(n: Int) extends Expr
case class Plus(left: Expr, right: Expr) extends Expr

package calculator.ir

/**
 * -----------
 * Grammar
 * -----------
 * 
 *  n ∈ 𝒵
 *  
 */

abstract class AST

case class Num(n: Int) extends AST

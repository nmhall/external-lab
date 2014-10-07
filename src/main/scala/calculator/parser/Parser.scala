package calculator.parser

import scala.util.parsing.combinator._
import calculator.ir._

/**
 * -----------
 * Grammar
 * -----------
 * 
 *                   n ∈ 𝒵 
 * 
 *       e ∈ Expr ::= e + t | e - t | t
 *       t ∈ Term ::= t * f | t / f | f
 *       f ∈ Fact ::= n | ( e )
 *  
 */

object CalcParser extends JavaTokenParsers with PackratParsers {

    // parsing interface
    def apply(s: String): ParseResult[AST] = parseAll(expr, s)

    // expressions
    lazy val expr: PackratParser[Expr] = 
      (   expr~"+"~term ^^ {case l~"+"~r ⇒ l |+| r}
      	| expr~"-"~term ^^ {case l~"-"~r ⇒ l |-| r}
        | term )
        
    // factors
    lazy val fact: PackratParser[Expr] =
      (number
       | "("~expr~")" ^^ {case "("~f~")" ⇒ Fact(f)} )
      
    lazy val term: PackratParser[Expr] =
      (	 term~"*"~fact ^^ {case l~"*"~r ⇒ l |*| r}
       | term~"/"~fact ^^ {case l~"/"~r ⇒ l |/| r}
       | fact )
      
    // numbers
    def number: Parser[Num] = wholeNumber ^^ {s ⇒ Num(s.toInt)}
    
 }

package calculator.parser

import scala.util.parsing.combinator._
import calculator.ir._

object CalcParser extends JavaTokenParsers {

    // parsing interface
    def apply(s: String): ParseResult[AST] = parseAll(number, s)

    // numbers
    def number: Parser[Num] = wholeNumber ^^ {s ⇒ Num(s.toInt)}
    
 }


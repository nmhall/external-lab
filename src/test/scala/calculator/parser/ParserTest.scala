package calculator.parser

import org.scalatest._

import calculator.ir._
import calculator.parser._
import edu.hmc.langtools._

class NumTests extends FunSpec with LangParseMatchers[AST] {

  override val parser = CalcParser.apply _
  
  describe("A number") {

    it("can be a single digit") {
      program("1") should parseAs ( Num(1) )
    }
    
    it ("can be multiple digits") {
      program("10") should parseAs ( Num(10) )
      program("121") should parseAs ( Num(121) )
    }
    
    it ("can be a negative number") {
      program("-10") should parseAs ( Num(-10) )
    }
    
    it ("can be floating-point number") {
      program("1.1") should not (parse)
      program(" .3") should not (parse)
    }

  }
}

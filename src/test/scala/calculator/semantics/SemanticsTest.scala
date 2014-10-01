package calculator.semantics

import org.scalatest._

import calculator.ir._
import calculator.parser._
import calculator.semantics._
import edu.hmc.langtools._

class NumSemanticsTests extends FunSpec 
  with LangInterpretMatchers[AST, Int] {

    override val parser      = CalcParser.apply _
    override val interpreter = eval _
 
    describe("A number") {

      it("should evaluate to an integer") {
        program("1")   should compute (   1 )
        program("10")  should compute (  10 )
        program("121") should compute ( 121 )
        program("-10") should compute ( -10 )
      }

  }
}
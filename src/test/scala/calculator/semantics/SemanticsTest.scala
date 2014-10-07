package calculator.semantics

import org.scalatest._

import calculator.ir._
import calculator.parser._
import calculator.semantics._
import edu.hmc.langtools._

class NumSemanticsTests extends FunSpec
    with LangInterpretMatchers[AST, Int] {

  override val parser = CalcParser.apply _
  override val interpreter = eval _

  describe("A number") {

    it("should evaluate to an integer") {
      program("1") should compute (1)
      program("10") should compute (10)
      program("121") should compute (121)
      program("-10") should compute (-10)
    }

  }

  describe("Addition") {

    it("can add two numbers") {
      program("1 + 1") should compute (2)
    }

    it("can be chained (and is left-associative)") {
      program("1 + 2 + 100") should compute (103)
    }

    it("can handle negative numbers") {
      program("1 + -1") should compute (0)
    }

  }
  
  describe("Subtraction") {
    it("can subtract a small number from a big number") {
      program("3 - 2") should compute (1)
    }
    
    it("can subtract a big number from a small number") {
      program("2 - 4") should compute (-2)
    }
    
    it("can be chained (and is left-associative)") {
      program("4 - 5 - 2") should compute (-3)
    }
    
    it("can handle negative numbers"){
      program("-1 - -1") should compute (0)
    }
  }
  
  describe("Multiplication") {
    it("can multiply two numbers") {
      program("3 * 2") should compute (6)
    }
    
    it("can be chained (and is left-associative)") {
      program("4 * 3 * 2") should compute (24)
    }
    
    it("can handle negative numbers") {
      program("-4 * 3") should compute (-12)
      program("-4 * -3") should compute (12)
    }
    
    it("takes precedence over addition") {
      program("3 * 2 + 1") should compute (7)
      program ("1 - 3 * 2") should compute (-5)
    }
  }
  
  describe("Division") {
    it("can divide a number by its divisor") {
      program("6 / 3") should compute (2)
    }
    
    it("truncates for integer division") {
      program("6 / 4") should compute (1)
      program("5 / 6") should compute (0)
    }
    
    it("can handle negative numbers") {
      program("-6 / 3") should compute (-2)
      program("-6 / -3") should compute (2)
    }
    
    it("takes precedence over subtraction") {
      program("2 + 4 / 2") should compute (4)
      program("2 - 4 / 2") should compute (0)
    }
  }
  
  describe("Parentheses") {
    it("establishes precedence") {
      program("(1 + 2) * 3") should compute (9)
      program("1 + 2 * 3") should compute (7)
    }
    
    it("should be nestable") {
      program("(2 * (1 + 3)) * (1 - (2 - 3))") should compute (16)
    }
  }

}

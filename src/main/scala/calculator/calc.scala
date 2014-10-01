package calculator

import scala.tools.nsc.EvalLoop
import calculator.parser.CalcParser

object Calculator extends EvalLoop with App {
  override def prompt = "> "

  loop { line ⇒
    CalcParser(line) match {
      case CalcParser.Success(t, _) ⇒ println(t)
      case e: CalcParser.NoSuccess  ⇒ println(e)
    }
  }
}

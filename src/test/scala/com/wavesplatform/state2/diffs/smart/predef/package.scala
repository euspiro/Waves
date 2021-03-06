package com.wavesplatform.state2.diffs.smart

import com.wavesplatform.lang.{Evaluator, TypeInfo}
import com.wavesplatform.utils.dummyTypeCheckerContext
import fastparse.core.Parsed.Success
import monix.eval.Coeval
import scorex.transaction.Transaction
import scorex.transaction.smart.BlockchainContext
package object predef {
  val networkByte: Byte = 'u'
  def runScript[T: TypeInfo](script: String, tx: Transaction = null): Either[String, T] = {
    val Success(expr, _) = com.wavesplatform.lang.Parser(script)
    val Right(typedExpr) = com.wavesplatform.lang.TypeChecker(dummyTypeCheckerContext, expr)
    Evaluator[T](BlockchainContext.build(networkByte, Coeval(tx), Coeval(???), null), typedExpr)
  }
}

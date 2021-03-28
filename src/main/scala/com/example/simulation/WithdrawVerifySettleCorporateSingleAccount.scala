package com.example.simulation

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.language.postfixOps


/**
 * @author : mahmoud
 * @since : 2/4/20
 */
//noinspection DuplicatedCode
object WithdrawVerifySettleCorporateSingleAccount {

  val count = 100000

  private val transaction_ids = for (i <- 0 until count) yield {
    Map("transaction_id" -> (System.nanoTime().toString + s"$i"))
  }

  def scnWithdrawCorporate: ScenarioBuilder = scenario("single corporate wallet")
    .feed(transaction_ids)
    .exec {
      http("withdraw from corporate wallet")
        .post("/money/api/v1/wallet/corporate/withdraw")
        .body(ElFileBody("withdraw_corporate_wallet_single.json"))
        .header("Authorization", "Bearer " + TokenHolder.token)
        .check(status.is(200))
    }
    .exec {
      http("verify transaction")
        .post("/money/api/v1/wallet/corporate/transaction/verify")
        .body(ElFileBody("verify_transaction_corporate_wallet.json"))
        .header("Authorization", "Bearer " + TokenHolder.token)
        .check(status.is(200))
    }
    .exec {
      http("settle transaction")
        .post("/money/api/v1/wallet/corporate/transaction/settle")
        .body(ElFileBody("settle_transaction_corporate_wallet.json"))
        .header("Authorization", "Bearer " + TokenHolder.token)
        .check(status.is(200))
    }

}

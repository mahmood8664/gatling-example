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
object CreateWithdrawVerifySettleCorporateMultiAccount {

  private val walletFrom: Int = sys.env.getOrElse("WALLET_FROM", "130100").toInt
  private val walletTo: Int = sys.env.getOrElse("WALLET_TO", "130200").toInt

  private val transaction_ids = for (i <- walletFrom + 100000000 until walletTo + 100000000) yield {
    Map("transaction_id" -> (System.nanoTime().toString + s"$i"))
  }

  private val deposit_transaction_id = for (i <- walletFrom + 300000000 until walletTo + 300000000) yield {
    Map("deposit_transaction_id" -> (System.nanoTime().toString + s"$i"))
  }

  private val corporate_ids = for (i <- walletFrom until walletTo) yield {
    Map("corporate_id" -> s"$i")
  }

  def scnCreateWithdrawVerifySettleCorporate: ScenarioBuilder = scenario("multi corporate wallet")
    .feed(transaction_ids)
    .feed(deposit_transaction_id)
    .feed(corporate_ids)
    .exec {
      http("create corporate wallet")
        .post("/composite/api/v1/wallet/corporate")
        .body(ElFileBody("create_corporate_wallet.json"))
        .header("Authorization", "Bearer " + TokenHolder.token)
        .check(status.is(200))
    }
    .exec {
      http("deposit to corporate wallet")
        .post("/money/api/v1/wallet/corporate/deposit")
        .body(ElFileBody("deposit_corporate_wallet.json"))
        .header("Authorization", "Bearer " + TokenHolder.token)
        .check(status.is(200))
    }
    .exec {
      http("withdraw from corporate wallet")
        .post("/money/api/v1/wallet/corporate/withdraw")
        .body(ElFileBody("withdraw_corporate_wallet_multi.json"))
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

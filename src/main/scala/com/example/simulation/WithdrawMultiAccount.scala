package com.example.simulation

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.language.postfixOps


/**
 * @author : mahmoud
 * @since : 2/4/20
 */
//noinspection DuplicatedCode
object WithdrawMultiAccount {

  val count = 100000
  val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJQR19DTElFTlQiLCJQR19SRVBPUlRFUiIsIlBHX09QRVJBVElPTiJdLCJleHAiOjE1ODI5Njk5NTcsImp0aSI6ImZkZTljMTM0LThiYTItNGExNy04YTZmLWZmODMzZmI0YzNlMyIsImNsaWVudF9pZCI6InNuYXBwX2NoYXJjaCJ9.Y_KqmHmhsPN0n0otuKxxBiqSwC61UuVbt9VJ9OEUs0sAJbMT8hkPOVRtncfRvWxkLydLUwsgoUYdlOPXGt7PwBiD7XpCBgdT5jgmUiK-Q8rIiqoiR_8mlznFnJChtajiBSo77VEWA1tZodGgena-GPgdp28pcVNAylqwAsBGt0U"
  private val transaction_ids = for (i <- 0 until count) yield {
    Map("transaction_id" -> (System.nanoTime().toString + s"$i"))
  }

  private val corporate_ids = for (i <- 1 until count) yield {
    Map("corporate_id" -> s"$i")
  }

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(sys.env.getOrElse("BASE_URL", "http://money-management.fintech-development.svc:8084"))
//        .baseUrl(sys.env.getOrElse("BASE_URL", "http://localhost:8084"))
    .acceptHeader("*/*")
    .contentTypeHeader("application/json")
  //    .maxConnectionsPerHost(100)

  def scnWithdrawCorporate: ScenarioBuilder = scenario("multi corporate wallet")
    .feed(transaction_ids)
    .feed(corporate_ids)
    .exec {
      http("withdraw from corporate wallet")
        .post("/api/v1/wallet/corporate/withdraw")
        .body(ElFileBody("withdraw_corporate_wallet_multi.json"))
        .header("Authorization", "Bearer " + token)
        .check(status.is(200))
    }
//    .exec {
//      http("verify transaction")
//        .post("/api/v1/wallet/corporate/transaction/verify")
//        .body(ElFileBody("verify_transaction_corporate_wallet.json"))
//        .header("Authorization", "Bearer " + token)
//        .check(status.is(200))
//    }
//    .exec {
//      http("settle transaction")
//        .post("/api/v1/wallet/corporate/transaction/settle")
//        .body(ElFileBody("settle_transaction_corporate_wallet.json"))
//        .header("Authorization", "Bearer " + token)
//        .check(status.is(200))
//    }

}

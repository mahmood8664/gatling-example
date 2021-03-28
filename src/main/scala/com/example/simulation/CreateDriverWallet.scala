package com.example.simulation

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

/**
 * @author : mahmoud
 * @since : 1/4/20
 */
object CreateDriverWallet {

  private val ibans = csv(sys.env.getOrElse("CREATE_WALLET_IBANS_PARTIES", "create_wallet_ibans_parties.csv"))

  val scnCreateWallet: ScenarioBuilder = scenario("Create Wallet")
    .feed(ibans)
    .exec(http("create wallet")
      .post("/money/api/v1/wallet/driver")
      .body(ElFileBody("create_wallet.json"))
      .header("Authorization", "Bearer " + TokenHolder.token)
      .check(status.is(200))
      .check(jsonPath("$..status").is("ACTIVE"))
      .check(jsonPath("$..financialAccountNumber").exists))

}

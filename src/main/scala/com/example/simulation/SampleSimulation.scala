package com.example.simulation

import io.gatling.core.Predef._

import scala.language.postfixOps

/**
 * @author : mahmoud
 * @since : 1/1/20
 */
class SampleSimulation extends Simulation {

  //  private val baseUrl = sys.env.getOrElse("BASE_URL", "http://localhost:8080")
  //  private val username = sys.env.getOrElse("FIN_USER", "user")
  //  private val password = sys.env.getOrElse("FIN_PASSWORD", "pass")
  //
  //  private val result = Http(baseUrl + "/uaa/api/v1/client/login")
  //    .postData(s"""{"username": "$username","password": "$password"}""")
  //    .header("Content-Type", "application/json")
  //    .header("Charset", "UTF-8")
  //    .option(HttpOptions.readTimeout(10000)).asString
  //
  //  private val userNumbers: Int = sys.env.getOrElse("NUMBER_OF_USER", "100").toInt
  //  var token: String = ""
  //  if (result.code != 200) {
  //    throw new Error("cannot login to get token")
  //  }
  //  TokenHolder.token = new ObjectMapper().readTree(result.body).at("/value").asText

  setUp(
    //    GetParty.scnGetParties.inject(atOnceUsers(userNumbers)).protocols(HttpBase.httpProtocol),
    //    CreateWallet.scnCreateWallet.inject(atOnceUsers(userNumbers)).protocols(HttpBase.httpProtocol),
    //    CreateAndSettleInvoice.scnCreateAndSettleInvoice.inject(atOnceUsers(userNumbers)).protocols(HttpBase.httpProtocol),
    //    DriverTransactionReport.scnDriverTransaction.inject(atOnceUsers(userNumbers)).protocols(HttpBase.httpProtocol),
    //    WithdrawVerifySettleCorporateSingleAccount.scnWithdrawCorporate.inject(atOnceUsers(110)).protocols(HttpBase.httpProtocol),
    //    WithdrawMultiAccount.scnWithdrawCorporate.inject(atOnceUsers(4000)).protocols(WithdrawMultiAccount.httpProtocol),
    //    Scenarios.searchScenario.inject(atOnceUsers(10)).protocols(HttpBase.httpProtocol)
    TestReactive.scnTestReactive.inject(rampUsers(20000) during 3).protocols(TestReactive.httpProtocol),
    //    CreateWithdrawVerifySettleCorporateMultiAccount.scnCreateWithdrawVerifySettleCorporate.inject(atOnceUsers(userNumbers)).protocols(HttpBase.httpProtocol),
  )

}

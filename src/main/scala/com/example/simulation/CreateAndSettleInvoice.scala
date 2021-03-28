package com.example.simulation

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

/**
 * @author : mahmoud
 * @since : 1/4/20
 */
object CreateAndSettleInvoice {

  private val invoice_ids = csv(sys.env.getOrElse("INVOICE_DATA", "invoice_data.csv"))
  val scnCreateAndSettleInvoice: ScenarioBuilder = scenario("Create and Settle Invoice")
    .feed(invoice_ids)
    .exec {
      http("create invoice")
        .post("/money/api/v1/wallet/invoice")
        .body(ElFileBody("create_invoice.json"))
        .header("Authorization", "Bearer " + TokenHolder.token)
        .check(status.is(201))
    }
    .exec {
      http("settle invoice")
        .post("/money/api/v1/wallet/invoice/settlement")
        .body(ElFileBody("settle_invoice.json"))
        .header("Authorization", "Bearer " + TokenHolder.token)
        .check(status.is(200))
    }

}

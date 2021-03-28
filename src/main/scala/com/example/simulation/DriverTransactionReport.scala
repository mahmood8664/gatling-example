package com.example.simulation

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

/**
 * @author : mahmoud
 * @since : 1/4/20
 */
object DriverTransactionReport {

  private val driver_feed = csv(sys.env.getOrElse("DRIVER_ID_TRX", "driver_id_trx.csv")).circular

  val scnDriverTransaction: ScenarioBuilder = scenario("driver transaction")
    .feed(driver_feed)
    .exec(http("driver transactions")
      .get("/money/api/v1/transactions/driver/history")
      .queryParam("driverId", "${driver_id}")
      .queryParam("pageNumber", "0")
      .queryParam("pageSize", "10")
      .header("Authorization", "Bearer " + TokenHolder.token)
      .check(status.is(200)))

}

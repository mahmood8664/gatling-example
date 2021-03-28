package com.example.simulation

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

/**
 * @author : mahmoud
 * @since : 12/28/19
 */
object GetParty {

  private val feeder = csv("party.csv")

  val scnGetParties: ScenarioBuilder = scenario("get party")
    .feed(feeder)
    .exec {
      http("get parties")
        .get("/accounts/api/v1/party")
        .queryParam("ids", "${party_id}")
        .header("Authorization", "Bearer " + TokenHolder.token)
        .check(status.is(200))
        .check(jsonPath("$..partiesDetails").count.is(1))
    }
}

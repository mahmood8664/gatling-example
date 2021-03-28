package com.example.simulation

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

/**
 * @author : mahmoud
 * @since : 12/28/19
 */
object Login {

  val loginJson: String = "login.json"
  val scnLogin: ScenarioBuilder = scenario("login")
    .exec(
      http("login request")
        .post("/uaa/api/v1/client/login")
        .body(RawFileBody(loginJson))
        .check(status.is(200))
        .check(jsonPath("$..value").saveAs("token"))
    )
    .exec { session: Session =>
      token = session("token").toString
      session
    }
  var token: String = ""


}

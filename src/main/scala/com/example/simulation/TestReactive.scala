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
object TestReactive {

  private val baseUrl: String = sys.env.getOrElse("BASE_URL", "http://localhost:8585")

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .acceptHeader("*/*")
    .contentTypeHeader("application/json")

  def scnTestReactive: ScenarioBuilder = scenario("add user")
    .exec {
      http("add user")
        .post("/api/v1/user/")
        .body(RawFileBody("create_user.json"))
        .check(status.is(200))
    }
}

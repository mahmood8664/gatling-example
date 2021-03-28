package com.example.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder


/**
 * @author : mahmoud
 * @since : 1/4/20
 */
object HttpBase {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(sys.env.getOrElse("BASE_URL", "http://localhost:8080"))
    .acceptHeader("*/*")
    .contentTypeHeader("application/json")

}

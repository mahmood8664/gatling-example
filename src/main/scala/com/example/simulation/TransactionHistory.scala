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
object TransactionHistory {

  private val token: String = sys.env.getOrElse("TOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJNalpsUUdwbGpwWEVyd0oiLCJpc3MiOjAsImV4cCI6MTU4NTkwMTk0MSwianRpIjoiRC01ZTVmNjQ3NTBmMDY0IiwiaWF0IjoxNTgzMzA5OTQxfQ.GOQfHthuIMfXke4c0HHTMUc7LW3ygZgZxiZpV5aDvZO8JlLRv9aIZ564EwFmFmG3pjof8PhwaOXIIABacrHMIvDpvK-pvSxX9tn_y_csBhDjiC6A1uGbLA4djs6Alj18lsJDNd__p_1hLZY8nL5alPAdYX8irMstOkobi_68Fku2YynwuFo9jqAUCdK2ioj-dLJCmMT5uca6g2vbYLdbzSUwzamw0-6HK6cjBo5RFt9waXniYx-orAwocD4alxzTYuXrSqA3UwEM5KJLG5J6_TcI5-N4RN3q9xq0x9v3K74d0ocUGMynMjukhRGhIbVnT5h-81IRUZsNxKpaZLmOiSOtSkC9_1l36gyKPjEV_RDVqTnkGVoiXShz6DNxaEMlQW7IgFgn_XAXiKVYLaOXTKfXsQoP5k6TgCp0pNg44jyUw5E3k0AjClAGQThVu_l0JGJ5sHP5AOTh9Cerfx9OaTLqeGPZAsvTNuGis7PG1aFUwv2Y_V8q06Qlv2GnJh25zOrgN28vfIT7FACh-Dxbzhs7N1LeAVUd2g96UqvGo1leziZ1TBGn3uoBHpYHfgHbmPQgHD8GttDsjctjsiBjiDnECNfUnEen0iCDJ46XGV0YZT6qYcoUNj5jROwOL9Hn_FMAnUdH2oT-TJYoge1cHU1cg7bOIE8ESz-TPpf21fc")
  private val baseUrl: String = sys.env.getOrElse("BASE_URL", "http://blahblahblah.com")


  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .acceptHeader("*/*")
    .contentTypeHeader("application/json")

  def scnTransactionHistory: ScenarioBuilder = scenario("Transaction history")
    .exec {
      http("transaction history")
        .get("/v2/driver/finance/history?page=1")
        .header("Authorization", "Bearer " + token)
        .check(status.is(200))
    }
}

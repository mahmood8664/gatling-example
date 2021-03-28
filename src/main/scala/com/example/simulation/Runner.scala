package com.example.simulation

import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

/**
 * @author mahmood
 * @since 1/8/20
 */
object Runner {

  def main(args: Array[String]): Unit = {
    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[SampleSimulation].getName)
    Gatling.fromMap(props.build)
  }
}

package edu.knoldus.util

import com.typesafe.config.{Config, ConfigFactory}

/**
 * Created by pallavi on 31/1/18.
 */
object TwitterConfigReader {

  val config: Config = ConfigFactory.load()
  val getConsumerKey: String = config.getString("twitter.consumer.key")
  val getSecretConsumerKey: String = config.getString("twitter.consumer.secret")
  val getAccessKey: String = config.getString("twitter.access.key")
  val getSecretAccessKey: String = config.getString("twitter.access.secret")

}

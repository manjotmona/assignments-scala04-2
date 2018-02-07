package edu.knoldus.util

import com.typesafe.config.{Config, ConfigFactory}

/**
 * Created by pallavi on 31/1/18.
 */
object TwitterConfigReader {

  val Config: Config = ConfigFactory.load()
  val GetConsumerKey: String = Config.getString("twitter.consumer.key")
  val GetSecretConsumerKey: String = Config.getString("twitter.consumer.secret")
  val GetAccessKey: String = Config.getString("twitter.access.key")
  val GetSecretAccessKey: String = Config.getString("twitter.access.secret")

}

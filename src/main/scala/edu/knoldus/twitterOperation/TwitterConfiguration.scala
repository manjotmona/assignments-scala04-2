package edu.knoldus.twitterOperation

import edu.knoldus.util.TwitterConfigReader
import twitter4j.{Twitter, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

/**
 * Created by manjot on 5/2/18.
 */
class TwitterConfiguration {
  def initialiseTwitterInstance() : Twitter={
    val cb = new ConfigurationBuilder()
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(TwitterConfigReader.GetConsumerKey)
      .setOAuthConsumerSecret(TwitterConfigReader.GetSecretConsumerKey)
      .setOAuthAccessToken(TwitterConfigReader.GetAccessKey)
      .setOAuthAccessTokenSecret(TwitterConfigReader.GetSecretAccessKey)
    val tf = new TwitterFactory(cb.build())
     tf.getInstance()
  }
}

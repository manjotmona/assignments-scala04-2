package edu.knoldus.twitterOperation

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

import edu.knoldus.util.TwitterConfigReader
import org.apache.log4j.Logger
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder
//import org.apache.twitter4j.Twitter


/**
 * Created by pallavi on 31/1/18.
 */
object Configuration {
  def main(args: Array[String]) {
    val log = Logger.getLogger(this.getClass)


    val cb = new ConfigurationBuilder()
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(TwitterConfigReader.getConsumerKey)
      .setOAuthConsumerSecret(TwitterConfigReader.getSecretConsumerKey)
      .setOAuthAccessToken(TwitterConfigReader.getAccessKey)
      .setOAuthAccessTokenSecret(TwitterConfigReader.getSecretAccessKey)
    val tf = new TwitterFactory(cb.build())
    val twitter = tf.getInstance()


    val twitterOperations = new TwitterOperations("#ranjan", twitter)
    val result = twitterOperations.getTweetsOnHashTag()

    result onComplete {

      case Success(r) =>
        print("\n The Searched result is: \n")
        r.foreach(status => println("@" + status.getUser.getScreenName + ":" + status.getText))
      case Failure(e) => print("Twitter Failure !!" + e.getMessage)
    }

    val count = twitterOperations.getNumberOfTweets()

    count onComplete {
      case Success(c) =>
        print("\n Number of tweets:\n")
        print(c + "\n\n")
      case Failure(e) => print("Twitter Failure !!" + e.getMessage)
    }
    val average = twitterOperations.getAverageTweetsPerDay()

    average onComplete {
      case Success(a) =>
        print("\n Average number of tweets per day:\n")
        print(a)
      case Failure(e) => print("Twitter Failure !!" + e.getMessage)
    }

    val favorite = twitterOperations.getLikesOfATweet()
    favorite onComplete {
      case Success(a) =>
        println("\nLikes Per Tweet:\n")
        a.foreach(println)
      case Failure(e) => print("Twitter Failure !!" + e.getMessage)
    }

    val reTweets = twitterOperations.getReTweets()
    reTweets onComplete {

      case Success(a) =>
        println("\nRe-Tweets Per Tweet:\n")
        a.foreach(println)
      case Failure(e) => print("Twitter Failure !!" + e.getMessage)
    }


    Thread.sleep(100000)
  }
}

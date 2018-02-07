package edu.knoldus.twitterOperation

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

import org.apache.log4j.Logger
import twitter4j.Twitter

/**
 * Created by manjot on 31/1/18.
 */
object Application {
  def main(args: Array[String]): Unit = {
    val log = Logger.getLogger(this.getClass)
    val twitterConfiguration = new TwitterConfiguration
    val twitter = twitterConfiguration.initialiseTwitterInstance()
    val twitterOperations = new TwitterOperations("#sath", twitter: Twitter)
    val result = twitterOperations.getTweetsOnHashTag()
    result onComplete {
      case Success(r) => log.info("\n The Searched result is: \n")
        r.foreach(status => log.info("@" + status.getUser.getScreenName + ":" + status.getText))
      case Failure(e) => log.info("Twitter Failure !!" + e.getMessage)
    }
    val count = twitterOperations.getNumberOfTweets()
    count onComplete {
      case Success(c) => log.info("\n Number of tweets:\n")
        log.info(c + "\n\n")
      case Failure(e) => log.info("Twitter Failure !!" + e.getMessage)
    }
    val average = twitterOperations.getAverageTweetsPerDay
    average onComplete {
      case Success(a) => log.info("\n\n\n\n******Average number of tweets per day:\n")
        log.info("\n" + a + "\n")
      case Failure(e) => log.info("\n\n\n******Twitter Failure !!" + e.getMessage)
    }
    val favorite = twitterOperations.getLikesOfATweet()
    favorite onComplete {
      case Success(list) => log.info("\nLikes Per Tweet:\n")
        val (sum1,sum2) = list.foldLeft((0, 0)) { case ((accA, accB), (a, b)) => (accA + a, accB + b) }
        log.info(sum1 / list.size)
        log.info("\nRe-Tweets Per Tweet:\n")
        log.info(sum2 / list.size)
      case Failure(e) => log.info("Twitter Failure !!" + e.getMessage)
    }
    Thread.sleep(1000000)
  }
}

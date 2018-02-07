package edu.knoldus.twitterOperation

import java.text.SimpleDateFormat
import java.util.Date

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import twitter4j.{Query, QueryResult, Status, Twitter}

/**
 * Created by manjot on 1/2/18.
 */
class TwitterOperations(val inputString: String, val twitter: Twitter) {
  //val twitterConfiguration = new TwitterConfiguration
  //val twitter = twitterConfiguration.initialiseTwitterInstance()
  def getQueryResult(): Future[QueryResult] = {
    Future {
      val query = new Query(inputString)
      query.setCount(100)
      twitter.search(query)
    }
  }

  def getTweetsOnHashTag(): Future[List[Status]] = {
    val result = getQueryResult()
    val tweets = result.map(x => x.getTweets.asScala.toList)
    tweets
  }

  def getNumberOfTweets(): Future[Int] = {
    Thread.sleep(200)
    val result = getQueryResult()
    result.map(x => x.getTweets.size())
  }

  def getAverageTweetsPerDay: Future[Int] = {
    Thread.sleep(400)
    val result = getQueryResult()
    val dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
    val time = "Wed Jan 31 21:00:00 IST 2018"
    val date: Date = dateFormat.parse(time)

    val tweets = result.map(x => x.getTweets.asScala.toList)
    val oneDay = tweets.map(x=> x.filter(y=> y.getCreatedAt == date))
    val a = oneDay.map(x => x.size)
    a
  }

  def getLikesOfATweet(): Future[List[(Int, Int)]] = {

    Thread.sleep(800)
    val result = getQueryResult()
    val tweets = result.map(x => x.getTweets.asScala.toList)
    for {status <- tweets} yield {
      status.map(x => (x.getFavoriteCount, x.getRetweetCount))
    }
  }

  def getReTweets(): Future[List[Int]] = {
    Thread.sleep(1000)
    val result = getQueryResult()

    val tweets = result.map(x => x.getTweets.asScala.toList)
    for (status <- tweets) yield {
      status.map(x => x.getRetweetCount)
    }
  }
}

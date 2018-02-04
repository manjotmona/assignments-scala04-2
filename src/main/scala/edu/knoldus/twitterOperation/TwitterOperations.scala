package edu.knoldus.twitterOperation

import java.util.Date

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import twitter4j.{Query, Status, Twitter}
import java.text.SimpleDateFormat
/**
 * Created by pallavi on 1/2/18.
 */
class TwitterOperations(val inputString: String,val twitter: Twitter) {

  val query = new Query(inputString)

  query.setCount(100)
  val result = twitter.search(query)


  def getTweetsOnHashTag(): Future[List[Status]] =Future{

    val tweets = result.getTweets.asScala.toList
    tweets
  }


  def getNumberOfTweets(): Future[Int]= Future{
    Thread.sleep(100)
    result.getTweets.size()

  }

  def getAverageTweetsPerDay(): Future[Int] =Future{

    Thread.sleep(200)
    val dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
    val time = "Wed Jan 31 21:00:00 IST 2018"
    //val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    //val time = "2018-01-31"
    val date: Date = dateFormat.parse(time)

    val tweets = result.getTweets.asScala.toList
    val oneDay = for(s <- tweets if s.getCreatedAt == date) yield s
    oneDay.length
  }

  def getLikesOfATweet():Future[List[Int]]=Future{
    Thread.sleep(300)

    val tweets = result.getTweets.asScala.toList
    for(status<- tweets) yield status.getFavoriteCount
  }

  def getReTweets():Future[List[Int]] =Future{
    Thread.sleep(400)

    val tweets = result.getTweets.asScala.toList
    for(status<- tweets) yield status.getRetweetCount
  }

}

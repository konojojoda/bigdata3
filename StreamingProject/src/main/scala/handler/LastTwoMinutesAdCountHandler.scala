package handler

import java.sql.Connection
import java.text.SimpleDateFormat
import java.util.Date

import bean.Ads_log
import org.apache.spark.streaming.Minutes
import org.apache.spark.streaming.dstream.DStream
import util.JDBCUtil

object LastTwoMinutesAdCountHandler {
  def windowTwoMinutesCount1(filterAdsDStream: DStream[Ads_log]) = {
    val format = new SimpleDateFormat("hh-mm")
    val windowDStream: DStream[Ads_log] = filterAdsDStream.window(Minutes(2))
    val hmAdToOneDStream: DStream[((String, String), Int)] = windowDStream.map(ads => {
      val hm: String = format.format(new Date(ads.timestamp))
      ((hm, ads.adid), 1)
    })
    val hmAdToCountDStream: DStream[((String, String), Int)] = hmAdToOneDStream.reduceByKey(_+_)
    val adToHmCountDStream: DStream[(String, (String, Int))] = hmAdToCountDStream.map {
      case ((hm, adid), count) => {
        (adid, (hm, count))
      }
    }
    val adidToHmCountListDStream: DStream[(String, Iterable[(String, Int)])] = adToHmCountDStream.groupByKey()
    val adidToSortedHmCountListDStream: DStream[(String, List[(String, Int)])] = adidToHmCountListDStream.mapValues(list => {
      list.toList.sortWith {
        (left, right) => left._1 < right._1
      }
    })
    adidToSortedHmCountListDStream
  }

  def windowTwoMinutesCount(filterAdsDStream: DStream[Ads_log]) = {
    val windowDStream: DStream[Ads_log] = filterAdsDStream.window(Minutes(2))

    val adidToCountDStream: DStream[(String, Long)] = windowDStream.map(ads => {
      (ads.adid, 1L)
    }).reduceByKey(_ + _)
    adidToCountDStream.foreachRDD(rdd => {
      rdd.foreachPartition(iter => {
        val connection: Connection = JDBCUtil.getConnection
        iter.foreach {
          case (adid, count) => {
            JDBCUtil.insertOrUpdate(connection,
              """
                |insert into last_two_minutes_ad_count(adid,count)
                |values(?,?)
                |on duplicate key
                |update count=?
              """.stripMargin, Array(adid, count, count))
          }
            connection.close()
        }
      })
    })
  }

}

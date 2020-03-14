package handler

import java.sql.Connection
import java.text.SimpleDateFormat
import java.util.Date

import bean.Ads_log
import org.apache.spark.streaming.dstream.DStream
import util.JDBCUtil

object DayAdCountHandler {
  val format = new SimpleDateFormat("yyyy-MM-dd")

  def countAd(filterAdsDStream: DStream[Ads_log]) = {

    val dtAreaCityAdToOneDStream: DStream[((String, String, String, String), Int)] = filterAdsDStream.map(ads => {
      val dt: String = format.format(new Date(ads.timestamp))
      ((dt, ads.area, ads.city, ads.adid), 1)
    })

    val dtAreaCityAdToCountDStream: DStream[((String, String, String, String), Int)] = dtAreaCityAdToOneDStream.reduceByKey(_ + _)

    dtAreaCityAdToCountDStream.foreachRDD(rdd => {
      rdd.foreachPartition(iter => {
        val connection: Connection = JDBCUtil.getConnection
        iter.foreach {
          case ((dt, area, city, adid), count) => {
            JDBCUtil.insertOrUpdate(connection,
              """
                |insert into dt_city_ad_count(dt,areaname,cityname,adid,count)
                |values(?,?,?,?,?)
                |on duplicate key
                |update count=count+?
              """.stripMargin, Array(dt, area, city, adid, count, count))
          }
            connection.close()
        }
      })
    })
  }

}

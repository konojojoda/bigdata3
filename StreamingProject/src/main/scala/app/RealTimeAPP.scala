package app

import java.sql.Connection

import bean.Ads_log
import handler.{BlackListHandler, DayAdCountHandler, LastTwoMinutesAdCountHandler}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.streaming.api.java.JavaInputDStream
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import util.{JDBCUtil, MyKafkaUtil, PropertiesUtil}

object RealTimeAPP {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("RealTimeAPP").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(3))

    val topic: String = PropertiesUtil.load("config.properties").getProperty("kafka.topic")
    val initDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(topic, ssc)

    val adsLogDStream: DStream[Ads_log] = initDStream.map(record => {
      val arr: Array[String] = record.value().split(" ")
      Ads_log(arr(0).toLong, arr(1), arr(2), arr(3), arr(4))
    })

//    val filterAdsDStream: DStream[Ads_log] = adsLogDStream.filter(ads => {
//      val connection: Connection = JDBCUtil.getConnection
//      val bool: Boolean = JDBCUtil.isExist(connection, "select * from black_list where userid=?", Array(ads.userid))
//      connection.close()
//      !bool
//    })


//    val filterAdsDStream: DStream[Ads_log] = adsLogDStream.transform(rdd => {
//      rdd.foreachPartition(
//        iter => {
//          val connection: Connection = JDBCUtil.getConnection
//          iter.filter(ads => {
//            val bool: Boolean = JDBCUtil.isExist(connection, "select * from black_list where userid=?", Array(ads.userid))
//            connection.close()
//            !bool
//          })
//        })
//      rdd
//    })

    val filterAdsDStream: DStream[Ads_log] = adsLogDStream.transform(rdd => {
      val connection: Connection = JDBCUtil.getConnection
      val blackList: List[String] = JDBCUtil.blackList(connection,
        """
select * from black_list
        """.stripMargin)
      val broadList: Broadcast[List[String]] = ssc.sparkContext.broadcast(blackList)
      connection.close()
      rdd.filter(ads => {
        !broadList.value.contains(ads.userid)
      })
    })



    filterAdsDStream.cache()
    //filterAdsDStream.print(10)
    BlackListHandler.saveInBlackList(filterAdsDStream)

    //DayAdCountHandler.countAd(filterAdsDStream)

//    val value: DStream[(String, List[(String, Int)])] = LastTwoMinutesAdCountHandler.windowTwoMinutesCount1(filterAdsDStream)
//    value.print()
//    LastTwoMinutesAdCountHandler.windowTwoMinutesCount(filterAdsDStream)

    ssc.start()
    ssc.awaitTermination()
  }
}

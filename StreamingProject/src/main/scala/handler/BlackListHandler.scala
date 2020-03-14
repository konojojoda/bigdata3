package handler

import java.sql.Connection
import java.text.SimpleDateFormat
import java.util.Date

import bean.Ads_log
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import util.JDBCUtil

object BlackListHandler {
  def saveInBlackList(filterAdsDStream: DStream[Ads_log]) = {
    val format = new SimpleDateFormat("yyyy-MM-dd")
    val dtUserAdToOneDStream: DStream[((String, String, String), Long)] = filterAdsDStream.map(ads => {
      val dt: String = format.format(new Date(ads.timestamp))
      ((dt, ads.userid, ads.adid), 1L)
    })
    val dtUserAdToCountDStream: DStream[((String, String, String), Long)] = dtUserAdToOneDStream.reduceByKey(_+_)
    dtUserAdToCountDStream.foreachRDD(rdd=>{
      //val connection: Connection = JDBCUtil.getConnection
      rdd.foreachPartition(iter=>{
        val connection: Connection = JDBCUtil.getConnection
        iter.foreach{
          case((dt,userid,adid),count)=>{
            var sql=""
            var params=Array[Any]()
            //插入或更新
            JDBCUtil.insertOrUpdate(
              connection,
              """ |insert into user_ad_count (dt,userid,adid,count)
                  |value(?,?,?,?)
                  |on duplicate key
                  |update count=count+?
              """.stripMargin,
              Array[Any](dt,userid,adid,count,count))
            //获取个数
            val result: Long = JDBCUtil.getCount(connection,
              """
                |select count from user_ad_count where dt=? and userid=? and adid=?
              """.stripMargin,Array(dt,userid,adid))

            if(result>=40){
              JDBCUtil.insertOrUpdate(connection,
                """
                  |insert into black_list (userid) values(?)
                  |on duplicate key update userid=?
                """.stripMargin,
                Array(userid,userid))
            }
          }
        }
        connection.close()
      })
    })
  }
}

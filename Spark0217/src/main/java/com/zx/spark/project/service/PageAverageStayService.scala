package com.zx.spark.project.service

import com.zx.spark.project.bean
import com.zx.spark.project.common.{TDao, TService}
import com.zx.spark.project.dao.PageAverageStayDao
import org.apache.spark.rdd.RDD
import java.text.SimpleDateFormat
import java.util.Date

class PageAverageStayService extends TService () {
  override def getDao(): TDao = new PageAverageStayDao()
  def compute() = {
    val actionRDD: RDD[bean.UserVisitAction] = getActionRDD()
    val sessionRDD: RDD[(String, (Long, String))] = actionRDD.map(a=>(a.session_id,(a.page_id,a.action_time)))
    val listRDD: RDD[List[(Long, String)]] = sessionRDD.groupByKey().mapValues(list=>{list.toList.sortBy(_._2)}).map(_._2)
    //计算时间
    val zipRDD: RDD[List[((Long, String), (Long, String))]] = listRDD.map(list=>list.zip(list.tail))
    val pidToDuringRDD: RDD[List[(Long, Long)]] = zipRDD.map(list => {
      list.map {
        case (t1, t2) => {
          val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") //2019-07-17 00:00:02
          val date1: Date = sdf.parse(t1._2)
          val date2: Date = sdf.parse(t2._2)

          (t1._1, (date2.getTime - date1.getTime)/1000)
        }
      }
    })
    val pidToSumRDD: RDD[(Long, Long)] = pidToDuringRDD.flatMap(list=>list).reduceByKey(_+_)
    //计算个数
    val pidToTimesRDD: RDD[(Long, Int)] = pidToDuringRDD.flatMap(list=>list).map(t=>(t._1,1)).reduceByKey(_+_)
    val finalRDD: RDD[(Long, (Long, Int))] = pidToSumRDD.join(pidToTimesRDD)
    val result: RDD[String] = finalRDD.map {
      case (pid, t) => {
        pid + "->" + t._1 * 1.0 / t._2
      }
    }
    result
  }
}

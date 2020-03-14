package com.zx.spark.project.service

import com.zx.spark.project.bean.UserVisitAction
import com.zx.spark.project.common.{TDao, TService}
import com.zx.spark.project.dao.HotCategorySessionTop10Dao
import com.zx.spark.project.util.ProjectLocal
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

import scala.collection.mutable

class HotCategorySessionTop10Service() {
  val hotCategorySessionTop10Dao=new HotCategorySessionTop10Dao()
  def compute()={
    val rdd: RDD[String] = hotCategorySessionTop10Dao.textFile
    val actionRDD: RDD[UserVisitAction] = rdd.map(
      action => {
        val aa: Array[String] = action.split("_")
        UserVisitAction(
          aa(0),
          aa(1).toLong,
          aa(2),
          aa(3).toLong,
          aa(4),
          aa(5),
          aa(6).toLong,
          aa(7).toLong,
          aa(8),
          aa(9),
          aa(10),
          aa(11),
          aa(12).toLong
        )
      }
    )
    //top10品类
    val unionRDD: RDD[(String, (Int, Int, Int))] = rdd.flatMap(
      action => {
        val datas: Array[String] = action.split("_")
        if (datas(6) != "-1") {
          List((datas(6), (1, 0, 0)))
        } else if (datas(8) != "null") {
          val tuples1: mutable.ArraySeq[(String, (Int, Int, Int))] = datas(8).split(",").map((_,(0,1,0)))
          tuples1
        } else if (datas(10) != "null") {
          val tuples2: mutable.ArraySeq[(String, (Int, Int, Int))] = datas(8).split(",").map((_,(0,1,0)))
          tuples2
        } else {
          Nil
        }
      }
    )
    val finalRDD: RDD[(String, (Int, Int, Int))] = unionRDD.reduceByKey(
      (t1, t2) => (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
    )
    val categories = finalRDD.sortBy(_._2,false).map(_._1).take(10)
    val bcIds: Broadcast[Array[String]] = ProjectLocal.getSparkContextOnLocal().broadcast(categories)


    val cateAndSessionToOneRDD: RDD[(String, Int)] = actionRDD.flatMap(
      a => {
        if (a.click_category_id != -1) List((a.click_category_id + "_" + a.session_id, 1))
        else if (a.order_category_ids != "null") {
          val strings: Array[String] = a.order_category_ids.split(",")
          strings.map(b => (b + "_" + a.session_id, 1))
        } else if (a.pay_category_ids != "null") {
          val strings1: Array[String] = a.pay_category_ids.split(",")
          strings1.map(b => (b + "_" + a.session_id, 1))
        }
        else Nil
      }
    ).filter(a=>bcIds.value.contains(a._1.split("_")(0)))
    //(品类_sessionid,1)
    val reduceRDD: RDD[(String, Int)] = cateAndSessionToOneRDD.reduceByKey(_+_)
    val cateToSessionAndSumRDD: RDD[(String, (String, Int))] = reduceRDD.map {
      case (cateAndSession, sum) => {
        val caAndSe: Array[String] = cateAndSession.split("_")
        (caAndSe(0), (caAndSe(1), sum))
      }
    }
    val groupRDD: RDD[(String, Iterable[(String, Int)])] = cateToSessionAndSumRDD.groupByKey()
    val sortRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(
      list => {
        list.toList.sortBy(_._2)(Ordering.Int.reverse).take(10)
      }
    )
    sortRDD
  }


}

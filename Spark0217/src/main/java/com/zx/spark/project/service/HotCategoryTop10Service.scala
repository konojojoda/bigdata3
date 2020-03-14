package com.zx.spark.project.service

import com.zx.spark.project.bean.HotCategory
import com.zx.spark.project.common.TService
import com.zx.spark.project.dao.HotCategoryTop10Dao
import com.zx.spark.project.helper.{HotCategoryAccumulator, HotCategoryAccumulator1}
import com.zx.spark.project.util.ProjectLocal
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.collection.mutable

case class HotCategoryTop10Service(){
  private val hotCategoryTop10Dao = new HotCategoryTop10Dao()
  private val rdd: RDD[String] = hotCategoryTop10Dao.textFile
  rdd.cache()

  def compute() = {
//    1.1  点击RDD
    val clickToOneRDD: RDD[(String, Int)] = rdd.map(line => {
      val strings: mutable.ArrayOps[String] = line.split("_")
      (strings(6), 1)
    }).filter(_._1 != "-1")
    val clickRDD: RDD[(String, Int)] = clickToOneRDD.reduceByKey(_ + _)
    //1.2下单RDD
    val orderToOneRDD: RDD[(String, Int)] = rdd.map(
      line => {
        val strings: mutable.ArrayOps[String] = line.split("_")
        strings(8)
      }
    ).flatMap(
      a => {
        a.split(",")
      }
    ).map((_, 1))
    val orderRDD: RDD[(String, Int)] = orderToOneRDD.reduceByKey(_ + _)
    //1.3支付RDD
    val payToOneRDD: RDD[(String, Int)] = rdd.map(
      line => {
        val strings: mutable.ArrayOps[String] = line.split("_")
        strings(10)
      }
    ).flatMap(
      a => {
        a.split(",")
      }
    ).map((_, 1))
    val payRDD: RDD[(String, Int)] = payToOneRDD.reduceByKey(_ + _)
    //2.加0
    val clickChangeRDD: RDD[(String, (Int, Int, Int))] = clickRDD.map {
      case (cate, click) => (cate, (click, 0, 0))
    }
    val orderChangeRDD=orderRDD.map {
      case (cate, order) => (cate, (0,order, 0))
    }
    val payChangeRDD=payRDD.map {
      case (cate, pay) => (cate, (0,0,pay))
    }

    val unionRDD: RDD[(String, (Int, Int, Int))] = clickChangeRDD.union(orderChangeRDD).union(payChangeRDD)



    val finalRDD: RDD[(String, (Int, Int, Int))] = unionRDD.reduceByKey(
      (t1, t2) => (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
    )
    finalRDD.sortBy(_._2,false)
  }
  ///TODO===================================================================
  def compute1() = {
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
    finalRDD.sortBy(_._2,false)
  }
  ///TODO===================================================================
  def compute2() = {
    val beanRDD: RDD[HotCategory] = rdd.flatMap(
      action => {
        val datas: mutable.ArrayOps[String] = action.split("_")
        if (datas(6) != "-1") {
          List(HotCategory(datas(6), 1, 0, 0))
        }
        else if (datas(8) != "null") {
          datas(8).split(",").map(
            a => HotCategory(a, 0, 1, 0)
          )
        } else if (datas(10) != "null") {
          datas(10).split(",").map(
            a => HotCategory(a, 0, 0, 1)
          )
        }
        else {
          Nil
        }
      }
    )
    val groupRDD: RDD[(String, Iterable[HotCategory])] = beanRDD.groupBy(_.id)
    val reduceRDD: RDD[HotCategory] = groupRDD.mapValues(
      list => {
        list.reduce(
          (h1, h2) => {
            h1.clickCount = h1.clickCount + h2.clickCount
            h1.orderCount = h1.orderCount + h2.orderCount
            h1.payCount = h1.payCount + h2.payCount
            h1
          }
        )
      }
    ).map((_._2))
    val categories: Array[HotCategory] = reduceRDD.collect().sortWith(
      (left, right) => {
        if (left.clickCount > right.clickCount || (left.clickCount == right.clickCount && left.orderCount > right.orderCount) || (left.orderCount == right.orderCount && left.payCount > right.payCount)) {
          true
        } else {
          false
        }
      }
    )
    categories
  }
  ///TODO===================================================================
  def compute3() = {
    val beanRDD: RDD[(String,HotCategory)] = rdd.flatMap(
      action => {
        val datas: mutable.ArrayOps[String] = action.split("_")
        if (datas(6) != "-1") {
          List((datas(6),HotCategory(datas(6), 1, 0, 0)))
        }
        else if (datas(8) != "null") {
          datas(8).split(",").map(
            a => (a,HotCategory(a, 0, 1, 0))
          )
        } else if (datas(10) != "null") {
          datas(10).split(",").map(
            a => (a,HotCategory(a, 0, 0, 1))
          )
        }
        else {
          Nil
        }
      }
    )
    val beanReduceRDD: RDD[(String, HotCategory)] = beanRDD.reduceByKey(
      (left, right) => {
        left.clickCount = left.clickCount + right.clickCount
        left.orderCount = left.orderCount + right.orderCount
        left.payCount = left.payCount + right.payCount
        left
      }
    )
    beanReduceRDD.sortBy(t=>(t._2.clickCount,t._2.orderCount,t._2.payCount),false)
  }
  ///TODO===================================================================
  def compute4()={
    val acc = new HotCategoryAccumulator1()
    ProjectLocal.getSparkContextOnLocal().register(acc)
    rdd.foreach(
      action => {
        val datas: mutable.ArrayOps[String] = action.split("_")
        if (datas(6) != "-1") {
          acc.add(datas(6),"click")
        }
        else if (datas(8) != "null") {
          datas(8).split(",").map(
            a=>acc.add(a,"order")
          )
        } else if (datas(10) != "null") {
          datas(10).split(",").map(
            a => acc.add(a,"pay")
          )
        }
        else {
          Nil
        }
      }
    )
    acc.value.toList.sortBy(t=>(t._2.clickCount,t._2.orderCount,t._2.payCount))(Ordering.Tuple3[Int,Int,Int].reverse)
  }

}







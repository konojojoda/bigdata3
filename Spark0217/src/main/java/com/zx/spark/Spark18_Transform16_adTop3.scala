package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark18_Transform16_adTop3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val lineRDD: RDD[String] = sc.textFile("input/agent.log")
    //修改成省份——广告，1
    val proAndAdToOneRDD: RDD[(String, Int)] = lineRDD.map(
      line => {
        val strings: Array[String] = line.split(" ")
        (strings(1) + "_" + strings(4), 1)
      }
    )

    //求和
    val proAndAdToSumRDD: RDD[(String, Int)] = proAndAdToOneRDD.reduceByKey(_+_)
    //改成省份，（广告，和）
    val proToAdAndSumRDD: RDD[(String, (String, Int))] = proAndAdToSumRDD.map {
      case (proAndAd, sum) => {
        val list1: Array[String] = proAndAd.split("_")
        (list1(0), (list1(1), sum))
      }
      case _=>("error",("error",0))
    }
    //println(proToAdAndSumRDD.collect().mkString(","))
    val groupRDD: RDD[(String, Iterable[(String, Int)])] = proToAdAndSumRDD.groupByKey()
    val lastRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(
      datas => {
        datas.toList.sortWith(
          (left, right) => left._2 > right._2
        ).take(3)
      }
    )
    //sortRDD.take(3)
    //val takeRDD: Array[(String, List[(String, Int)])] = sortRDD.take(3)
    //println(lastRDD.collect().mkString(","))
lastRDD.collect().foreach(println)



    sc.stop()
  }
}

package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark22_Transform20_sortByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a",88),("b",94),("a",91),("b",93),("a",95),("b",98)),2)

    println(rdd.sortByKey(true).collect().mkString(","))




    sc.stop()
  }
}

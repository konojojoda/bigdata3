package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark7_Transform5_filter {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val initRDD: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)
    initRDD.filter(a=>a%2==0).collect().foreach(println)

    sc.stop()
  }
}

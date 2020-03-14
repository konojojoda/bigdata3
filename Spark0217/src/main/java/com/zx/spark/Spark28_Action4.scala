package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark28_Action4 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4,1,3,1),2)

    val intToLong1: collection.Map[Int, Long] = rdd.map((_,1)).countByKey()
    println(intToLong1)
    val intToLong: collection.Map[Int, Long] = rdd.countByValue()
    println(intToLong)





    sc.stop()
  }
}

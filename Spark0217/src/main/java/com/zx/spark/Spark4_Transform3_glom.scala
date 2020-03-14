package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark4_Transform3_glom {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val numRDD: RDD[Int] = sc.makeRDD(List(1,4,3,2),2)
    val glomRDD: RDD[Array[Int]] = numRDD.glom()
    val maxRDD: RDD[Int] = glomRDD.map(array=>array.max)
    println(maxRDD.collect().sum)

    sc.stop()
  }
}

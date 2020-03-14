package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark3_Transform2_flatMap {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val value: RDD[String] = sc.makeRDD(List("a b c","a b"),2)
    val stringRDD: RDD[String] = value.flatMap(s=>s.split(" "))
    stringRDD.collect().foreach(println)
    //value.saveAsTextFile("output")

    sc.stop()
  }
}

package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark5_Transform4_groupBy {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val stringRDD: RDD[String] = sc.makeRDD(List("Hello","Hadoop","Scala","Java","Spark"))
    stringRDD.groupBy(s=>s.charAt(0)).collect().foreach(println)



    sc.stop()
  }
}

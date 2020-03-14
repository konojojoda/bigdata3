package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark30_Action6 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))
    rdd.collect().foreach(println)
    println("================")
    rdd.foreach(println)







    sc.stop()
  }
}

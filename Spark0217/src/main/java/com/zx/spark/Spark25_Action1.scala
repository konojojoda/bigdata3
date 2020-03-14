package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark25_Action1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(4,3,1,2))
    val i: Int = rdd.reduce(_+_)
    println(i)
    val d: Double = rdd.sum()
    println(d)






    sc.stop()
  }
}

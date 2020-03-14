package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark26_Action2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(4,3,1,2))
    val l: Long = rdd.count()
    println(l)
    val ints: Array[Int] = rdd.take(3)
    for(a <- ints){
      println(a)
    }
    val array: Array[Int] = rdd.takeOrdered(3)
    println(array.mkString(","))






    sc.stop()
  }
}

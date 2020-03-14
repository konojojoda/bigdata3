package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark13_Transform11_双Value {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
   val rdd1: RDD[Int] = sc.makeRDD(List(1,2,3,4))
   val rdd2: RDD[Int] = sc.makeRDD(List(4,5,6,7))
    println(rdd1.union(rdd2).collect().mkString(","))
    println(rdd1.intersection(rdd2).collect().mkString(","))
    println(rdd1.subtract(rdd2).collect().mkString(","))


    println(rdd1.zip(rdd2).collect().mkString(","))

    sc.stop()
  }
}

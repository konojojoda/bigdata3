package com.zx.spark.dep

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark_Dep01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("serial")
    val sc = new SparkContext(conf)

    val rdd: RDD[String] = sc.makeRDD(List("Hello Spark","Hello Scala","hadoop","Java"))

    val rdd1: RDD[String] = rdd.flatMap(a=>a.split(" "))
    val rdd1Str: String = rdd1.toDebugString
    println(rdd1Str)
    println("========================")
    val rdd2: RDD[(String, Int)] = rdd1.map((_,1))
    println(rdd2.toDebugString)
    println("=================")
    val rdd3: RDD[(String, Int)] = rdd2.reduceByKey(_+_)
    println(rdd3.toDebugString)
    println("===========================")
    val rdd4: RDD[(String, Int)] = rdd3.sortByKey()
    println(rdd4.toDebugString)
    sc.stop()
  }
}

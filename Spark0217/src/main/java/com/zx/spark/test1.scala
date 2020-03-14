package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object test1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("aaa")
    val sc = new SparkContext(conf)
    val numRDD: RDD[String] = sc.textFile("input/1*",3)
    numRDD.saveAsTextFile("output")
    //val strings: Array[String] = numRDD.collect()
    //strings.s
    println("".length)
  }
}

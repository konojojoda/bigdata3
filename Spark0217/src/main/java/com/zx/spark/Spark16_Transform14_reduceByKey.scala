package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object Spark16_Transform14_reduceByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("b",2),("a",3),("b",4)),2)
    println(rdd.groupByKey.mapValues(list=>list.sum).collect().mkString(","))


    sc.stop()
  }
}

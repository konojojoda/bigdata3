package com.zx.spark.io

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.parsing.json.JSON

//import scala.util.parsing.json.JSON

object jsonTest {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("json").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd: RDD[String] = sc.textFile("input/test.json")
    import scala.util.parsing.json
    val rdd1: RDD[Option[Any]] = rdd.map(JSON.parseFull)
    rdd1.collect().foreach(println)


    sc.stop()
  }
}

package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark6_WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val stringRDD: RDD[String] = sc.makeRDD(List("Hello Spark","Hello Scala"))
    val wordRDD: RDD[String] = stringRDD.flatMap(s=>s.split(" "))
    val groupRDD: RDD[(String, Iterable[String])] = wordRDD.groupBy(w=>w)
    val sizeRDD: RDD[(String, Int)] = groupRDD.mapValues(list=>list.size)
    sizeRDD.collect().foreach(println)


    sc.stop()
  }
}

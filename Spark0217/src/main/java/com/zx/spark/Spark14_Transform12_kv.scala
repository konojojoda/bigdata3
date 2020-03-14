package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Spark14_Transform12_kv {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val kvRDD: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3) ,("d", 4)), 2)
    println(kvRDD.mapPartitionsWithIndex(
      (index, list) => {
        list.map((index, _))
      }
    ).collect().mkString(","))

    println(kvRDD.partitionBy(new HashPartitioner(3)).mapPartitionsWithIndex(
      (index, list) => list.map((index, _))
    ).collect().mkString(","))

    sc.stop()
  }
}

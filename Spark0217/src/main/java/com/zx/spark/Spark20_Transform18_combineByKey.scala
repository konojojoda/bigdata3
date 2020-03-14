package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark20_Transform18_combineByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a",88),("b",95),("a",91),("b",93),("a",95),("b",98)),2)
    val wordsToSumAndCountRDD: RDD[(String, (Int, Int))] = rdd.combineByKey(
      num => (num, 1),
      (t: (Int, Int), num) => (t._1 + num, t._2 + 1),
      (t1: (Int, Int), t2: (Int, Int)) => (t1._1 + t2._1, t2._2 + t1._2)
    )
    wordsToSumAndCountRDD.mapValues{
      case (sum,count)=>sum/count
    }.collect().foreach(println)




    sc.stop()
  }
}

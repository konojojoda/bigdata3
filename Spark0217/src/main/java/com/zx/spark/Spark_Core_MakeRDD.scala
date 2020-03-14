package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark_Core_MakeRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建集合
    val list = List(1,2,3,4)
    //2.创建RDD
    val numRDD: RDD[Int] = sc.makeRDD(list)
    //3.执行RDD
    val ints: Array[Int] = numRDD.collect()
    ints.foreach(println)




    sc.stop()
  }
}

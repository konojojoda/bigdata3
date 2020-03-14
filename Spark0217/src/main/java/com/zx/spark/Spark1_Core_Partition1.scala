package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark1_Core_Partition1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建集合
    val list = List(1,2,3,4)
    //2.创建RDD
    val numRDD: RDD[String] = sc.textFile("input/1.txt",3)
    //3.将数据保存到文件中
    numRDD.saveAsTextFile("output")




    sc.stop()
  }
}

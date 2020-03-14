package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark9_Transform7_sample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[Int] = sc.makeRDD(1 to 10)
    rdd.sample(true,0.5,5).collect().foreach(println)




    sc.stop()
  }
}

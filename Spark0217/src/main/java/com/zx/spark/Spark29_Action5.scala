package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark29_Action5 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4,1,3,1))

    rdd.saveAsTextFile("output1")
    //rdd.map((_,1)).saveAsSequenceFile("output2")
    //rdd.saveAsObjectFile("output3")





    sc.stop()
  }
}

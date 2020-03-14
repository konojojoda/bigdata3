package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark27_Action3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)
    val i: Int = rdd.aggregate(10)(_+_,_+_)
    val j: Int = rdd.fold(10)(_+_)
    println("i="+i)
    println("j="+j)






    sc.stop()
  }
}

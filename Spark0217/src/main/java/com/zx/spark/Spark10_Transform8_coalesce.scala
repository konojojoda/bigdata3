package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark10_Transform8_coalesce {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6),2)
    //rdd.filter(_==1).saveAsTextFile("output")
//    rdd.filter(_==1).mapPartitionsWithIndex(
//      (index,list)=>{
//        list.map((index,_))
//      }
//    ).collect().foreach(println)
    println(rdd.filter(_ == 1).getNumPartitions)
    println(rdd.filter(_ == 1).coalesce(3).getNumPartitions)



    sc.stop()
  }
}

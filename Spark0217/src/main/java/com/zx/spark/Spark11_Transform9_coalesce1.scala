package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark11_Transform9_coalesce1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,7,8),2)
    rdd.mapPartitionsWithIndex(
      (index,list)=>{
        list.map((index,_))
      }
    ).collect().foreach(println)
println("*************")
    rdd.coalesce(4,true).mapPartitionsWithIndex(
      (index,list)=>{
        list.map((index,_))
      }
    ).collect().foreach(println)


    sc.stop()
  }
}

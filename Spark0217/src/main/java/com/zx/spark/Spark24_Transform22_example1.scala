package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark24_Transform22_example1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[String] = sc.textFile("input/agent.log")
    rdd.map(
      s=>{
        val words: Array[String] = s.split(" ")
        (words(1)+"-"+words(4),1)
      }
    ).reduceByKey(_+_)
        .map{
          case (name,count)=>{
            val names: Array[String] = name.split("-")
            (names(0),(names(1),count))
          }
        }.groupByKey()
        .mapValues{
          list=>list.toList.sortWith{
            (left,right)=>left._2>right._2
          }.take(3)
        }.collect().foreach(println)





    sc.stop()
  }
}

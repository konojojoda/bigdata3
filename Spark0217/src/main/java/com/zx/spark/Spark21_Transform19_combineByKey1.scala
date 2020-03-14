package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark21_Transform19_combineByKey1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a",88),("b",95),("a",91),("b",93),("a",95),("b",98)),2)

    val numRDD: RDD[(String, Int)] = rdd.combineByKey(num=>num,(sum:Int,num)=>sum+num,(t1:Int,t2:Int)=>t1+t2)
    numRDD.collect().foreach(println)




    sc.stop()
  }
}

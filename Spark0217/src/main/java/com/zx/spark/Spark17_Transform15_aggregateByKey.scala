package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark17_Transform15_aggregateByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("b",2),("a",3),("c",6),("a",3),("b",4)),2)
    rdd.aggregateByKey(0)((x,y)=>{math.max(x,y)},_+_).collect().foreach(println)


    sc.stop()
  }
}

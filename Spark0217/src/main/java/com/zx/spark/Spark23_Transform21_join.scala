package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark23_Transform21_join {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd1 = sc.makeRDD(List(("a",1),("b",2),("a",3),("c",4)))
    val rdd2 = sc.makeRDD(List(("a",3),("b",4),("a",5)))

    println(rdd1.cogroup(rdd2).collect().mkString(","))





    sc.stop()
  }
}

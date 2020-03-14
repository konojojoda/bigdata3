package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark31_Action7 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))
    val user = new User31
    rdd.collect().foreach(a=>println(user.age+a))







    sc.stop()
  }
  class User31 /*extends Serializable */{
    val age:Int=18
  }
}

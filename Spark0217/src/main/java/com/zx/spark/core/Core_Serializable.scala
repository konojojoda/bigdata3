package com.zx.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Core_Serializable {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("serial")
    val sc = new SparkContext(conf)

    val rdd: RDD[String] = sc.makeRDD(List("Hello Spark","Hello Scala","hadoop","Java"))

    val search = new Search("Hello")
   // println(search.getMatch1(rdd).collect().mkString(","))
    println("===============")
    println(search.getMatch2(rdd).collect().mkString(","))

  }
  class Search(query:String)   {
    def isMatch(s:String)={
      s.contains(query)
    }
    def getMatch1(rdd:RDD[String])={
      rdd.filter(s=>isMatch(s))
    }
    def getMatch2(rdd:RDD[String])={
      val str =query
      rdd.filter(s=>s.contains(str))
    }
  }
}

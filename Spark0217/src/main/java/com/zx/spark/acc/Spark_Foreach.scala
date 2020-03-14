package com.zx.spark.acc

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object Spark_Foreach {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("json").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4,5))
    val sum: LongAccumulator = sc.longAccumulator("sum")
    var forSum=0
    rdd.foreach(a=>forSum+=a)
    println(forSum)


    sc.stop()
  }
}

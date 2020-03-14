package com.zx.spark.acc

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.util.{AccumulatorV2, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object Spark2_Broadcast {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("json").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(List( ("a",1), ("b", 2), ("c", 3), ("d", 4) ),4)
    val list = List( ("a",4), ("b", 5), ("c", 6), ("d", 7) )
    val bc: Broadcast[List[(String, Int)]] = sc.broadcast(list)
    val mapRDD: RDD[(String, (Int, Int))] = rdd1.map {
      case (key, num) => {
        var num2 = 0
        for ((k, v) <- bc.value) {
          if (k == key) {
            num2 = v
          }
        }
        (key, (num, num2))
      }
    }
    println(mapRDD.collect().mkString(","))





    sc.stop()
  }
}

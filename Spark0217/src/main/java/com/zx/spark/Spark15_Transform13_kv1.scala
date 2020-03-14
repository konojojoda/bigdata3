package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

object Spark15_Transform13_kv1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建RDD
    val kvRDD: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3) ,("d", 4)), 2)
    println(kvRDD.mapPartitionsWithIndex(
      (index, list) => {
        list.map((index, _))
      }
    ).collect().mkString(","))

    println(kvRDD.partitionBy(new MyPartitioner(3)).mapPartitionsWithIndex(
      (index, list) => list.map((index, _))
    ).collect().mkString(","))

    sc.stop()
  }
  class MyPartitioner(num:Int) extends Partitioner{
    override def numPartitions: Int = num

    override def getPartition(key: Any): Int = {
      key match{
        case null=>0
        case "a"=>1
        case "b"=>2
        case "c"=>1
        case "d"=>2
        case _=>0
      }
    }
  }

}

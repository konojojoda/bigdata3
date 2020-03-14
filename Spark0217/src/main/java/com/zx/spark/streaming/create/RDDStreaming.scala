package com.zx.spark.streaming.create

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object RDDStreaming {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))

    val queue = new mutable.Queue[RDD[Int]]
    val testDStream: InputDStream[Int] = ssc.queueStream(queue,false)
    testDStream.map((_,1)).reduceByKey(_+_).print()

    ssc.start()


    for(i <- 1 to 5){
      queue += ssc.sparkContext.makeRDD(1 to 300,10)
      Thread.sleep(2000)
    }
    ssc.awaitTermination()

  }
}

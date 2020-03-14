package com.zx.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Wordcount {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))

    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",9999)
    val dStream: DStream[(String, Int)] = value.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    dStream.print()


    ssc.start()
    ssc.awaitTermination()
  }
}

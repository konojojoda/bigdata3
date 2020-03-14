package com.zx.spark.streaming.receiver

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TestMyReceiver {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("TestMyReceiver")
    val ssc = new StreamingContext(conf,Seconds(3))

    val value: ReceiverInputDStream[String] = ssc.receiverStream(new MyReceiver("hadoop102",9999))
    value.flatMap(_.split(" "))
        .map((_,1))
        .reduceByKey(_+_)
        .print()


    ssc.start()
    ssc.awaitTermination()
  }
}

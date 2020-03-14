package com.zx.spark.streaming.trans

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Join {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("DirectAPI010").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))


    val dStream1: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",9999)
    val dStream2: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",8888)

    val wordToOneDStream: DStream[(String, Int)] = dStream1.flatMap(_.split(" ")).map((_,1))
    val wordToTwoDStream: DStream[(String, Int)] = dStream2.flatMap(_.split(" ")).map((_,2))
    val joinDStream: DStream[(String, (Int, Int))] = wordToOneDStream.join(wordToTwoDStream)
    joinDStream.print()

    ssc.start()
    ssc.awaitTermination()
  }
}

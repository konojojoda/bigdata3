package com.zx.spark.streaming.trans

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object window1 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("DirectAPI010").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))


    val dStream1: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",9999)

    dStream1.window(Seconds(9),Seconds(6)).flatMap(_.split(" ")).map((_,1))
        .reduceByKey(_+_).print()


    ssc.start()
    ssc.awaitTermination()
  }
}

package com.zx.spark.streaming.trans

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Window2 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("DirectAPI010").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(2))

    ssc.checkpoint("ck2")

    val dStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",9999)

    val dStream1: DStream[(String, Int)] = dStream.flatMap(_.split(" ")).map((_, 1))
//    dStream1.reduceByKeyAndWindow(_+_,Seconds(6)).print()

    dStream1.reduceByKeyAndWindow((x:Int,y:Int)=>x+y,(x:Int,y:Int)=>x-y,
      Seconds(6),Seconds(4),ssc.sparkContext.defaultParallelism,
      (x:(String,Int))=>x._2>0).print()

    ssc.start()
    ssc.awaitTermination()
  }

}

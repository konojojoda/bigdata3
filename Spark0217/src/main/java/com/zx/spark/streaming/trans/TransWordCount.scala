package com.zx.spark.streaming.trans

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TransWordCount {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("DirectAPI010").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))

    val dStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",9999)
    println(s"111111*******${Thread.currentThread().getName}")
    dStream.transform(rdd=>{
      println(s"2222222222****${Thread.currentThread().getName}")
      rdd.flatMap(_.split(" ")).map(
        a=>{
          println(s"33333****${Thread.currentThread().getName}")
          (a,1)
        }
      ).reduceByKey(_+_)
    }).print()


    ssc.start()
    ssc.awaitTermination()
  }
}

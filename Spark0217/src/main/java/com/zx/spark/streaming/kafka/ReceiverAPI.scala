package com.zx.spark.streaming.kafka

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object ReceiverAPI {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("ReceiverAPI")
    val ssc = new StreamingContext(conf,Seconds(5))
    val map: Map[String, Int] = Map[String,Int]("test" -> 1)

    val kafkaDStream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc,"hadoop102:2181,hadoop103:2181,hadoop104:2181","test",map)
    val kafkaDStream1: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc,"hadoop102:2181,hadoop103:2181,hadoop104:2181","test",Map[String,Int]("test"->2))
    val value: DStream[(String, String)] = kafkaDStream.union(kafkaDStream1)
    value.flatMap(t=>{
      t._2.split(" ")
    }).map((_,1))
        .reduceByKey(_+_)
        .print()
    ssc.start()
    ssc.awaitTermination()
  }
}

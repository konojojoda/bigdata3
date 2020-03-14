package com.zx.spark.streaming.kafka


import java.io.{BufferedReader, InputStreamReader}

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{InputDStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object DirectAPI01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("ReceiverAPI")
    val ssc = new StreamingContext(conf,Seconds(3))

    val value: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc,"hadoop102:2181","test1",Map[String,Int]("test"->3))
    value.flatMap(t=>t._2.split(" "))
        .map((_,1))
        .reduceByKey(_+_)
        .print()


    ssc.start()
    ssc.awaitTermination()
  }
}

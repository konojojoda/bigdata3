package com.zx.spark.streaming.kafka

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object DirectAPI02 {

  def getSCC: StreamingContext = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("getActiveOrCreate")
    val ssc = new StreamingContext(conf,Seconds(3))
    ssc.checkpoint("ck")

    val map: Map[String, String] = Map[String, String](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoop102:9092,hadoop103:9092,hadoop104:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "test2"
    )
     val stream: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,map,Set("test"))
    stream.flatMap{
      case (key,word)=>word.split(" ")
    }.map((_,1)).reduceByKey(_+_).print()

    ssc
  }

  def main(args: Array[String]): Unit = {
    val ssc=StreamingContext.getActiveOrCreate("ck",()=>getSCC)
    ssc.start()
    ssc.awaitTermination()
  }
}

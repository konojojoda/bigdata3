package com.zx.spark.streaming.kafka

import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object DirectAPIHandle {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("DirectAPIHandle")
    val ssc = new StreamingContext(conf, Seconds(3))

    val kafkaParas: Map[String, String] = Map[String, String](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoop102:9092,hadoop103:9092,hadoop104:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "test01"
    )

    val fromOffsets: Map[TopicAndPartition, Long] = Map[TopicAndPartition, Long](
      new TopicAndPartition("bbb", 0) -> 2L,
      new TopicAndPartition("bbb", 1) -> 2L
    )

    val kafkaDStream: InputDStream[MessageAndMetadata[String, String]] = KafkaUtils.createDirectStream
      [String, String, StringDecoder, StringDecoder, MessageAndMetadata[String, String]](
        ssc, kafkaParas, fromOffsets,
        (m: MessageAndMetadata[String, String]) => m)

    // Hold a reference to the current offset ranges, so it can be used downstream
    //定义空数组
    var offsetRanges = Array.empty[OffsetRange]

    val afterOffsetDStream: DStream[MessageAndMetadata[String, String]] = kafkaDStream.transform { rdd =>
      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      rdd
    }
    val ddStream: DStream[String] = afterOffsetDStream.map(_.message())
    ddStream.foreachRDD { rdd =>
      rdd.foreach(println)
      for (o <- offsetRanges) {
        println(s"${o.topic}->${o.partition}->${o.fromOffset}->${o.untilOffset}")
      }

    }

    ssc.start()
    ssc.awaitTermination()

  }
}

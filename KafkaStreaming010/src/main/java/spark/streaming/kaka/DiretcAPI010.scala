package spark.streaming.kaka

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object DiretcAPI010 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("DirectAPI010").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))

    val kafkaParams: Map[String, Object] = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoo102:9092,hadoo103:9092,hadoop104:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "aGroup1",
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer",
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer"
    )


    val kafkaDStream: InputDStream[ConsumerRecord[String, String]] =
      KafkaUtils.createDirectStream[String, String](
      ssc, LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set("bbb"), kafkaParams))

    kafkaDStream.flatMap(t=>t.value().split(" ")).map((_,1)).reduceByKey(_+_).print()

    ssc.start()
    ssc.awaitTermination()
  }

}

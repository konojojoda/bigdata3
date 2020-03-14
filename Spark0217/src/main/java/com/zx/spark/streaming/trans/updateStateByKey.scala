package com.zx.spark.streaming.trans

import com.zx.spark.streaming.receiver.MyReceiver
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object updateStateByKey {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("DirectAPI010").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))
    ssc.checkpoint("ck1")

    val dStream: ReceiverInputDStream[String] = ssc.receiverStream(new MyReceiver("hadoop102",9999))
    val wordToCountDStream: DStream[(String, Int)] = dStream.flatMap(_.split(" ")).map((_, 1)).updateStateByKey(
      (seq: Seq[Int], sum: Option[Int]) => {
        Some(seq.sum + sum.getOrElse(0))
      }
    )
    wordToCountDStream.print()


    ssc.start()
    ssc.awaitTermination()
  }
}

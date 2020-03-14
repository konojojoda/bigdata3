package com.zx.spark.streaming.gracefully

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.streaming.{StreamingContext, StreamingContextState}


class MonitorStop(ssc:StreamingContext) extends Runnable {
  override def run(): Unit = {
    val system: FileSystem = FileSystem.get(new URI("hdfs://hadoop102:9000"), new Configuration(), "zxtest")

    while (true) {
      try
        Thread.sleep(5000)
      catch{
        case e:Exception=>e.printStackTrace()
      }

      val state: StreamingContextState = ssc.getState()
      val bool: Boolean = system.exists(new Path("hdfs://hadoop102:9000/stopSSC"))
      if(bool){
        if(state==StreamingContextState.ACTIVE){
          ssc.stop(stopSparkContext = true,true)
          System.exit(0)
        }
      }
    }

  }
}

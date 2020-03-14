package com.zx.spark.streaming.receiver

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

class MyReceiver(host: String,port: Int) extends Receiver[String](StorageLevel.MEMORY_ONLY) {
    def receive() = {
      try {
        val socket = new Socket(host, port)
        socket.getInputStream()
        val reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        var input = reader.readLine()
        while (!isStopped() && input != null) {
          store(input)
          input = reader.readLine()
        }
        reader.close()
        socket.close()
        restart("restart")
      } catch {
        case e:Exception =>restart("restart")
      }
    }
  override def onStart(): Unit = {




      new Thread {
        override def run() = {
          receive()
        }
      }.start()
    }


  override def onStop(): Unit = {}
}

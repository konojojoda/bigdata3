package com.zx.scala.chapter07

import scala.collection.mutable

object Scala_Collection17_Queue {
  def main(args: Array[String]): Unit = {
   val que=new mutable.Queue[String]()
    que.enqueue("a","b","c")

    println(que.dequeue() + ">>" + que)
    println(que.dequeue() + ">>" + que)
    println(que.dequeue() + ">>" + que)
  }
}


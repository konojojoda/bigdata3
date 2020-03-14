package com.zx.scala.chapter04

object Scala_Control {
  def main(args: Array[String]): Unit = {
    val flag = true
    val result=if(flag){
      println("hello")
      "zhangsan"
    }
    println(result)
  }
}

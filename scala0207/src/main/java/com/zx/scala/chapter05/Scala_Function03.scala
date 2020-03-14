package com.zx.scala.chapter05

object Scala_Function03 {
  def main(args: Array[String]): Unit = {
    def map(arr:Array[Int],op:Int=>Int)={
      for(elem<-arr) yield op(elem)
    }

    val arr=map(Array(1,2,3,4),a=>a*a)
    println(arr.mkString(","))
  }
}

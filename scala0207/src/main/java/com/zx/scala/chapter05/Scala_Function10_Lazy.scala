package com.zx.scala.chapter05

object Scala_Function10_Lazy {
  def main(args: Array[String]): Unit = {
    lazy val laz =  sum(10)
    println("==================")
    println(laz)
  }
        def sum(a:Int):Int={
          println("调用sum")
          return a
        }
//    lazy val res = sum(10, 30) // List(10000)
//    println("----------------") // 10s
//    println("res=" + res)
//  }
//  def sum(n1: Int, n2: Int): Int = {
//    println("sum被执行。。。")
//    return n1 + n2
//  }
  // sum被执行。。。
  // ----------------
  // res=40


}

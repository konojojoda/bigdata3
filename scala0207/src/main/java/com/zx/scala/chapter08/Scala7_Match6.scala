package com.zx.scala.chapter08

object Scala7_Match6 {
  def main(args: Array[String]): Unit = {
   val (x,y)=(1,2)
    println(s"x=$x,y=$y")
    val Array(first,second,_*)=Array(1,2,3,4,5,6)
    println(s"first=$first,second=$second")
  }
}

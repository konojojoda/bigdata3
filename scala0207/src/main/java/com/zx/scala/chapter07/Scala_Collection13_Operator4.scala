package com.zx.scala.chapter07

object Scala_Collection13_Operator4 {
  def main(args: Array[String]): Unit= {
   val list = List(1,2,3,4)
    println(list.fold(5)(_ + _))
    println(list.scan(5)(_ + _))
    println(list.foldLeft("")(_ + _))
    println(list.scanLeft("")(_ + _))
    println(list.foldRight(5)(_ - _))
  }
}


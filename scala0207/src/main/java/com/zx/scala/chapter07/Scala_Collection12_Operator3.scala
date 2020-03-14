package com.zx.scala.chapter07

object Scala_Collection12_Operator3 {
  def main(args: Array[String]): Unit= {
   val list = List(1,2,3,4,5)
    println(list.reduceRight(_ - _))
  }
}


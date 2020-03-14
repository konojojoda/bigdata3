package com.zx.scala.chapter07

object Scala_Collection11_Operator2 {
  def main(args: Array[String]): Unit= {
    val list1 = List(1,2,3,4,5)
    val list2 = List(4,5,6,7)
    println(list1.union(list2))
    println(list1.intersect(list2))
    println(list1.diff(list2))
    println(list1.zip(list2))

    val iterator = list1.sliding(3)
    while(iterator.hasNext){
      println(iterator.next())
    }
  }
}


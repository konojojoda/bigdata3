package com.zx.scala.chapter07

object Scala_Collection6_Tuple {
  def main(args: Array[String]): Unit = {
    val t=(1,20,"zhangsan")
    println(t._1)

    val iterator = t.productIterator
    while(iterator.hasNext){
      println(iterator.next())
    }

    val t1=(1,"aa")
    println(Map(t1))

    val list = List(List(List(1,2)),List(List(3,4)))
    println(list.flatMap(a => a))
    println(list.flatMap(a => a))


  }
}

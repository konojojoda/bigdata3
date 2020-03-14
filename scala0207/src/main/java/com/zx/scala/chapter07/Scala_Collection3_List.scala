package com.zx.scala.chapter07

object Scala_Collection3_List {
  def main(args: Array[String]): Unit = {
    val list = List(1)
    val list1 = list :+ 2


    println(list1)
    println(list)

    val list2=1::2::Nil
    println(list2)

    val list3=list2:::list1:::Nil
    println(list3)
  }
}

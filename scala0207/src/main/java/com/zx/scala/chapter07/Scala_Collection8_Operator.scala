package com.zx.scala.chapter07

object Scala_Collection8_Operator {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4)
    //println(list.product)
    val iterator1: Iterator[Int] = list.iterator
    val iterator: Iterator[Any] = list.productIterator
    println(iterator.next())
    println(iterator.next())
//    while(iterator.hasNext){
//     // println(iterator.next())
//    }
    def loop(i:Int): Unit ={
  println(i)
}
    //list.foreach(loop)
    list.foreach(println )



  }
}

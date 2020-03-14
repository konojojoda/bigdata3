package com.zx.scala.chapter08

object Scala8_Match7 {
  def main(args: Array[String]): Unit = {
  //将list中的Int类型的元素加一，并去掉字符串
    val list:List[Any]=List(1,2,3,4,"aaa")

    val list1 = list.map(
      data => {
        data match {
          case a: Int => a + 1
          case a => a
        }
      }
    )
    println(list1.filter(_.isInstanceOf[Int]))

    println(list.filter(_.isInstanceOf[Int])
      .map(a => a.asInstanceOf[Int] + 1))
    list.collect{case i:Int=>i+1}.foreach(println)
    list.map{case i:Int=>i+1}.foreach(println)
  }
}

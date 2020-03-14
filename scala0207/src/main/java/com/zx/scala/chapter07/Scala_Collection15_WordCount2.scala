package com.zx.scala.chapter07

import scala.collection.mutable


object Scala_Collection15_WordCount2 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("Hello Scala Spark Hadoop", 4),
      ("Hello Scala Spark", 3),
      ("Hello Scala", 2),
      ("Hello ", 1)
    )
    //1.拆分成字符串
    //    println(list.map(
    //      a => (a._1 + " ") * a._2
    //    ))

    //    println(list.map(
    //      a => {
    //        val s = a._1
    //        val cnt = a._2
    //        var s1 = ""
    //        for (i <- 1 to cnt) {
    //          s1 += s + " "
    //        }
    //        s1
    //      }
    //    ))
    //2.将数据进行转换
    println(list.flatMap(
      t => {
        val s = t._1
        val cnt = t._2
        s.split(" ").map(
          word => (word, cnt)
        )
      }
    ).groupBy(a => a._1)
      .mapValues(
        a => a.map(_._2).sum
      ).toList.sortWith(
      (a, b) => a._2 > b._2
    ).take(3))
  }
}


package com.zx.scala

object Test {
  def main(args: Array[String]): Unit = {
    val list: List[List[(String, Int)]] = List(
      List(("Hello World Hadoop Hive", 4), ("Hello Spark Scala Hive", 4)),
      List(("Hello World Hive", 3), ("Hello Spark  Hive", 2)),
      List(("Hello World Hadoop", 2), ("Hello Scala Hive", 3)),
      List(("Hello Hadoop Hive", 1), ("Spark Scala Hive", 1))
    )

    println(list.flatten.flatMap(
      a => {
        a._1.split(" ").map(word => (word, a._2))
      }
    ).filter(_._1.startsWith("H"))
      .groupBy(_._1)
      .mapValues(a => a.map(tuple => tuple._2).sum)
      .toList.sortWith((left, right) => left._2 > right._2)
      .take(2))

    println(list.flatten.flatMap(
      a => {
        val s = a._1.split(" ")
        val count = a._2
        s.map(
          word => {
            (word, count)
          }
        )
      }
    ).groupBy(_._1)
      .mapValues(
        a => a.map(_._2).sum
      )
      .toList.filter(
      a => a._1.startsWith("H")
    ).sortWith(
      (left, right) => {
        left._2 > right._2
      }
    ).take(2))


    println(list.flatten.flatMap(
      a => {
        a._1.split(" ").map(
          s => (s, a._2)
        )
      }
    ).groupBy(_._1)
      .mapValues(
        a => a.map(b => b._2).sum
      )
      .toList.filter(
      a => a._1.startsWith("H")
    ).sortWith(
      (a, b) => {
        a._2 > b._2
      }
    )
      .take(2))


    println(list.flatten.flatMap {
      case (words, count) => {
        words.split(" ").map(
          word => (word, count)
        )
      }
    }.filter(tup => tup._1.startsWith("H"))
      .groupBy(_._1)
      .mapValues(a => {
        a.map(b => b._2).sum
      })
      .toList.sortWith {
      (a, b) => a._2 > b._2
    }.take(2))







  }

}

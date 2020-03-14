package com.zx.scala.chapter07

object Scala_Collection10_WordCount {
  def main(args: Array[String]): Unit= {
    val stringList = List(
      "Hello Hadoop Hive Scala Spark",
      "Hello Hadoop Hive Scala",
      "Hello Hadoop Hive",
      "Hello Hadoop",
      "Hello"
    )
    //1.将集合中的字符串进行扁平化操作，拆分成一个一个的单词
    val strings = stringList.flatMap(list=>list.split(" "))
    //2.将相同的单词放置在一个组中
    val stringToGroups = strings.groupBy(s=>s)
    //println(stringToGroups)
    //3.将分组后的数据进行结构的转换
    val stringToInt = stringToGroups.map(

      t => (t._1, t._2.length)
    )
    //println(stringToInt)
    //4.将转换结构后的数据进行排序
    //println(stringToInt.toList)
    val tuples = stringToInt.toList.sortWith(
      (left, right) => left._2 < right._2
    )
    tuples
    //5.将排序后的数据获取前三名
    val tuple = tuples.take(3)
    //6.将结果打印在控制台上
    //tuple.foreach(println(_))


    println(stringList
      .flatMap(a => a.split(" "))
      .groupBy(a => a)
      //.map(a => (a._1, a._2.length))
      .mapValues(s=>s.length)
      .toList.sortWith((a, b) => a._2 > b._2)
      .take(3))

    println(stringList
      .flatMap(s => s.split(" "))
      .groupBy(a => a)
      .mapValues(a => a.length)
      .toList.sortWith {
      (left, right) => left._2 > right._2
    }.take(3))
  }
}


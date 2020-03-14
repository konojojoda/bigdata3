package com.zx.scala.chapter07

import scala.collection.mutable

object Scala_Collection14_Operator5 {
  def main(args: Array[String]): Unit= {
    val map1 = mutable.Map( ("a", 1), ("b", 2), ("c", 3) )
    val map2 = mutable.Map( ("a", 4), ("d", 5), ("c", 6) )
    println(map1.foldLeft(map2)(
      (a, kv) => {
        val k = kv._1
        val v = kv._2
        a(k) = a.getOrElse(k, 0) + v
        a
      }
    ))
  }
}


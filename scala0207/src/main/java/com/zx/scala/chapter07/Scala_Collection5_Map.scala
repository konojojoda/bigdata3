package com.zx.scala.chapter07

import scala.collection.mutable

object Scala_Collection5_Map {
  def main(args: Array[String]): Unit = {
    val map = Map("a"->1,"b"->2,"c"->3)
    println(map)
    val maybeInt = map.get("d")
    val i = map.getOrElse("a",-1)
    println(i)
    //println(maybeInt.get)
  }
}

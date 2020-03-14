package com.zx.scala.chapter07

import scala.collection.mutable

object Scala_Collection18_Par {
  def main(args: Array[String]): Unit = {
   val result1=(0 to 100).map{num=>Thread.currentThread().getName}
   val result2=(0 to 100).par.map{num=>Thread.currentThread().getName}

    println(result2)
  }
}


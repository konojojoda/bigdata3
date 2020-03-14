package com.zx.scala.chapter07

import scala.collection.mutable.ArrayBuffer

object Scala_Collection2_Array2 {
  def main(args: Array[String]): Unit = {
   var ints = ArrayBuffer(1,2)
    ints.append(3)
    ints.append(4)

    ints.insert(0,3)
    ints.remove(0)
    ints.update(0,5)
    ints(0)=6

    val array = ints.toArray
    println(ints)
    var ints1=(array.toBuffer)
    println(array)
    println(ints1)

    println(ints==ints1)
    println(ints eq ints1)



    println(ints.mkString(","))
  }
}

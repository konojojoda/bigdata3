package com.zx.scala.chapter07

import scala.collection.mutable
object Scala_Collection4_Set {
  def main(args: Array[String]): Unit = {
//   var ints = Set(1,2,3,4)
//    var ints1=ints += 5
//val ints1=ints +5
    //    ints1.add(6)
    //    ints1.add(7)
    //    ints1.add(8)
    //    ints.add(6)
    //    ints.add(8)
    //    ints.add(9)
    //    ints.update(7,true)
    //    ints.update(8,false)
    //    ints.remove(8)
    //    println(ints1)
    val mSet=mutable.Set(1,2,3,4,5,6,7,8)
    println(mSet)
    val set=Set(1,2,3,4,5,6,7,8)
    println(set)
  }
}

package com.zx.scala.chapter07

object Scala_Collection7_Dim {
  def main(args: Array[String]): Unit = {
    val array: Array[Array[Int]] = Array.ofDim[Int](3,4)
    array(1)(2)=8
    for(i<-array){
      for(j<-i){
        print(j+" ")
      }
      println()
    }



  }
}

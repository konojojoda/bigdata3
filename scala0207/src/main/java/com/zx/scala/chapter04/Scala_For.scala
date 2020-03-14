package com.zx.scala.chapter04

object Scala_For {
  def main(args: Array[String]): Unit = {
    for (i <- 1 until 10 by 2){
      println(i)
    }
//    for(i <- 1 to 3){
//      //println("i="+i)
//      for(j <- 1 to 3){
//        println("i="+i)
//        println("j="+j)
//      }
//    }
    for(i <- 1 to 3; j <- 1 to 3){
      println("i="+i+"j="+j)
    }
//    val result=for(i <- 1 to 5){
//      println(i)
//    }
  val result=for(i <- 1 to 5) yield "i= "+i
    println(result)
  }
}

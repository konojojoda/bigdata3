package com.zx.scala.chapter09

object Scala1_Exception {
  def main(args: Array[String]): Unit = {
    try{
      val i=10/0
    }catch{
      case ex:ArithmeticException=>{
        println("除数为0算术异常")
      }
      case e:Exception=>{
        println("其他异常")
      }
    }finally {
      println("xxxxx")
    }
  }
}

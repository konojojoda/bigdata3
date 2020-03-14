package com.zx.scala.chapter04

import scala.util.control.Breaks
import scala.util.control.Breaks._

object Scala_Break {
  def main(args: Array[String]): Unit = {
    Breaks.breakable{
      for(i <- 1 to 10){
        if(i==5){
          Breaks.break()
        }
        println("i="+i)
      }
    }
    breakable{
      for(i <- 1 to 10){
        if(i == 8){
          break()
        }
        println("i="+i)
      }
    }
  }
}

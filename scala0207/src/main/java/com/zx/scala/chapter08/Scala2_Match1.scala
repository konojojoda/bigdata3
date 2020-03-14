package com.zx.scala.chapter08

object Scala2_Match1 {
  def main(args: Array[String]): Unit = {
    def abs(x:Int) ={
      x match{
        case i:Int if i<=0 => -i
        case j:Int if j>=0 =>j
        case _ => "type illegal"
      }
    }

    println(abs(5))

  }
}

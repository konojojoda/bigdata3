package com.zx.scala.chapter08

object Scala1_Match {
  def main(args: Array[String]): Unit = {
    val a:Int=10
    val b=20
    val operator='*'
    val value: Any = operator match {
      case '+' => a + b
      case '-' => a - b
      case '/' => a / b
      case '*' => a * b
      case _ => "illegal"
    }
    println(value)

  }
}

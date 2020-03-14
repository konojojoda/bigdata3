package com.zx.scala.chapter05

object Scala_Function05_Closure {
  def main(args: Array[String]): Unit = {
//    val name = "zhangsan"
//    def test(): Unit ={
//      println(name)
//
//    }
//    test()

    def test()={
      val name ="zhangsan"
      def test1(): Unit ={
        println(name)
      }
      test1 _
    }
    test()()
  }
}

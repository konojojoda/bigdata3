package com.zx.scala.chapter05

object Scala_Function06_clourse02 {
  def main(args: Array[String]): Unit = {
    //不需要访问变量，只要是嵌套函数就会出现闭包效果
    val name="zhangsan"
//    def test() ={
//      def test1(): Unit ={
//        println(name)
//      }
//      test1 _
//    }
//    test()
    //将函数赋值给变量进行调用时，也会产生闭包效果
//    def test()={
//      println(name)
//}
//    val v1=test _
      //匿名函數都有閉包
    val f=()=>println(name)
    f

  }


}

package com.zx.scala.chapter05

object Scala_Function2 {
  def main(args: Array[String]): Unit = {
    //函数做变量
//    def f1(): Unit ={
//      println("test")
//    }
//    val v1:()=>Unit=f1// _
//    v1()
//    println(v1)
////函数做参数
//    def f2(f:(Int)=>Int): Int ={
//      f(10)
//    }
//    def f22(a:Int): Int ={
//      a+10
//    }
//
//    //println(f2(f22))
//    //println(f2((a: Int) => {a + 10}))
//    println(f2(_+10))
    //函数做返回值
    def f3(a:Int) ={
      def f33(): Int ={
        a+15
      }
      f33 _
    }

    println(f3(10)())

  }
}

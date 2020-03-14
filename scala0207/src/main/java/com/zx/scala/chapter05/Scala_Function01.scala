package com.zx.scala.chapter05

object Scala_Function01 {
  def main(args: Array[String]): Unit = {
    //1.函数五参数，无返回值
    def f1():Unit={
      println("f1...")
    }
    //f1
    //2.函数有参数 无返回值
    def f2(s:String):Unit={
      println(s)
    }
    //f2("hello")
    //3.函数有参数有返回值
    def f3(s:String):String={
      return s+"world"
    }

    //println(f3("Hello "))
    //4.函数无参数，有返回值
   def  f4():String={
      return "zhangsan"
    }

    //println(f4())
    //5.函数有多个参数，有返回值
    def f5(a:Int,b:Int):Int={
      return a+b
    }

    //println(f5(10, 20))
    //6.函数有不确定的参数，无返回值
    def f6(a:Int*):Unit={
      println(a)
    }
    f6()
    //7.可变参数和不可变参数同时声明 不可变参数应该放置在可变参数的前面
    //函数的参数使用val声明，无法在函数体中进行修改
    def f7(b:Int,a:Int*):Unit={
      //b=20
      //println("f7...."+a+"=="+b)
      println(a)
    }
    f7(10)
    //f7(10,20)
    //8.给参数设定默认值
    //Scala中可以将参数声明时直接进行默认值的设定
    def f8(password:String="000000"):Unit={
//      if(password==null){
//        password="000000"
//      }
      println(password)
    }
    //f8("a")
    //9.带名参数
    def f9(password:String="000000",name:String):Unit={
      println(name+":"+password)
    }
    //f9(name="zhangsan")
  }
}

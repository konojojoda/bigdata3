package com.zx.scala.chapter06

package object xxx{
  val username:String="zhangsan"
  def test():Unit={
}
}

package xxx {
  class User02{

  }

  object Scala_Object_package1 {
    def main(args: Array[String]): Unit = {
      //1.路径没有限制 Scala编译器会在编译时自动生成文件路径
      //2.package关键字可以多次声明，当前类编译后会在最后package所在的包中生成编译类
      //3.package可以使用层次结构，在package后增加大括号，形成层次结构，
      // 有了自己的作用域范围，子包中的程序可以直接访问父包中的类，而无需导入操作
      //4.包也是对象，在package中无法直接声明属性或方法
      //  在包对象中可以声明属性或方法   包对象中的属性或方法在本包或子包中可以直接访问
      val user = new User02()
      println(user)
      println(username)
      println(test())
    }
  }

}

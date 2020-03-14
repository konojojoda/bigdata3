package com.zx.scala.chapter08

object Scala6_Match5 {
  def main(args: Array[String]): Unit = {
   //样例类
    //专门用于模式匹配，但是也可以当成普通的类来使用
    //样例类需要在普通类的声明前面加case关键字
    //样例类的参数列表不能省略
      //如果样例类没有逻辑，那么大括号可以省略
    //样例类中构造参数自动生成类的属性，默认用val修饰，也可以采用var修饰
    //编译器会给样例类自动生成apply，unapply方法，并且实现可序列化接口
    val user = User06("lisi",22)
    println(user match {
      case User06("lisi", 22) => true
      case a => false
    })

  }
}
case class User06(name:String,age:Int)

package com.zx.scala.chapter08

object Scala5_Match4 {
  def main(args: Array[String]): Unit = {
   //匹配对象
    //构建对象的时候，可以采用new 的语法方式，也可以采用伴生对象的apply方法的方式

    //当对象进行模式匹配的时候，会调用特定的方法unapply方法
    //unapply方法用对象反推参数，如果参数和指定的值保持一致，那么匹配成功
    val user = null
    println(user match {
      case User05("zhangsan", 20) => true
      case a => false
    })


  }
}
class User05(val name:String,val age:Int)
object User05{
  def apply(name: String, age: Int): User05 = new User05(name, age)

  def unapply(user: User05): Option[(String, Int)] = {
    if (user==null)
      None
    else
      Some(user.name,user.age)
  }
}

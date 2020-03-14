package com.zx.scala.chapter06

object Scala_Object {
  def main(args: Array[String]): Unit = {
    val user:User01=new User01()
    println(user.username)

    println(user.login())
  }
}
class User01{
  //声明属性（变量）
  var username="zhangsan"
  //声明方法
  def login():Boolean={
    true
  }
}

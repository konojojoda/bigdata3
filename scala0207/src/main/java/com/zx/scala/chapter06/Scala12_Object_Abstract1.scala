package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala12_Object_Abstract1{
  def main(args: Array[String]): Unit = {
    println(new Child12().s)
  }
}
 abstract class User12(){
  var s :String
}
 class Child12() extends User12(){
    var s="zhangsan"
}


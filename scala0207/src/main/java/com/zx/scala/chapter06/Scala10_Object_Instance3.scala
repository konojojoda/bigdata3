package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala10_Object_Instance3{
  def main(args: Array[String]): Unit = {
    val user=new User10("12")
    println(user.name)
  }
}
 class User10(val name:String){

}
class Child10(name:String) extends User10(name){

}


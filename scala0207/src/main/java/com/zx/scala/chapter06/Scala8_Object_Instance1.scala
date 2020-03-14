package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala8_Object_Instance1_Object_Instance{
  def main(args: Array[String]): Unit = {
    val user=new User08("12")
    println(user)
  }
}
 class User08(){
  def this(a:String){
    this()
  }
}


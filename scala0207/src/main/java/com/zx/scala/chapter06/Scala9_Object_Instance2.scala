package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala9_Object_Instance2_Object_Instance1_Object_Instance{
  def main(args: Array[String]): Unit = {
    val user=new User09("12")
    println(user.name)
  }
}
 class User09(var name:String){

}


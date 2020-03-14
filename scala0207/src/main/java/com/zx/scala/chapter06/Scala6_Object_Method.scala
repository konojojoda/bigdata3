package com.zx.scala.chapter06


import scala.beans.BeanProperty


object Scala6_Object_Method{
  def main(args: Array[String]): Unit = {
    println(User06.apply())
    println(User06())
  }
}
 class User06{

}
object  User06{
  def instance():User06=new User06

  //def apply(): User06 = new User06()
  import java.util._
  def apply() = new Date()
  def apply(name:String):User06=new User06()
}

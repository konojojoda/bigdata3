package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala13_Object_Abstract2{
  def main(args: Array[String]): Unit = {
    println(new Child13().s)
  }
}
 abstract class User13(){
  val s :String="zhangsan"
}
 class Child13() extends User13(){
    override val s:String="lisi"
}


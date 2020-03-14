package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala2_Object_Ext{
  def main(args: Array[String]): Unit = {
    println(Color.BLUE)

  }
}

object Color extends Enumeration{
  val RED=Value(1,"red")
  val BLUE=Value(2,"blue")
  val YELLOW=Value(3,"yellow")
}

object Test20 extends App{
  println("xxxxxx")
}

package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala14_Object_Trait{
  def main(args: Array[String]): Unit = {
    new Person16().run

  }
}
trait  Operator {
  def run():Unit
}

class Person16 extends Operator{
  override def run(): Unit = {
    println("people run...")
  }
}



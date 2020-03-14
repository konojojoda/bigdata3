package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala15_Object_Trait1{
  def main(args: Array[String]): Unit = {
    new Person20().test()

  }
}
class User20{
  def test(): Unit ={
    println("test....")
  }
}
trait  Operator20 extends User20{
  def run():Unit

  override def test(): Unit = {
    println("oper test....")
  }
}

class Person20 extends Operator20{
  def run(): Unit = {
    println("people run...")
  }

  override def test(): Unit = {
    println("person20 test....")
  }
}



package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala11_Object_Abstract{
  def main(args: Array[String]): Unit = {
    new Child11
  }
}
 abstract class User11(){
  def test():Unit
}
 class Child11() extends User11(){
    def test(): Unit = {}
}


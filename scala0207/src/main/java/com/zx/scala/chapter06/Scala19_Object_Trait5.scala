package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala19_Object_Trait5{
  def main(args: Array[String]): Unit = {
    new User19()

  }
}

trait  MyTrait19{
  println("1...")
}
trait MyTrait191 extends MyTrait19 {
  println("2....")
}

class Person19 extends MyTrait19 {
  println("3....")
}
class User19 extends Person19 with MyTrait19 with MyTrait191{
  println("4....")
}



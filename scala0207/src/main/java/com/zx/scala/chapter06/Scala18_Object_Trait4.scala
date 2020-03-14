package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala18_Object_Trait4{
  def main(args: Array[String]): Unit = {
    new User18()

  }
}

trait  MyTrait181{
  println("1...")
}
trait MyTrait182{
  println("2....")
}

class Person18{
  println("3....")
}
class User18 extends Person18 with MyTrait182 with MyTrait181{
  println("4....")
}



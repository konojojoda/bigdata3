package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala17_Object_Trait3{
  def main(args: Array[String]): Unit = {

    new User17().insert()
    val user1 = new User17 with MyTrait17
    user1.update()
  }
}

trait  MyTrait17{
  def update(): Unit ={
    println("update....")
  }
}

class Person17{
  def insert(): Unit ={
    println("insert...")
  }
}
class User17 extends Person17 {

}



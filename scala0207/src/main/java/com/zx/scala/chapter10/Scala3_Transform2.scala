package com.zx.scala.chapter10

object Scala3_Transform2 {
  def main(args: Array[String]): Unit = {
    implicit def test(user:User03) ={
      new User03Ext
    }
   val user = new User03
    user.insert()
    user.update()
  }
}
class User03Ext{
  def update()={
    println("update data...")
  }
}
class User03{
  def insert()={
    println("insert data....")
  }
}
trait update{
  def update()={
    println("update data...")
  }
}

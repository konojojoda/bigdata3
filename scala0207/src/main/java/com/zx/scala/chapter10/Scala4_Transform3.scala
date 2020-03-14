package com.zx.scala.chapter10

object Scala4_Transform3 {
  def main(args: Array[String]): Unit = {

   val user = new User04
    user.insert()
    user.update()
  }
  implicit class User04Ext(user:User04){
    def update()={
      println("update data...")
    }
  }
}

class User04{
  def insert()={
    println("insert data....")
  }
}
trait update{
  def update()={
    println("update data...")
  }
}

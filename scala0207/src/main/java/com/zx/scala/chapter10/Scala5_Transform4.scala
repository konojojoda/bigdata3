package com.zx.scala.chapter10

import com.zx.scala.chapter10.Scala5_Transform4.User05

object Scala5_Transform4 /*extends test*/ {
  def main(args: Array[String]): Unit = {


    val user = new User05()
    user.insert()
    user.update1
  }

  //  implicit class User04Ext(user:User04){
  //    def update()={
  //      println("update data...")
  //    }
  //  }
  class User05 {
    def insert() = {
      println("insert data....")
    }
  }
}

//trait test{}
//object test{
//  implicit class User04a(user:User05){
//    def update()={
//      println("update...")
//    }
//  }
//}

//trait update{
//  def update()={
//    println("update data...")
//  }
//}

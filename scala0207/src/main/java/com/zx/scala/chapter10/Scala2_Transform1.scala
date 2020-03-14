package com.zx.scala.chapter10

object Scala2_Transform1 {
  def main(args: Array[String]): Unit = {
    def regUser(implicit  password:String="000000")={
      println("password="+password)
    }
    implicit val password:String="123123"
    implicit val password1:Int=123124
    regUser
  }
}

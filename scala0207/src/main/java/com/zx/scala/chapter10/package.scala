package com.zx.scala

import com.zx.scala.chapter10.Scala5_Transform4.User05

package object chapter10 {
implicit class User04ext(user:User05){
  def update1={
    println("update...")
  }
}
}

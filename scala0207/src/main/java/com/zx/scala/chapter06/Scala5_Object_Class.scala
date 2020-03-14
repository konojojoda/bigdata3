package com.zx.scala.chapter06



import scala.beans.BeanProperty


object Scala5_Object_Class{
  def main(args: Array[String]): Unit = {
    val user0 = new User05
    //println(user0.age)
    println(user0.name)
  }
  }
class User05{
  @BeanProperty
  var name:String=_

  private var age:Int=_
  protected var sex:String="male"
  private[zx] var sex1:String="male"
}
class ChildUser05 extends User05{
  def test(): Unit ={
    println(sex)
  }
}

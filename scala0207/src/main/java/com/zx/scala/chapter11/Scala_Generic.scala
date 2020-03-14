package com.zx.scala.chapter11

object Scala_Generic {
  def main(args: Array[String]): Unit = {
    new Test[User01]
    //println(test1)
    //test(new Parent01)

  }
  def test[T >: User01](t:T): Unit ={
    println("t="+t)
  }
}
//class Test[User01]{}
//class Test[+User01]{}
//class Test[-User01]{}
class Test[T <: User01]{}
class Parent01{}
class User01 extends Parent01{}
class Child01 extends User01{}

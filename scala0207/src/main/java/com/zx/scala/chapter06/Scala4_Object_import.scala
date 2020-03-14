package com.zx.scala.chapter06




object Scala4_Object_import{
  def main(args: Array[String]): Unit = {
    //1.导类
    //2.import static 静态导入类的静态属性和静态方法

    //1.哪些包中的类无需导入  java.lang   scala  scala.Predef中的属性和方法
    //2.导入一个包中的所有类，采用下划线来代替星号
    //import java.sql._
    //import java.util._
    //3.可以将一个包中多个类在同一行中导入
    //import java.util.{ArrayList, HashMap}
    //new ArrayList[String]()
    //new HashMap[String, Int]()
    //4.不同的包有相同名称类，Scala可以使用特殊的语法隐藏类
    //import java.sql.{Date=>_,Array=>_,_}
    import java.util._
    //println(new Date())
    //5.Scala支持类的重命名
    //import java.lang.{String=>S}
   // val a:S="s"
    val s:String="s1"
    //println(a)
    println(s)
    //6.Scala访问包中的类采用的是相对路径，但是可以使用_root_访问绝对路径
    //查找类是以当前包所在的位置查找的
    //绝对路径：不可改变的路径
    val map=new java.util.HashMap
    println(map)
    //map.put
    val map1=new _root_.java.util.HashMap()
    println(map1)
    //map1.put

  }
}
//package java{
//  package util{
//  class HashMap{
//
//  }
//}
//}

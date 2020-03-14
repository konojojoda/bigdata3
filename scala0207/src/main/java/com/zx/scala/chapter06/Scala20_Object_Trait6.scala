package com.zx.scala.chapter06

import scala.beans.BeanProperty


object Scala20_Object_Trait6{
  def main(args: Array[String]): Unit = {
    val user2 = new MYSQL()
    user2.insert()

  }
}

trait  Oper1{
  def insert(): Unit ={
    println("插入数据")
  }
}
trait FileOper extends Oper1 {
  override def insert(): Unit = {
    print("向文件中")
    super.insert()
  }
}
trait MemoryOper extends Oper1 {
  override def insert(): Unit = {
    print("向内存中")
    super[Oper1].insert()
  }
}


class MYSQL extends FileOper with MemoryOper {

}



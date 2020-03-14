package com.zx.scala.chapter05

import scala.util.control.Breaks

object Scala_Function09_Abstract {
  def main(args: Array[String]): Unit = {
    def myWhile(flag:Boolean)(op: =>Unit):Unit={
      if(flag){
        op
        myWhile(flag)(op)
      }
    }

    myWhile(true)(println("zhangsan"))

  }
}

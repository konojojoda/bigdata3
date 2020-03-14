package com.zx.scala.chapter08

object Scala3_Match2 {
  def main(args: Array[String]): Unit = {
    def describe(x:Any)={
      x match{
        case a:Int=>"Int"
        case a:String=>"String"
        case a:List[Int]=>"List"
        case a:Array[Int]=>"Array"
        case something=>"something else "+something
      }
    }

    println(describe(Array('a','b')))

  }
}

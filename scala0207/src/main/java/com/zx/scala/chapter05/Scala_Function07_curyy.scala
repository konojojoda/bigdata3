package com.zx.scala.chapter05

object Scala_Function07_curyy {
  def main(args: Array[String]): Unit = {
    def test(a:Int)={
      val ar=a+10
      def test1(b:Int)={
        val br=b+20
        def test2(c:Int)={
          val cr=c+30
          ar+br+cr
        }
        test2 _
      }
      test1 _
    }

    println(test(10)(20)(30))
  }


}

package com.zx.scala.chapter05

object Scala_Function08_Recursion {
  def main(args: Array[String]): Unit = {
    //1.函数的内部调用函数本身
    //2.递归函数应该有跳出的逻辑
    //3.递归函数在调用时，参数传递之间应该有规律
    //4.Scala中的递归函数不能省略返回值类型
    def sum(num:Int):Int={
      if(num<=1){
        1
      }else{
        num+sum(num-1)
      }
    }
    //println(sum(100000))
    //尾递归
    def sum1(num:Int,result:Int):Int={
      if(num==0){
        result
      }else{
        sum1(num-1,num+result)
      }
    }
    println(sum1(5000000,0))
  }


}

package com.zx.scala.chapter08

object Scala4_Match3 {
  def main(args: Array[String]): Unit = {
    def test()={
      ("zhangsan",20)
    }
    val t=test()
    //println(t._1+","+t._2)
    val (name,age)=test()
    //println("name="+name+",age="+age)
    //println(s"name=$name,age=$age")
    //循环时也可以采用模式匹配
    val list=List(("a",1),("b",2),("c",3))

    for(a<-list){
      //if(a._1.equals("a"))println(a._2)
    }
    //for(("a",num)<-list){
    for((_,num)<-list){
      //println(num)
    }

    val map: Map[String, Int] = Map(("a",2),("b",4),("c",6))
    val map1 = map.map {
      case (a, b) => (a, b * 2)
    }
    println(map1)

  }
}

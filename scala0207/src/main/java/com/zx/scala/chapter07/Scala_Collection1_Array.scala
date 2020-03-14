package com.zx.scala.chapter07

object Scala_Collection1_Array {
  def main(args: Array[String]): Unit = {
    val strings = new Array[String](3)
    strings(0)="aa"
    strings(1)="bb"
    strings(2)="cc"
    //println(strings)
//    for(s<-strings){
//      println(s)
//    }
    //println(strings.mkString(","))
    val v1 = strings.:+("dd")

    println(v1 mkString(","))

    val ll=new Array[Int](2)
    ll(0)=1
    ll(1)=2
    val l0 = ll.+:(3)
    l0.update(0,5)
    //val l2=(3)-:l1
    println(l0 eq ll)
    println(l0.mkString(","))

    val ints = Array(1,2,3)
    println(ints.mkString(","))

  }
}

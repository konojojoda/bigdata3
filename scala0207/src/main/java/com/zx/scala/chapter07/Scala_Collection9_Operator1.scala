package com.zx.scala.chapter07

object Scala_Collection9_Operator1 {
  def main(args: Array[String]): Unit= {
  val list = List(1,2,3,4,5,6)
    //val ints: List[Int] = list.map(_+10)
    //println(ints)
    val list1: List[List[Int]] = List(List(1,2),List(3,4))
    println(list1.flatMap(list=>list))
    val strings: List[String] = List("hello Scala","Hello Hadoop","Hello Spark")
    println(strings.flatMap(a => a.split(" ")))
    //过滤
    //按照指定的规则对集合中的每一条数据进行筛选过滤
    //满足条件的数据保留下来，不满足条件的数据丢弃
    println(list.filter(a=>a%2==0))
    //分组
    //按照指定的规则对每个数据进行分组操作
    //按照函数规则的返回值作为分组的key,相同的key的数据放置在同一个组中
    //返回结果是一个Mao，其中的Key就是分组key，其中的value就是满足条件的数据
    println(list.groupBy(a=>a%3))
    println(list.sortBy(a=>a)(Ordering.Int.reverse))
    val user1 = new User9()
    val user2 = new User9()
    user1.age=10
    user2.age=20
    val users = List(user1,user2)
    println(users.sortWith(
      (a: User9, b: User9) => a.age < b.age
    ))
//    val list2 = List(("a",1),("b",2),("a",3))
//    for((_,b)<- list2){
//      println(b)
//    }


  }
}
class User9{
  var age=1

  override def toString: String = "user"+age
}

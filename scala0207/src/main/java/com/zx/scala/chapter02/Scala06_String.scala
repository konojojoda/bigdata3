package com.zx.scala.chapter02

object Scala06_String {
  def main(args: Array[String]): Unit = {
    val name:String ="zhangsan"
    //字符串连接
    println("Hello " +name)
    //传值字符串
    printf("name=%s\n",name)
  //json字符串
    val json : String ="{\"name\":\"zhangsan\"}"
    val json1:String="{\"name\":\""+name+"\"}"
    println(json)
    println(json1)
    println(s"name=$name")
    println(s"name=${name.length}")
    println(
      """
        Aname:zhangsan
        Aage:20
        |gender:male
      """.stripMargin('A'))

  }
}

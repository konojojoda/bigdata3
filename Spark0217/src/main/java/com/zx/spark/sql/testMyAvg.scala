package com.zx.spark.sql

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object testMyAvg {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().master("local[*]").appName("MyAvg").getOrCreate()
    import spark.implicits._
    //spark.udf.register("myAvg",MyAvg)
    spark.udf.register("myMax",MyMax)
    val rdd: RDD[String] = spark.sparkContext.textFile("input/people.txt")
    rdd.map(line=>{
      val datas: Array[String] = line.split(",")
      if(datas.length==2){
        Person(datas(0),datas(1).trim.toInt)
      }else
        Person(datas(0),Integer.MIN_VALUE)
    }).toDF().createOrReplaceTempView("person")
    //spark.sql("select myAvg(age) from person").show()
    spark.sql("select myMax(age) from person").show()
    spark.stop()

  }
}
case class Person(name:String,age:Int)

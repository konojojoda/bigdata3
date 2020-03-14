package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置APP名称
    val conf = new SparkConf().setAppName("wordcount").setMaster("local[*]")
    //2.创建SparkContext，该对象是提交Spark APP的入口
    val context = new SparkContext(conf)
    //3.指定读取文件的位置
    val lineRDD: RDD[String] = context.textFile("input")

    val wordRDD: RDD[String] = lineRDD.flatMap(line=>line.split(" ")).distinct()

    //wordRDD.map((_, 1)).reduceByKey(_ + _).collect().foreach(println)
    context.textFile("input").flatMap(line=>line.split(" "))
      .map(word=>(word,1))
        .reduceByKey(_+_).collect().foreach(println)




    context.stop()
  }
}

package com.zx.spark.dep

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object Spark03_Dep03 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("serial")
    val sc = new SparkContext(conf)
    sc.setCheckpointDir("cp")

    val rdd: RDD[String] = sc.textFile("input/1.txt")

    val rdd1: RDD[String] = rdd.flatMap(a=>{
      println("flatmap.....")
      a.split(" ")
    })

    val rdd2: RDD[(String, Int)] = rdd1.map((_,1))
    //println(rdd2.dependencies)
    //println("=================")

    val rdd3: RDD[(String, Int)] = rdd2.reduceByKey(_+_)
    //rdd2.cache()
    //rdd2.persist(StorageLevel.DISK_ONLY)
    rdd2.checkpoint()
    rdd2.cache()


    val rdd4: RDD[(String, Int)] = rdd3.sortByKey()
    rdd2.saveAsTextFile("output6")
    println("===========================")
    rdd2.saveAsTextFile("output7")
    rdd2.saveAsTextFile("output8")
    rdd2.collect()

    sc.stop()
//val lineRDD: RDD[String] = sc.textFile("input/1.txt")
//
//    val wordRDD: RDD[String] = lineRDD.flatMap(line=>{
//      println("flatMap......")
//      line.split(" ")
//    })
//
//    val wordToOneRDD: RDD[(String, Int)] = wordRDD.map(word=>(word, 1))
//    // RDD的cache方法默认只能放置在内存中
//    // 如果想要将数据保存到磁盘中，那么可以采用persist方法
//    //wordToOneRDD.cache()
//    //wordToOneRDD.persist(StorageLevel.MEMORY_AND_DISK)
//    // RDD中不存储数据的，所以同一个RDD执行多个不同分支的操作，会从头多次执行数据䣌加载
//    // 所以为了提高效率，可以将RDD计算的结果保存到缓存中方便下一回的使用
//    // 如果RDD执行过程中出现的错误，那么可以将那些执行时间长或数据非常重要的
//    // 缓存起来，防止数据丢失，并且提高效率
//    wordToOneRDD.saveAsTextFile("output1")
//    println("***************************")
//    wordToOneRDD.saveAsTextFile("output2")
//
//    //val wordToSumRDD: RDD[(String, Int)] = wordToOneRDD.reduceByKey( (x, y) => x + y )
//
//    //wordToSumRDD.collect().foreach(println)
//    wordToSumRDD.collect().foreach(println)
//    sc.stop()
  }
}

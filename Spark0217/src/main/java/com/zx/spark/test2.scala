package com.zx.spark

//import org.apache.commons.lang.mutable
import scala.collection.mutable
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object test2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("aaa")
    val sc = new SparkContext(conf)

    val rdd: RDD[String] = sc.makeRDD(List("Hello","Hello","Spark","Scala"))
    print("1==")
    println(rdd.countByValue().mkString(","))
    print("2==")
    println(rdd.groupBy(a => a).mapValues(list => list.size).collect().mkString(","))
    print("3==")
    println(rdd.map((_, 1)).countByKey().mkString(","))
    print("4==")
    println(rdd.map((_, 1)).foldByKey(0)(_ + _).collect().mkString(","))
    print("5==")
    println(rdd.map((_, 1)).aggregateByKey(0)(_ + _, _ + _).collect().mkString(","))
    print("6==")
    println(rdd.map((_, 1)).reduceByKey(_ + _).collect().mkString(","))
    print("7==")
    println(rdd.map((_, 1)).groupByKey().mapValues(list => list.size).collect().mkString(","))
    print("8==")
    println(rdd.map((_, 1)).groupByKey().mapValues(list => list.sum).collect().mkString(","))
    print("9==")
    println(rdd.map((_, 1)).combineByKey(a => a,
      (a: Int, b) => a + b,
      (a: Int, b: Int) => a + b
    ).collect().mkString(","))
    print("10==")
    println(rdd.groupBy(a => a).mapValues(list => list.count(a => true)).collect().mkString(","))

    println("==============")
    println(rdd.map((_, 1)).reduce(
      (t1, t2) => {
        if (t1._1 == t2._1)
          (t1._1, t1._2 + t2._2)
        else
          t1
      }
    ))
    println("==============")

    rdd.map(a=> mutable.Map((a,1))).reduce{
      (m1,m2)=>{
        m1.foldLeft(m2)(
          (m3,kv)=>{
            val k = kv._1
            m3(k)=m3.getOrElse(kv._1,0)+kv._2
            m3
          }
        )
      }
    }.foreach(println)





  }
}

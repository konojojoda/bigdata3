package com.zx.spark.acc

import org.apache.spark.rdd.RDD
import org.apache.spark.util.{AccumulatorV2, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object Spark1_Acc1 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("json").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[String] = sc.makeRDD(List("Hello","Spark","Hello"))
    val sum: LongAccumulator = sc.longAccumulator("sum")
    val acc = new WordCountAccumulator

    sc.register(acc)
    rdd.foreach(
      a=>acc.add(a)
    )
    println(acc.value)


    sc.stop()
  }
  //Spark在闭包检测时，会对累加器进行序列化，序列化时，会执行writereplace方法
  //方法中会调用copy方法对累加器的复制，然后进行累加器的重置操作
  //接下来判断累加器是否isZero
  class WordCountAccumulator extends AccumulatorV2[String,mutable.Map[String,Long]]{
    var map=mutable.Map[String,Long]()
    override def isZero: Boolean = map.isEmpty

    override def copy(): AccumulatorV2[String, mutable.Map[String, Long]] = new WordCountAccumulator

    override def reset(): Unit = map.clear()

    override def add(v: String): Unit = {
      map(v)=map.getOrElse(v,0L)+1L
    }

    override def merge(other: AccumulatorV2[String, mutable.Map[String, Long]]): Unit = {
      val map1=map
      val map2=other.value
      map=map1.foldLeft(map2)(
        (map3,kv)=>{
          map3(kv._1)=map3.getOrElse(kv._1,0L)+kv._2
          map3
        }
      )
    }

    override def value: mutable.Map[String, Long] = map
  }
}

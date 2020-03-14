package com.zx.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark2_Core_Transform_map {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("make")
    val sc: SparkContext = new SparkContext(conf)
    //1.创建集合
    val list = List(1,2,3,4)
    //2.创建RDD
    val value = sc.makeRDD(list,2)
    //val transRDD: RDD[Int] = value.map(num=>num*2)
    //val transRDD: RDD[Int] = value.mapPartitions(list=>list.map(a=>a*2))
    value.mapPartitionsWithIndex(
      (index,list)=>{
        if(index==0){
          list
        }else{
          list.map(_*2)
        }
      }
    )
    value.mapPartitionsWithIndex(
      (index,list)=>{
        list.map(a=>(index,a))
      }
    ).collect().foreach(println)
    //3.将数据输出
    //transRDD.collect().foreach(println)




    sc.stop()
  }
}

package com.zx.spark.project.helper

import com.zx.spark.project.bean.HotCategory
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

class HotCategoryAccumulator1 extends AccumulatorV2[(String,String),mutable.Map[String,HotCategory]]{
  var map = mutable.Map[String,HotCategory]()
  override def isZero: Boolean = map.isEmpty

  override def copy(): AccumulatorV2[(String, String), mutable.Map[String, HotCategory]] = new HotCategoryAccumulator1

  override def reset(): Unit = map.clear()

  override def add(v: (String, String)): Unit = {
    val hc=map.getOrElse(v._1,HotCategory(v._1,0,0,0))
    v._2 match{
      case "click"=>hc.clickCount+=1
      case "order"=>hc.orderCount+=1
      case "pay"=>hc.payCount+=1
      case _=>
    }
    map(v._1)=hc
  }

  override def merge(other: AccumulatorV2[(String, String), mutable.Map[String, HotCategory]]): Unit = {
//    other.value.foreach{
//      case (cid,hc2)=>{
//        val hc1: HotCategory = map.getOrElse(cid,HotCategory(cid,0,0,0))
//        hc1.orderCount = hc1.orderCount + hc2.orderCount
//        hc1.clickCount = hc1.clickCount + hc2.clickCount
//        hc1.payCount = hc1.payCount + hc2.payCount
//        map(cid)=hc1
//      }
//    }
    map=map.foldLeft(other.value) {
      case (map1, kv) => {
        val key: String = kv._1
        val hc1: HotCategory = kv._2 //map
        val hc2: HotCategory = map1.getOrElse(key, HotCategory(key, 0, 0, 0))//map1
        hc1.orderCount = hc1.orderCount + hc2.orderCount
        hc1.clickCount = hc1.clickCount + hc2.clickCount
        hc1.payCount = hc1.payCount + hc2.payCount
        map1(key) = hc1
        map1
      }
    }
  }

  override def value: mutable.Map[String, HotCategory] = map
}

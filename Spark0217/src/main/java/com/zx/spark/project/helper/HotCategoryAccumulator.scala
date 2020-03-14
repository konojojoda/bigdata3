package com.zx.spark.project.helper

import com.zx.spark.project.bean.HotCategory
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

class HotCategoryAccumulator extends AccumulatorV2[(String,String),mutable.Map[String,HotCategory]]{
  var map=mutable.Map[String,HotCategory]()

  override def isZero: Boolean = map.isEmpty

  override def copy(): AccumulatorV2[(String, String), mutable.Map[String, HotCategory]] = new HotCategoryAccumulator

  override def reset(): Unit = map.clear()

  override def add(v: (String, String)): Unit = {
    val hotCategory: HotCategory = map.getOrElse(v._1,HotCategory(v._1,0,0,0))
    v._2 match{
      case "click"=>hotCategory.clickCount+=1
      case "order"=>hotCategory.orderCount+=1
      case "pay"=>hotCategory.payCount+=1
      case _=>
    }
    map(v._1)=hotCategory
  }

  override def merge(other: AccumulatorV2[(String, String), mutable.Map[String, HotCategory]]): Unit = {
    other.value.foreach{
      case (cid,hc1)=>{
        val hc2=map.getOrElse(cid,HotCategory(cid,0,0,0))
        hc2.clickCount=hc2.clickCount+hc1.clickCount
        hc2.orderCount=hc2.orderCount+hc1.orderCount
        hc2.payCount=hc2.payCount+hc1.payCount
        map(cid)=hc2
      }
    }
  }

  override def value: mutable.Map[String, HotCategory] = map
}

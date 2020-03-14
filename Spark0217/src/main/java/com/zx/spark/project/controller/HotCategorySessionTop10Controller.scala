package com.zx.spark.project.controller

import com.zx.spark.project.common.TController
import com.zx.spark.project.service.HotCategorySessionTop10Service
import org.apache.spark.rdd.RDD

class HotCategorySessionTop10Controller extends TController() {
  val hotCategorySessionTop10Service=new HotCategorySessionTop10Service()
  override def execute(): Unit = {
    val rdd= hotCategorySessionTop10Service.compute()
    rdd.collect().foreach(println)
  }
}

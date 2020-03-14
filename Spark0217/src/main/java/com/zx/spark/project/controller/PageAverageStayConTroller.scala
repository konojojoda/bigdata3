package com.zx.spark.project.controller

import com.zx.spark.project.common.TController
import com.zx.spark.project.service.PageAverageStayService
import org.apache.spark.rdd.RDD

class PageAverageStayConTroller extends TController {
  override def execute(): Unit = {
    val service = new PageAverageStayService()
    val result: RDD[String] = service.compute()
    result.collect().foreach(println)
  }
}

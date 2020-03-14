package com.zx.spark.project.controller

import com.zx.spark.project.common.TController
import com.zx.spark.project.service.HotCategoryTop10Service
import org.apache.spark.rdd.RDD

case class HotCategoryTop10Controller() extends TController{
  private val hotCategoryTop10Service = new HotCategoryTop10Service()
  override def execute()= {
    val result= hotCategoryTop10Service.compute4()


    result.take(10).foreach(println)
  }

}

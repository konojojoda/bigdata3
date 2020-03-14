package com.zx.spark.project.controller

import com.zx.spark.project.common.TController
import com.zx.spark.project.service.TransfromRateService
import org.apache.spark.rdd.RDD

case class TransfromRateController() extends TController {
  val service = new TransfromRateService()

  override def execute(): Unit = {
    val result = service.compute()
    result.collect().foreach(println)
  }
}

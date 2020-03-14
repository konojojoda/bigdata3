package com.zx.spark.project.application

import com.zx.spark.project.common.TApplication
import com.zx.spark.project.controller.TransfromRateController

object TransfromRateApplication extends App with TApplication {
  startOnLocal() {
  val controller=new TransfromRateController()
    controller.execute()
  }
}

package com.zx.spark.project.application

import com.zx.spark.project.common.TApplication
import com.zx.spark.project.controller.PageAverageStayConTroller

object PageAverageStayApplication extends App with TApplication {
  startOnLocal() {
    val controller = new  PageAverageStayConTroller
    controller.execute()
  }
}

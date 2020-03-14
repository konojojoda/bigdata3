package com.zx.spark.project.application

import com.zx.spark.project.common.TApplication
import com.zx.spark.project.controller.HotCategoryTop10Controller

object HotCategoryTop10Application extends App with TApplication {
  startOnLocal() {
    val hotCategoryTop10Controller = new HotCategoryTop10Controller()
    hotCategoryTop10Controller.execute()
  }
}

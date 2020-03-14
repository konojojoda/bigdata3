package com.zx.spark.project.application

import com.zx.spark.project.common.TApplication
import com.zx.spark.project.controller.HotCategorySessionTop10Controller

object HotCategorySessionTop10Application extends App with TApplication{
  startOnLocal(){
    val hotCategorySessionTop10Controller=new HotCategorySessionTop10Controller()
    hotCategorySessionTop10Controller.execute()
  }
}

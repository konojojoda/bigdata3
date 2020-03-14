package com.zx.spark.project.util

import org.apache.spark.{SparkConf, SparkContext}

object ProjectLocal {
  private val scLocal = new ThreadLocal[SparkContext]
  def getSparkContextOnLocal() = {
    getSparkContext("local[*]","Applocation")
  }
  def getSparkContext(master:String,appname:String)={
    val sc: SparkContext = scLocal.get()
    if (sc==null){
      val conf: SparkConf = new SparkConf().setMaster(master).setAppName(appname)
      val sc1 = new SparkContext(conf)
      scLocal.set(sc1)
      sc1
    }else{
      sc
    }
  }

  def stopOnLocal()={
    getSparkContextOnLocal().stop()
  }
  def stop(master:String,appname:String)={
    getSparkContext(master,appname).stop()
  }
}

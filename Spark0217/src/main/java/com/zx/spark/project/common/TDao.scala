package com.zx.spark.project.common

import com.zx.spark.project.util.ProjectLocal
import org.apache.spark.rdd.RDD

trait TDao {
  def textFile(implicit path:String):RDD[String]={
    val rdd: RDD[String] = ProjectLocal.getSparkContextOnLocal().textFile(path)
    rdd
  }

}

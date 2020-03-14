package com.zx.spark.project.common

import com.zx.spark.project.bean.UserVisitAction
import org.apache.spark.rdd.RDD
import com.zx.spark.project.service._

trait TService {
  def getDao():TDao
  def getActionRDD()={
    val rdd: RDD[String] = getDao().textFile
    val actionRDD: RDD[UserVisitAction] = rdd.map(
      action => {
        val aa: Array[String] = action.split("_")
        UserVisitAction(
          aa(0),
          aa(1).toLong,
          aa(2),
          aa(3).toLong,
          aa(4),
          aa(5),
          aa(6).toLong,
          aa(7).toLong,
          aa(8),
          aa(9),
          aa(10),
          aa(11),
          aa(12).toLong
        )
      }
    )
    actionRDD
  }
}

package com.zx.spark.project.common

import com.zx.spark.project.util.ProjectLocal
import org.apache.spark.SparkContext

trait TApplication {
  def start(master: String, appname: String)(op: => Unit) = {
    val sc: SparkContext = ProjectLocal.getSparkContext(master, appname)
    try {
      op
    } catch {
      case e: Exception => {
        e.printStackTrace()
      }
    } finally {
      ProjectLocal.stop(master, appname)
    }
  }
  def startOnLocal()(op: =>Unit)={
    start("local[*]","application")(op)
  }

}

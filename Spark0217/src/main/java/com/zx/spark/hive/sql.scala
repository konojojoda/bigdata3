package com.zx.spark.hive

import org.apache.spark.sql.SparkSession

object sql {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().master("local[*]")
      .appName("test")
      .enableHiveSupport()
      .getOrCreate()
    import spark.implicits._

    spark.sql("show databases").show()
    spark.sql("use gmall").show()
    spark.sql("show tables").show()
    spark.sql("select * from gmall.ads_uv_count").show()

  }
}

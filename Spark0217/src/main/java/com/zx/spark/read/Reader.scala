package com.zx.spark.read

import org.apache.spark.sql.{DataFrame, SparkSession}

object Reader {
  def main(args: Array[String]): Unit = {
    //1.创建session对象
    val spark: SparkSession = SparkSession.builder()
      .appName("Reader")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._

    //val df: DataFrame = spark.read.json("input/test.json")
    val df: DataFrame = spark.read.format("json").load("input/test.json")
    df.show()
    spark.stop()
  }
}

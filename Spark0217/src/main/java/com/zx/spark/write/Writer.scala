package com.zx.spark.write

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object Writer {
  def main(args: Array[String]): Unit = {
    //1.创建session对象
    val spark: SparkSession = SparkSession.builder()
      .appName("Writer")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._

    val df: DataFrame = spark.read.format("json").load("input/test.json")

    df.createOrReplaceTempView("people")
    df.select("username").write.save("out1")
    spark.sql("select username from people").write.save("out2")
    df.write.mode(SaveMode.Overwrite).json("out")



    spark.stop()
  }
}

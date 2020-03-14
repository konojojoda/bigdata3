package com.zx.spark.jdbc

import org.apache.spark.sql.SparkSession

object JdbcReader {
  def main(args: Array[String]): Unit = {
    //1.创建session对象
    val spark: SparkSession = SparkSession.builder()
      .appName("Writer")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._

    spark.read.format("jdbc")
        .option("user","root")
        .option("url","jdbc:mysql://hadoop102:3306/gmall")
        .option("password","123456")
        .option("dbtable","base_category1")
        .load().show()








    spark.stop()
  }
}

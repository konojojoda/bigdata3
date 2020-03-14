package com.zx.spark.project1

import org.apache.spark.sql.SparkSession

object MainClass  {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().master("local[*]")
      .appName("test")
      //.config("HADOOP_USER_NAME","zx")
      .enableHiveSupport()
      .getOrCreate()
    import spark.implicits._
    spark.udf.register("cityRatio",MyUDAF)
//    city_info
//    product_info
//    user_visit_action
    //spark.sql("create table aa(id int)").show()

    spark.sql(
      """
        |select
        |    ci.area area,
        |    pi.product_name pname,
        |    ci.city_name cname
        |from
        |    (select * from sparkpractice.user_visit_action where click_category_id>-1) uv
        |join
        |    sparkpractice.city_info ci
        |on
        |    uv.city_id=ci.city_id
        |join
        |    sparkpractice.product_info pi
        |on
        |    uv.click_product_id=pi.product_id
      """.stripMargin).createTempView("joinTableTmpView")
    spark.sql(
      """
        |select
        |    area,
        |    pname,
        |    count(*) ct,
        |    cityRatio(cname)  city_ratio
        |from
        |    joinTableTmpView
        |group by
        |    area,pname
      """.stripMargin).createTempView("cityRatioTmpView")
    spark.sql(
      """
        |select
        |    area,
        |    pname,
        |    ct,
        |    city_ratio,
        |    rank() over(partition by area order by ct desc) rk
        |from
        |    cityRatioTmpView
      """.stripMargin).createTempView("rankTmpView")
    spark.sql(
      """
        |select
        |    area,
        |    pname,
        |    ct,city_ratio
        |from
        |    rankTmpView
        |where
        |    rk <=3
      """.stripMargin).show(1000,false)

    spark.stop()
  }

}

package com.zx.spark.project1

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._

object MyUDAF extends UserDefinedAggregateFunction{
  override def inputSchema: StructType = StructType(StructField("cityName",StringType)::Nil)

  override def bufferSchema: StructType = StructType(StructField("cityCount",MapType(StringType,LongType))::StructField("total",LongType)::Nil)

  override def dataType: DataType = StringType

  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer)= {
    buffer(0)=Map[String,Long]()
    buffer(1)=0L
  }

  override def update(buffer: MutableAggregationBuffer, input: Row) = {
    val cityCount: collection.Map[String, Long] = buffer.getMap[String,Long](0)
    val cityName: String = input.getString(0)
    buffer(0)=cityCount+(cityName->(cityCount.getOrElse(cityName,0L)+1L))
    buffer(1)=buffer.getLong(1)+1L
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row) = {
    val cityCt1: collection.Map[String, Long] = buffer1.getMap[String,Long](0)
    val cityCt2: collection.Map[String, Long] = buffer2.getMap[String,Long](0)
    buffer1(0)=cityCt1.foldLeft(cityCt2){
      case (map,(cityName,times))=>{
        map+(cityName->(map.getOrElse(cityName,0L)+times))
      }
    }
    buffer1(1)=buffer1.getLong(1)+buffer2.getLong(1)
  }

  override def evaluate(buffer: Row) = {
    val cityCount: collection.Map[String, Long] = buffer.getMap[String,Long](0)
    val total: Long = buffer.getLong(1)
    val top2CityCount= cityCount.toList.sortWith(_._2>_._2).take(2)
    var otherRatio=1D
    var result: List[CityRemark] = top2CityCount.map(t => {
      val ratio: Double = t._2.toDouble / total
      otherRatio -= ratio
      CityRemark(t._1, ratio)
    })
    if(cityCount.size>2){
      result=result :+ CityRemark("其他",otherRatio)
    }
    result.mkString(",")
  }
}

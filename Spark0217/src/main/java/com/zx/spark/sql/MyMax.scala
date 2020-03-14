package com.zx.spark.sql

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._

object MyMax extends UserDefinedAggregateFunction {
  //输入数据类型
  override def inputSchema: StructType = StructType(StructField("age", IntegerType) :: Nil)
  //中间数据类型
  override def bufferSchema: StructType = StructType(StructField("maxage", IntegerType):: Nil)
  //输出数据类型
  override def dataType: DataType = IntegerType
  //输出是否稳定
  override def deterministic: Boolean = true
  //初始值
  override def initialize(buffer: MutableAggregationBuffer)= {
    buffer(0)=Integer.MIN_VALUE
  }
  //分区内增加数据
  override def update(buffer: MutableAggregationBuffer, input: Row) = {
    if(input.getInt(0)!=0){
      buffer(0)=Math.max(buffer.getInt(0),input.getInt(0))
    }
  }
  //分区间合并
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row)= {
    buffer1(0)=Math.max(buffer1.getInt(0),buffer2.getInt(0))
  }

  override def evaluate(buffer: Row)= {
    buffer.getInt(0)
  }
}

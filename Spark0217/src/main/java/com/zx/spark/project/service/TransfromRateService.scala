package com.zx.spark.project.service

import com.zx.spark.project.bean
import com.zx.spark.project.common.{TDao, TService}
import com.zx.spark.project.dao.TransfromRateDao
import org.apache.spark.rdd.RDD

import scala.collection.mutable

case class TransfromRateService() extends TService {
  private val list :List[Int]=List(1,2,3,4,5,6,7)
  private val zipList: List[String] = list.zip(list.tail).map(t=>{t._1+"->"+t._2})
  val dao=new TransfromRateDao()
  override def getDao(): TDao = {dao}
  def computeFilter()= {
    val actionRDD: RDD[bean.UserVisitAction] = getActionRDD()
    actionRDD.cache()
    //获取分母
    val actionRDD1: RDD[bean.UserVisitAction] = actionRDD.filter(a=>list.init.contains(a.page_id))
    val dividenRDD: RDD[(Long, Long)] = actionRDD1.map(action=>(action.page_id,1L)).reduceByKey(_+_)
    val tuples: Array[(Long, Long)] = dividenRDD.collect()
    //获取分子
    val sessionRDD: RDD[(String, (Long, String))] = actionRDD.map(action=>(action.session_id,(action.page_id,action.action_time)))
    val groupRDD: RDD[(String, Iterable[(Long, String)])] = sessionRDD.groupByKey()
    val sortRDD: RDD[(String, List[(Long, String)])] = groupRDD.mapValues(
      list => list.toList.sortWith(
        (left, right) => left._2 < right._2
      )
    )
    val listPidRDD: RDD[List[Long]] = sortRDD.map(_._2).map(list=>{list.map(_._1)})
    val zipRDD: RDD[List[(Long, Long)]] = listPidRDD.map(list=>{list.zip(list.tail)})
    val value: RDD[(String, Int)] = zipRDD.flatMap(list => {
      list.map {
        case (a, b) => {
          (a + "->" + b, 1)
        }
      }
    }).filter(t=>zipList.contains(t._1))
    val valueSumRDD: RDD[(String, Int)] = value.reduceByKey(_+_)
    val result1: RDD[(String, Double)] = valueSumRDD.map {
      case (aTob, sum) => {
        val divide: Long = tuples.toMap.getOrElse(aTob.split("->")(0).toLong, 1L)
        (aTob, sum * 1.0 / divide)
      }
    }
    result1
  }
  def compute()= {
    val actionRDD: RDD[bean.UserVisitAction] = getActionRDD()
    actionRDD.cache()
    //获取分母
    //val actionRDD1: RDD[bean.UserVisitAction] = actionRDD.filter(a=>list.init.contains(a.page_id))
    val dividenRDD: RDD[(Long, Long)] = actionRDD.map(action=>(action.page_id,1L)).reduceByKey(_+_)
    val tuples: Array[(Long, Long)] = dividenRDD.collect()
    //获取分子
    val sessionRDD: RDD[(String, (Long, String))] = actionRDD.map(action=>(action.session_id,(action.page_id,action.action_time)))
    val groupRDD: RDD[(String, Iterable[(Long, String)])] = sessionRDD.groupByKey()
    val sortRDD: RDD[(String, List[(Long, String)])] = groupRDD.mapValues(
      list => list.toList.sortWith(
        (left, right) => left._2 < right._2
      )
    )
    val listPidRDD: RDD[List[Long]] = sortRDD.map(_._2).map(list=>{list.map(_._1)})
    val zipRDD: RDD[List[(Long, Long)]] = listPidRDD.map(list=>{list.zip(list.tail)})
    val value: RDD[(String, Int)] = zipRDD.flatMap(list => {
      list.map {
        case (a, b) => {
          (a + "->" + b, 1)
        }
      }
    })//.filter(t=>zipList.contains(t._1))
    val valueSumRDD: RDD[(String, Int)] = value.reduceByKey(_+_)
    val result1: RDD[(String, Double)] = valueSumRDD.map {
      case (aTob, sum) => {
        val divide: Long = tuples.toMap.getOrElse(aTob.split("->")(0).toLong, 1L)
        (aTob, sum * 1.0 / divide)
      }
    }
    result1
  }
}
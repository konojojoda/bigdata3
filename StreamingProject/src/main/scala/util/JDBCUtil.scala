package util

import java.sql.{Connection, PreparedStatement, ResultSet}
import java.util.Properties

import bean.Ads_log
import javax.sql.DataSource
import com.alibaba.druid.pool.DruidDataSourceFactory
import org.apache.spark.streaming.dstream.DStream

import scala.collection.mutable.ListBuffer

object JDBCUtil {



  //初始化连接池
  var dataSource: DataSource = init()

  //初始化连接池方法
  def init(): DataSource = {
    val properties = new Properties()
    val config: Properties = PropertiesUtil.load("config.properties")
    properties.setProperty("driverClassName", "com.mysql.jdbc.Driver")
    properties.setProperty("url", config.getProperty("jdbc.url"))
    properties.setProperty("username", config.getProperty("jdbc.user"))
    properties.setProperty("password", config.getProperty("jdbc.password"))
    properties.setProperty("maxActive", config.getProperty("jdbc.datasource.size"))
    DruidDataSourceFactory.createDataSource(properties)
  }

  //获取MySQL连接
  def getConnection: Connection = {
    dataSource.getConnection
  }
  def isExist(connection: Connection,sql:String,params:Array[Any]):Boolean={
    var pst:PreparedStatement=null
    var flag:Boolean=false
    try {
      pst = connection.prepareStatement(sql)
      for (i <- params.indices) {
        pst.setObject(i + 1, params(i))
      }
      flag = pst.executeQuery().next()
      pst.close()
    } catch {
      case e:Exception =>e.printStackTrace()
    }
    flag
  }

  def insertOrUpdate(connection: Connection, sql: String, params: Array[Any]): Unit= {
    var pst:PreparedStatement=null
    try {
      pst = connection.prepareStatement(sql)
      for (i <- params.indices) {
        pst.setObject(i + 1, params(i))
      }
      pst.executeUpdate()
      pst.close()
    }catch{
      case e:Exception=>e.printStackTrace()
    }
  }
  def getCount(connection: Connection, sql: String, params: Array[Any]): Long = {
    var result=0L
    var pst:PreparedStatement=null
    try {
      pst = connection.prepareStatement(sql)
      for (i <- params.indices) {
        pst.setObject(i + 1, params(i))
      }
      val set: ResultSet = pst.executeQuery()
      if(set.next()){
        result=set.getLong(1)
      }
      pst.close()
    }catch{
      case e:Exception=>e.printStackTrace()
    }
    result
  }

  def blackList(connection: Connection, sql: String): List[String] = {
    val pst: PreparedStatement = connection.prepareStatement(sql)
    val result: ResultSet = pst.executeQuery()
    var list= ListBuffer[String]()
    while (result.next()){
      list += result.getString(1)
    }
    list.toList
  }
}

package com.G3.spark.log

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 日志统计分析
  *
  * 1: 域名
  * 2: 流量
  * 分隔符\t
  *
  * 日志文件格式:
  * 域名  流量  资源  ip  访问时间
  *
  *
  * www.zhibo8.com	2000	https://www.baidu.com/video/music/djembe.pdf	111.255.45.0	[2019-02-06 09:41:24]
    www.baidu.com	-900	https://www.dongqiudi.com/pdf/tech/go.pdf	0.128.10.113	[2019-02-06 09:41:25]
    www.zhibo8.com	800	https://www.baidu.com/video/tech/scala.mp4	192.255.113.0	[2019-02-06 09:41:25]
    www.dongqiudi.com	5000	https://www.baidu.com/pdf/tech/go.pdf	125.45.0.45	[2019-02-06 09:41:25]
    www.youku.com	-1000	https://www.baidu.com/video/tech/scala.mp4	192.10.0.0	[2019-02-06 09:41:25]

  * @param fileName
  */
class logAnalyzer(fileName : String) {

  val sc = createLocalSparkContext
  //读取文件
  val lines = sc.textFile(fileName)

  //初始化SparkContext
  def createLocalSparkContext() = {
    val sparkConf = new SparkConf().setAppName(logAnalyzer.APP_NAME).setMaster("local[2]")
    new SparkContext(sparkConf)
  }
  //停止SparkContext
  def stopSparkContext() = {
    sc.stop()
  }

  //求每个域名的流量
  /**
    * 1)(domain,traffic)
    * 2)reduceByKey
    */
  def printTrafficByDomain() = {
    lines.map(x => {
      val temp = x.split("\t")
      var traffic = 0l
      try{
        traffic = temp(1).trim.toLong
      }catch {
        case e : Exception => traffic = 0l
      }
      (temp(0),traffic)
    }).reduceByKey(_+_).take(10).foreach(println)
  }

  //求省份的访问次数
  /**
    * 1.ip
    * 2.ip ==> province
    * 3.(province,1)
    * 4.reduceByKey(_+_)
    *
    */
  def printPVByProvince() = {
    lines.map(x => {
      val temp = x.split("\t")
      (Untils.getProvince(temp(logAnalyzer.IP_IDX).trim),1)
    }).reduceByKey(_+_).take(10).foreach(println)
  }

  //TODO..求每个域名下访问数最多的文件资源
  /**
    * ((domain,resources),1)
    *
    * groupBy
    * (www.dongqiudi.com,CompactBuffer(((www.dongqiudi.com,/video/tech/scala.mp4),155), ((www.dongqiudi.com,/video/music/djembe.pdf),301), ((www.dongqiudi.com,/pdf/tech/go.pdf),317)))
      (www.zhibo8.com,CompactBuffer(((www.zhibo8.com,/pdf/tech/go.pdf),294), ((www.zhibo8.com,/video/tech/scala.mp4),170), ((www.zhibo8.com,/video/music/djembe.pdf),314)))
    */
  def printMaxPVResourcesByDomain() = {
    lines.map(x => {
      val temp = x.split("\t")
      ((temp(0),Untils.getResources(temp(logAnalyzer.RESOURCE_IDX))),1)
    }).reduceByKey(_+_).groupBy(_._1._1).mapValues(_.toList.sortBy(_._2).reverse.take(1))
      .map(_._2).foreach(println)

  }

  //TODO..求访问次数最多的资源文件
  /**
    * (resources,1)
    */
  def printMaxResources() = {

    lines.map(x => {
      val temp = x.split("\t")
      (Untils.getResources(temp(2)),1)
    }).reduceByKey(_+_).sortBy(_._2,false).take(1).foreach(println)

  }

}
object logAnalyzer{

  val APP_NAME = "logAnalyzer"

  val DOMAIN_IDX = 0 //域名
  val TRAFFIC_IDX = 1 //流量
  val RESOURCE_IDX = 2 //资源
  val IP_IDX = 3 //ip
  val GET_TIME = 4 //访问时间

  def apply(fileName: String): logAnalyzer = new logAnalyzer(fileName)


}
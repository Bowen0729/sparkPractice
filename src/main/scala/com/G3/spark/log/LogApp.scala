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
  *
  *
  */
object LogApp {

  def main(args: Array[String]): Unit = {
    //filename
    val fileName = "C:\\Users\\Administrator\\Desktop\\文件\\log.txt"

    val log = logAnalyzer(fileName)
    //求每个域名的流量
//    log.printTrafficByDomain()
    //TODO..求省份的访问次数
//    log.printPVByProvince()
    //TODO..求每个域名下访问数最多的文件资源
//    log.printMaxPVResourcesByDomain()
    //TODO..求访问次数最多的资源文件
    log.printMaxResources()
    //TODO..关闭SparkContext
    log.stopSparkContext()
  }

}

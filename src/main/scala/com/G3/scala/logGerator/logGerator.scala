package com.G3.scala.logGerator

import java.io.FileWriter
import java.util.Date

import org.apache.commons.lang.time.FastDateFormat

import scala.collection.mutable.ListBuffer
import scala.util.Random

/**
  * 日志生成类
  */
object logGerator{

  val domain_list : ListBuffer[String] = ListBuffer("www.ruozedata.com","www.zhibo8.com","www.dongqiudi.com")
  val traffic_list : ListBuffer[_] = ListBuffer(2000,"C罗",5000,-1000,6000,"ruoze",1100,800,-900,"messi")

  /*
    随机生成domain
   */
  def sample_domain() ={
    domain_list(Random.nextInt(domain_list.length))
  }

  /**
    * 随机生成traffic
    */
  def sample_traffic() ={

    traffic_list(Random.nextInt(traffic_list.length))
  }
/**
  * 当前时间生成
 */
  def NowDate() : String = {
    val now : Date = new Date()
    val dataFormat : FastDateFormat = FastDateFormat.getInstance("[yyyy-MM-dd HH:mm:ss]")
    val date = dataFormat.format(now)
    date
  }

  /**
    * 将count行日志写入文件
    * @param count  日志行数
    */
  def inputToFile(count : Int): Unit = {
    val writer =  new FileWriter("C:\\Users\\Administrator\\Desktop\\文件\\log.txt" , true)
    var counter = count
    while(counter >= 1){
      var log_str = sample_domain() + "\t" + sample_traffic() + "\t" + NowDate()
      writer.write(log_str + "\n")
      counter = counter - 1
    }
    writer.close()
  }

  def main(args: Array[String]): Unit = {
    while (true){
      print("写入" + "\n")
      inputToFile(1000)
      Thread.sleep(90000)
    }
//    print(sample_domain())
  }
}
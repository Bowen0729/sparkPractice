package com.G3.scala.LogApp

import org.apache.spark.{SparkConf, SparkContext}

object LogApp {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("LogApp").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)

    val count = sc.textFile("file:///D:/log.txt").count()

    println(count)

    sc.stop()

  }

}

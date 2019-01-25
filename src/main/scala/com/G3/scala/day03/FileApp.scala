package com.G3.scala.day03

import scala.io.{Codec, Source}
import scala.xml.XML

object FileApp extends App {
//
//  val file = Source.fromFile("")(Codec.UTF8)
//
//  def readLine(): Unit ={
//    for (line <- file.getLines()){
//      println(line)
//    }
//  }
//

  //重点掌握
  def readAPI(): Unit ={
    val content = Source.fromURL("http://www.baidu.com")

    for(line <- content.getLines()){
      println(line)
    }

  }

  def readXML(): Unit ={
    XML.load("")

  }

  readAPI()

  def sum(a:Int,b:Int) = a+b
  //将原来接收2个参数的函数，将参数拆开
  //Spark UDF中常用
  def sum2(a:Int)(b:Int) = a+b



}

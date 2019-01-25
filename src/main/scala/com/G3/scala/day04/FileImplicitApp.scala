package com.G3.scala.day04

import java.io.File

import scala.io.Source

object FileImplicitApp {
  def main(args: Array[String]): Unit = {

    import ImplicitAspect.file2RichFile

    val file = new File(" ")
    file.read()

  }
}

//File 添加一个read方法，可以直接读取内容
class RichFile(val file:File){

  def read() = {
    Source.fromFile(file.getPath).mkString
  }
}

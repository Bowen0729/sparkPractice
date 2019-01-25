package com.G3.scala.day03

import java.io.FileNotFoundException

import scala.io.{Codec, Source}

object ExceptionApp extends App {

  try{
    val file = Source.fromFile("")(Codec.UTF8)

  }catch {
    //模式匹配
    case e:ArithmeticException => throw new Exception("aaa")
    case e:FileNotFoundException => println("not found")
    case e:Exception => println(e.getMessage)
    case _ => println(";")
  }finally {
    println("finally")
  }

}

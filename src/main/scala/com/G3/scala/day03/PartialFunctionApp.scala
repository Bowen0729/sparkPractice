package com.G3.scala.day03

object PartialFunctionApp extends App {

  def sayChineseName:PartialFunction[String,String] = {
    case "a" => "aa"
    case "b" => "bb"
    case "c" => "cc"
    case _ => "???"
  }

}

package com.G3.scala.day03

import scala.util.Random

object MatchApp extends App {

  var teachers = Array("a","b","c")

  val name = teachers(Random.nextInt(teachers.length))

  name match{
    case "a" => println("aa")
    case "b" => println("bb")
    case "c" => println("cc")
    case _ => println("???")
  }

  def greeting(array: Array[String]): Unit ={
    array match{
      case Array("a") => println("aa")
      case Array(x,y) => println(x + " " + y)
      case Array("a",_*) => println("???")
      case _ => println("= =")
    }
  }





}

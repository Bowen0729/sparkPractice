package com.G3.scala.day02

object CaseClassApp {

  def main(args: Array[String]): Unit = {
    println(Dog("name"))
  }

}
case class Dog(name:String)
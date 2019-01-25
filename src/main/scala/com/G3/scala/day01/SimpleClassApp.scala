package com.G3.scala.day01

/**
  * 类&属性
  */
object SimpleClassApp {

  def main(args: Array[String]): Unit = {
    val people = new People
    people.name = "a"
//    println(people.name + ":" + people.age + people.gender)
  }

}

class People{

  var name:String = _
  val age:Int = 18
  private [this] val gender = "M"


  def eat()={
    name + "eat ..."
  }


}
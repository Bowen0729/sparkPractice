package com.G3.scala.day01

/**
  * 抽象类
  */
object AbractApp {

  def main(args: Array[String]): Unit = {

    val dog = new Dog
    println(dog.woof)
  }

}

abstract class Animal{
  var name:String
  var age:Int
  def woof
}

class Dog extends Animal {

  override var name: String = _
  override var age: Int = _

  override def woof: Unit = {
    print("wang")
  }

}

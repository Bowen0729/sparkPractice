package com.G3.scala.day04

import ImplicitAspect._

object ImplicitApp {

  def main(args: Array[String]): Unit = {
    //定义隐式转换函数: 普通2不普通

    val dog = new Dog("q")
    dog.woof()


  }

}


class Dog(val name:String){

}

class Gaint(val name:String){
  def woof(): Unit ={
    println(s"$name a")
  }
}
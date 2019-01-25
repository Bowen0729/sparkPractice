package com.G3.scala.day02

/**
  * object类不需要new 直接调用
  * 类似静态类
  */
object Timer {

  var count = 0
  def currentCount():Long ={
    count += 1
    count
  }

  def main(args: Array[String]): Unit = {
    println(Timer.currentCount())
    println(Timer.currentCount())
    println(Timer.currentCount())
  }
}

class Timer{

}

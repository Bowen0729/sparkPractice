package com.G3.scala.day02


class ApplyApp{

  def ApplyAppClass(): Unit ={
    print("b")
  }

  def apply() ={
    println("class apply")
    new ApplyApp
  }

}

object ApplyApp {

  println("ApplyApp object enter")

  //apply中new了class
  def apply() ={
    println("apply")
    new ApplyApp
  }

  var count = 0
  def incr = {
    count += 1
  }

  def static: Unit ={
    print("a")
  }
  println("ApplyApp object leave")

}

object ApplyAppTest{
  def main(args: Array[String]): Unit = {
//    ApplyApp.static
    for(i <- 1 to 10){
      ApplyApp.incr
    }
//    print(ApplyApp.count)

//    val a = new ApplyApp()
//    a.ApplyApp()

    val b = ApplyApp()
//    b.ApplyAppClass()

    println(b())

  }
}
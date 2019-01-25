package com.G3.scala.day02

object ArrayApp {

  def main(args: Array[String]): Unit = {

    //调用了object中的apply方法，apply方法中new
    val a = Array("a","b","c")

    println(a.mkString)

    import scala.collection.mutable.ArrayBuffer
    val c = ArrayBuffer[Int]()

    c+=1
    c+=2
    c+=1
    c ++= Array(1,2)
    c.insert(1,4)
    c.remove(3)
    c.remove(0,2)
    c.trimEnd(1)

    c.toArray

    for(ele <- c){
      println(ele)
    }




  }

}

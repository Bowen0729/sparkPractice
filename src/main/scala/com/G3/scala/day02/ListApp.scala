package com.G3.scala.day02

object ListApp {

  def main(args: Array[String]): Unit = {
    val l = List(1,2,3,4)
    Nil
    l.head
    l.tail
    1::Nil
    1::2::3::Nil

    import scala.collection.mutable.ListBuffer
    val a = ListBuffer[Int]()

    a+=2
    a+=(1,5,9,6)
    a++=List(1,5,8,6)

    a.toList
    a.head

  }

}

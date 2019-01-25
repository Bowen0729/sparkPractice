package com.G3.scala.day02

object MapApp extends App {

  val a = Map("a"->22, "d"->11)

  import scala.collection.mutable.HashMap

  val c = HashMap[String,Int]()

  c("a") = 22
  c("b") = 15

  c.get("a")
  c.getOrElse("s",11)

  c += ("dd"-> 55,"aa"-> 55)

  for((key,_) <- a){
    println(key + "" + a.getOrElse(key,-99))

  }

  for(ele <- c.keySet){
    println(ele + " " + c(ele))
  }

  for(ele <- a.values){
    println(ele)
  }


}

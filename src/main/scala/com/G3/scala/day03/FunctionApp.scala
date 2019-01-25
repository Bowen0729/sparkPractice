package com.G3.scala.day03

object FunctionApp {

  def main(args: Array[String]): Unit = {

    val l = List(1,2,3,4,5,6,7,8,9)

    //TODO..将集合元素乘二
    l.map((x:Int) => x*2)

    l.map(x => x*2)

    l.map(_*2)

    l.map(_*2).foreach(println)

    l.map(_*2).filter(_ > 10)

    l.take(2)

    l.reduce(_+_)

    l.reduceLeft(_+_)

    l.reduceRight(_+_)

    l.reduceRight((a,b) => {

      println(a + " " + b)
      a - b
    })

    l.foldRight(2)((a,b)=>{

      println(a + " " + b)
      a - b
    })


    val f = List(List(1,2),List(3,4),List(5,6))
    f.foreach(println)
    f.flatten
    f.flatMap(_.map(_*2))


  }
}

package com.G3.scala.day1

/**
  * scala中函数的用法
  */
object FunctionApp {
  def main(args: Array[String]): Unit = {
    def add(x:Int, y:Int): Int ={
      x*y
      x+y
    }

//    def sayHello: Unit ={
//      println("hello")
//    }
//    print(add(3,5))
//    sayHello
//
//    1.until(10)
//    1 until 10
//    Range(1,10,3)

//    for(i <- 0 until 10){
//      println(i)
//    }

    //默认参数
    def loadSparkConf(fileName:String = "spark-default.conf"): Unit ={

      println(fileName)
    }

//    loadSparkConf("spark-a.conf")
//    loadSparkConf()

    //命名参数
//    def teacher(spark:String, linux:String): Unit ={
//      println(spark)
//      println(linux)
//    }
//
//    teacher("a","b")
//    teacher(spark = "a",linux = "b")


    //变长参数
    def sum(nums:Int*) ={
      var res = 0
      for(num <- nums){
        res+=num
      }
      res
    }

//    println(sum(2,3,4))

//    println(sum(1 to 10 :_*))

    def printTeachers(teachers:String*): Unit ={

      for(teacher <- teachers){
        println(teacher)
      }

    }

    printTeachers(Array("a","b"):_*)

  }
}

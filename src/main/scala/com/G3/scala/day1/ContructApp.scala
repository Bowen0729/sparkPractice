package com.G3.scala.day1

/**
  * 构造函数
  */
object ContructApp {

  def main(args: Array[String]): Unit = {
//    val girl = new Girl("cindy",18)
    //    print(girl.name + girl.age + girl.city)

//    val girl = new Girl("cindy",18,"13000002641")
//    println(girl.name + girl.age + girl.city + girl.phone)

    val cindy = new Cindy("cindy",18,"ai")

  }


}


class Girl(val name:String, val age:Int){
  println("girl enter")

  def hate(): Unit ={
    print(name)
  }
  var phone:String = _
  //附属构造函数,第一行必须调用主构造器
  def this(name:String,age:Int,phone:String) = {
    this(name,age)
    this.phone = phone
  }

  val city = "shenzhen"
  print("girl leave")
}
//继承先调用父类构造方法，父类没有子类访问不到
class Cindy(name:String, age:Int, val major:String) extends Girl(name,age){
  println("Cindy enter")
  println("Cindy leave")
}


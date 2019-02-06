package com.G3.spark.log

object Untils {

  def getProvince(ip : String) = {
    "辽宁"
  }

  /**
    * https://www.baidu.com/video/music/djembe.pdf?x=343&&y=99
    * @param resource
    * @return
    */
  def getResources(resource:String) = {
    var path = resource.trim.replaceFirst("//","")
    val startIndex = path.indexOf("/")
    val endIndex = path.indexOf("?")
    if(startIndex == -1){
      path = ""
    } else{
      path = path.substring(startIndex,if(endIndex == -1) path.length else endIndex)
    }
    path
  }


  def main(args: Array[String]): Unit = {
    println(getResources("https://www.baidu.com/video/music/djembe.pdf?x=343&&y=99"))
    println(getResources("https://www.baidu.com"))
    println(getResources("https://www.baidu.com/video/music/djembe.pdf"))
  }
}

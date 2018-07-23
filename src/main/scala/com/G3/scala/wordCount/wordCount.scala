package com.G3.scala.wordCount

object wordCount {

  def wc(wordList : List[String]) = {

    wordList.map(x => (x,1)).groupBy(_._1).mapValues(_.size)

  }


  def main(args: Array[String]): Unit = {

    val word  = List("a","b","b","a","c","a","c","c")
    print(wc(word))
  }
}

package com.G3.scala.day04

import java.io.File

object ImplicitAspect {

  implicit def dog2Gaint(dog: Dog):Gaint = new Gaint(dog.name)
  implicit def file2RichFile(file:File) : RichFile = new RichFile(file)



}

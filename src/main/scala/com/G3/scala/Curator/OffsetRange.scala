package com.G3.scala.Curator

case class OffsetRange(topic: String,
                       partition: Int,
                       fromOffset: Long,
                       utilOffset: Long)
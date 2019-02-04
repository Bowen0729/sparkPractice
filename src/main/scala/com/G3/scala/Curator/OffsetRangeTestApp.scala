package com.G3.scala.Curator

import scala.collection.mutable.ArrayBuffer

/**
  *   /consumers/G330/offsets/zbw_offset_topic/partition/0
  *   /consumers/G330/offsets/zbw_offset_topic/partition/1
  *   /consumers/G330/offsets/zbw_offset_topic/partition/2
  */
object OffsetRangeTestApp extends App{

  val groupName = "G330"
  val topicName = "zbw_offset_topic"
  val partitionNum = 10

  val offsetRangeManager = OffsetRangeManager

  //构建3个分区数据并存储到zk
  val parArrBuffer = ArrayBuffer[OffsetRange]()

  for(e <- 0 until partitionNum) {
    parArrBuffer += OffsetRange(topicName,e,0,100)
  }
  offsetRangeManager.apply().storeOffsets(parArrBuffer.toArray,groupName)

  //获取刚才创建的分区数据
  val offsetRangeMap = offsetRangeManager.apply().obtainOffsets(topicName,groupName)
  offsetRangeMap.foreach( m => {
    println(s"create node -> topic:${m._1.topic} partition:${m._1.partition} data:${m._2}")
  })

  //更新3个分区的新版本数据到ZK
  var parArrBufferForUpdate = ArrayBuffer[OffsetRange]()

  for(e <- 0 until partitionNum){
    parArrBufferForUpdate += OffsetRange(topicName,e,0,999)
  }
  offsetRangeManager.apply.updateOffsets(parArrBufferForUpdate.toArray,groupName)

  //获取刚才更新的分区数据
  val offsetRangeMapUpdated = offsetRangeManager.apply().obtainOffsets(topicName,groupName)
  offsetRangeMapUpdated.foreach(e => {
    println(s"update node -> topic:${e._1.topic} partition:${e._1.partition} data:${e._2}")
  })

}

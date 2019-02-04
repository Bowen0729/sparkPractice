package com.G3.scala.Curator

import com.typesafe.config.ConfigFactory

/**
  * zk节点格式
  *   /consumers/G330/offsets/zbw_offset_topic/partition/0
  *   /consumers/G330/offsets/zbw_offset_topic/partition/1
  *   /consumers/G330/offsets/zbw_offset_topic/partition/2
 *
 */

class OffsetRangeManager {

    val config = ConfigFactory.load()
    val offsetConfig = config.getConfig("zookeeper.offset")

    val rootName = offsetConfig.getString("rootName")
    val subName = offsetConfig.getString("subName")
    val partitionName = offsetConfig.getString("partitionName")

    val zkNodeManager = ZKNodeManager()
    /**
      * 构建zk存储路径
      *
      * @param rootName
      * @param groupName
      * @param subName
      * @param topic
      * @param partitionName
      * @param partition
      * @return
      */
    def getpath(
               rootName:String,
               groupName:String,
               subName:String,
               topic:String,
               partitionName:String,
               partition:Int = -1) = {
        if(partition < 0)
            s"/${rootName}/${groupName}/${subName}/${topic}/${partitionName}"
        else
            s"/${rootName}/${groupName}/${subName}/${topic}/${partitionName}/${partition}"
    }

    /**
      * 保存offsetRange对象数组（新增节点并存储数据）
      *
      * @param offsetRanges
      * @param groupName
      */
    def storeOffsets(
             offsetRanges:Array[OffsetRange],
             groupName:String) = {
        offsetRanges.foreach(e => {
            val path = getpath(rootName,groupName,subName,e.topic,partitionName,e.partition)
            //创建新节点并储存
            zkNodeManager.createNode(path,e.utilOffset.toString)
        })

    }

    /**
      * 保存offsetRange对象数组（更新节点数据）
      *
      * @param offsetRanges
      * @param groupName
      */
    def updateOffsets(
              offsetRanges:Array[OffsetRange],
              groupName:String) = {

        offsetRanges.foreach(e => {
            val path = getpath(rootName, groupName, subName, e.topic, partitionName, e.partition)
            //更新节点数据
            zkNodeManager.updateNode(path, e.utilOffset.toString)
        })
    }
        /**
          *获取指定topic所有分区数据
          *
          * @param topic
          * @param groupName
          * @return
          */
        def obtainOffsets(
                  topic:String,
                  groupName:String): Map[TopicAndPartition,Long] = {

            val path = s"/${rootName}/${groupName}/${subName}/${topic}/${partitionName}"
            val partitions = zkNodeManager.listNodes(path)
            var map = Map[TopicAndPartition,Long]()
            var subPath = ""

            import scala.collection.JavaConversions._
            partitions.foreach(p => {
                subPath = s"${path}/${p}"
                map += (TopicAndPartition(topic,p.toInt) -> zkNodeManager.getNodeDataAndStat(subPath)._1.toLong)
            })
            map
        }

}

object OffsetRangeManager{
    def apply(): OffsetRangeManager = new OffsetRangeManager()
}

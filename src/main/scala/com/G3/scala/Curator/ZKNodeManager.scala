package com.G3.scala.Curator

import java.util

import com.typesafe.config.ConfigFactory
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.data.Stat

class ZKNodeManager {

  val client = {
    //获取配置信息
    val config = ConfigFactory.load()
    val clientConfig = config.getConfig("zookeeper.client")

    //重试策略：初始时间为1s，重试3次
    val retryPolicy = new ExponentialBackoffRetry(
      clientConfig.getInt("baseSleepTimeMs"),
      clientConfig.getInt("maxRetries")
    )

    //通过工厂创建连接
    val cf = CuratorFrameworkFactory.builder()
            .connectString(clientConfig.getString("connect"))
        .sessionTimeoutMs(clientConfig.getInt("sessionTimeoutMs"))
          .connectionTimeoutMs(clientConfig.getInt("connectionTimeoutMs"))
        .retryPolicy(retryPolicy)
      .build()

    cf.start()

    cf
  }

  /**
    * 若创建节点的父节点不存在会先创建父节点再创建子节点
    *
    * @param path
    * @param data
    * @param createMode
    * @return
    */
  def createNode(path:String,
                 data:String,
                 createMode:CreateMode = CreateMode.PERSISTENT ) = {
    client.create().creatingParentsIfNeeded()
      .withMode(createMode)
        .forPath(path,data.getBytes())
  }

  /**
    * 获取子节点
    *
    * @param path
    * @return
    */
  def listNodes(path:String) : util.List[String] = {
    client.getChildren.watched().forPath(path)
  }

  /**
    * 返回节点数据和状态
    * @param path
    * @return
    */
  def getNodeDataAndStat(path:String) ={
    val stat = new Stat()
    val data = new String(client.getData.storingStatIn(stat).forPath(path))

    (data,stat)
  }

  /**
    * 删除节点
    * 若未删除成功
    *
    * @param path
    * @return
    */
  def deleteNode(path:String) = {

    val stat = new Stat()

    //在获取节点内容的同时把状态信息存入Stat对象
    val data = new String(client.getData().storingStatIn(stat).forPath(path))

    //client.delete().guranteed()保障机制（若未删除成功，只要会话有效就会在后台一直尝试）
    client.delete().guaranteed()
      .deletingChildrenIfNeeded()
      .withVersion(stat.getVersion())
      .forPath(path)
  }

  /**
    *更新节点数据
    *
    * @param path
    * @param data
    * @return
    */
  def updateNode(path:String,data:String) = {

    val stat = new Stat()
    client.getData().storingStatIn(stat).forPath(path)

    client.setData().withVersion(stat.getVersion()).forPath(path,data.getBytes())

  }

}
object ZKNodeManager {
  def apply(): ZKNodeManager = new ZKNodeManager()
}

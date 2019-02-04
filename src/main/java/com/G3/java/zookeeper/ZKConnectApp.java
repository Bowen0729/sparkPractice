package com.G3.java.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class ZKConnectApp implements Watcher{

    private static Logger logger = LoggerFactory.getLogger(ZKConnectApp.class);

    private static CountDownLatch connected = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{

        ZooKeeper zk = new ZooKeeper("192.168.254.128:2181", 5000, new ZKConnectApp());
        logger.warn("connecting");
        logger.warn("status:{}", zk.getState());

//        Thread.sleep(2000);
        connected.await();
        logger.warn("status:{}", zk.getState());
//        String path = zk.create("/zbw","pq".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        logger.warn("create node{}", path);

//        zk.setData("/zbw","pq-new".getBytes(),0);
//
//        Stat stat = new Stat();
//        byte[] data = zk.getData("/zbw",true,stat);
//        logger.warn("Data {}",new String(data));

        zk.delete("/zbw",-1);

    }

    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            logger.warn("watched:{}", watchedEvent);
            connected.countDown();
        }
    }
}

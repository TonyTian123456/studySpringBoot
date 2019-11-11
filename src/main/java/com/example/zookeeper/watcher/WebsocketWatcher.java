package com.example.zookeeper.watcher;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by cicada on 2019/9/25.
 */
public class WebsocketWatcher implements Watcher {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketWatcher.class);

    private ZooKeeper zooKeeper;

    public WebsocketWatcher(String zookeeperAddr) throws Exception {
        super();
        this.zooKeeper = new ZooKeeper(zookeeperAddr, 500, null);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.EventType.NodeDataChanged ==watchedEvent.getType()){
            System.out.println("节点变化了");
            String path = watchedEvent.getPath();
            System.out.println(path);
            try {
                String test=zooKeeper.getData(path,true,new Stat()).toString();
                System.out.println(test.toString());
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

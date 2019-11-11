package com.example.zookeeper;


import com.example.entity.data.User;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by cicada on 2019/9/24.
 */
public class zkTest {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181")
                .sessionTimeoutMs(1000)    // 连接超时时间
                .connectionTimeoutMs(1000) // 会话超时时间
                // 刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
        User  user = new User();
        user.setId(123l);
        user.setUserName("我叫网络测试");
        user.setIdentityCard("4114211989072272283");
        /*try {
            if (null != client.checkExists().forPath("/websocket")){
                client.setData().forPath("/websocket","哈哈哈".getBytes());
            }else {
                client.create().forPath("/websocket",user.toString().getBytes());
            }
            byte[] bytes = client.getData().forPath("/websocket");
            String s = bytes.toString();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
        ArrayList<ACL> list = new ArrayList<ACL>();
        int perm =ZooDefs.Perms.ADMIN | ZooDefs.Perms.READ;
        ACL acl1 =new ACL(perm,new Id("world","anyone"));
        ACL acl2 =new ACL(perm,new Id("id","127.0.0.1"));
//        ACL acl3 =new ACL(perm,new Id("id","anyone"));
        list.add(acl1);
        list.add(acl2);
        zooKeeper.create("/tuling","我爱你哟".getBytes(),list, CreateMode.EPHEMERAL_SEQUENTIAL);
    }
}

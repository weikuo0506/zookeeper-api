package com.walker.zookeeper.api.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author walkerwei
 * @version 2016/8/2
 */
public class Test {
    public static void main(String[] args) {

        try {

//            CuratorFramework client = ZookeeperUtil.newClient("192.168.23.242:2181");
            CuratorFramework client = ZookeeperUtil.newClient("localhost:2181");

            client.start();

            System.out.println(client.isStarted());
            //检查目录
            Stat stat = client.checkExists().forPath("/test/walker");

            //创建目录
            if (stat == null) {
                client.create().forPath("/test/walker", new String("I am walker!").getBytes("utf8"));
                stat = client.checkExists().forPath("/test/walker");
            }

            System.out.println("data length: "+stat.getDataLength());
            System.out.println("create time: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(stat.getCtime())));

            //获取数据
            byte[] data = client.getData().forPath("/test/walker");
            System.out.println(new String(data, Charset.forName("utf8")));

            //创建子节点
            stat = client.checkExists().forPath("/test/walker/providers");
            if (stat == null) {
                client.create().forPath("/test/walker/providers",new String("I am providers!").getBytes("utf8"));
            }
            stat = client.checkExists().forPath("/test/walker/consumers");
            if (stat == null) {
                client.create().forPath("/test/walker/consumers",new String("I am consumers!").getBytes("utf8"));
            }

            //获取子节点
            List<String> children = client.getChildren().forPath("/test/walker");
            for (String child : children) {
                System.out.println(child);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

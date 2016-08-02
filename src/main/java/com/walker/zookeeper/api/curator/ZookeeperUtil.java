package com.walker.zookeeper.api.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author walkerwei
 * @version 2016/8/2
 */
public class ZookeeperUtil {
    private static CuratorFramework client;
    public static CuratorFramework newClient(String zkConnStr) {
        if (client == null) {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            client = CuratorFrameworkFactory.newClient(zkConnStr, retryPolicy);
        }
        return client;
    }
}

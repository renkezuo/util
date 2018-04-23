package com.renke.y2018;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperUtil {
	public static void main(String[] args) throws Exception{
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("192.168.56.101:2181", 
		       1000, new Watcher() { 
		           // 监控所有被触发的事件
		           public void process(WatchedEvent event) { 
		               System.out.println("Node[" + event.getPath() +"]：已经触发了" + event.getType() + "事件！"); 
		           } 
		       });

		// 创建一个目录节点
		zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,
		  CreateMode.PERSISTENT); 
		// 创建一个子目录节点
		zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
		  Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		zk.create("/testRootPath/testChildPathThree", "testChildDataThree".getBytes(),
				  Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		System.out.println("testRootPath data : "+new String(zk.getData("/testRootPath",false,null))); 
		// 取出子目录节点列表
		System.out.println("得到子节点："+zk.getChildren("/testRootPath", false));
		Stat s = zk.exists("/testRootPath/testChildPathCCC", true);
		System.out.println(s);
		// 修改子目录节点数据
		zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(),-1); 
		System.out.println("目录节点状态：["+zk.exists("/testRootPath", false)+"]"); 
		// 创建另外一个子目录节点
		zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), 
		  Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", false,null))); 
		// 删除子目录节点
		zk.delete("/testRootPath/testChildPathTwo",-1); 
		zk.delete("/testRootPath/testChildPathThree",-1); 
		zk.delete("/testRootPath/testChildPathOne",-1); 
		// 删除父目录节点
		zk.delete("/testRootPath",-1); 
		// 关闭连接
		zk.close();
	}
}

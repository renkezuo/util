package com.renke.util.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;



/**
 * 池[初始化线程，线程最大数量，保持时间， 队列]
 * 创建线程，将线程加入队列，初始创建数量参数，线程不够时，创建线程，线程达到上限时，等待线程
 * 创建线程交给工厂来做[这样，当使用其他自定义线程时，很好操作了]
 * 队列抽象
 * 
 * 可访问队列
 * 可以看看ReetriantLock这个类
 * 获取锁，获取到了，就运行，获取不到，就休眠，等待锁释放被唤醒
 * 
 * 100个锁
 * 每个锁，单独的状态。不可重入
 * 
 * 
 * 每次try 看看size是否>0，减一，获取成功
 * 
 * queue保存任务列表所有的任务都到这里来
 * unlock的时候，直接唤醒第一个CLH
 * 
 * 
 * 
 * 而池的概念，就是默认可以持有最小数量锁，锁用完了，去申请，最多持有锁的个数为最大数
 * 每条线程过来访问我时，我看看hasLock，如果true，就交给他，false就让wait
 * 等待其他人unlock，我再唤醒他，同时把锁给他
 * 排队等待的人，单独做一个锁。一个一个去唤醒[公平锁]
 * @author renke.zuo@foxmail.com
 * @version V1.0
 * @time 2016-07-11 09:21:58
 */
public class ThreadPool {
	private int minimum;			//最小线程数
//	private int maximum;			//最大线程数
//	private int interval;			//每次创建线程数
//	private Queue queue;			//线程队列
	
	public ThreadPool(){
		minimum = 16;
//		maximum = 32;
//		interval = 16;
//		queue = new DefaultQueue<>(minimum);
	}
	
	
	
	
	static class DefaultThreadFactory implements ThreadFactory{
		//一个是池的编号
		private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        //池中线程的编号
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager security = System.getSecurityManager();
            group = (security != null) ? security.getThreadGroup() :
                                  Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                          poolNumber.getAndIncrement() +
                         "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                                  namePrefix + threadNumber.getAndIncrement(),
                                  0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
		
	}
	public int getMinimum(){
		return minimum;
	}
	
	public static void main(String[] args) {
		String str = "123";
		String str2 = "123";
		String str3 = new String("123");
		String str4 = new String("123");
		System.out.println("str==str2:"+(str==str2));
		System.out.println("str==str3:"+(str==str3));
		System.out.println("str==str4:"+(str==str4));
		System.out.println("str2==str3:"+(str2==str3));
		System.out.println("st3==str4:"+(str3==str4));
		ThreadPool tp  = new ThreadPool();
		System.out.println("tp.getMinimum()"+tp.getMinimum());
//		ThreadPoolExecutor tpe = new ThreadPoolExecutor(0, 0, 0, null, null);
//		ExecutorService e = Executors.newCachedThreadPool();
//		try{
//			Thread.currentThread().interrupt();
//		}finally{
//			System.out.println("I'm finally");
//		}
		
		
	}
}

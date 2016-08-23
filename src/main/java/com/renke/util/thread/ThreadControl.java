package com.renke.util.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadControl {
	
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
	
	static class MyTestRun implements Runnable{

		String name;
		public MyTestRun(String name){
			this.name = name;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("MyTestRun:"+name);
			try {
				Thread.sleep(2000);
				System.out.println("MyTestRunSleepDown:"+name);
				synchronized (this) {
					this.wait();
				}
				System.out.println("MyTestRunWaitDown:"+name);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		ThreadFactory threadFactory = new ThreadControl.DefaultThreadFactory();
		MyTestRun[] mtrs = new MyTestRun[20];
		Thread[] ts = new Thread[20];
		
		for ( int i=0 ; i < 20;i++){
			mtrs[i] = new MyTestRun("thread-"+i);
			ts[i] = threadFactory.newThread(mtrs[i]);
			ts[i].start();
		}
		Thread.sleep(6000);
		
		for(int i=0;i<20;i++){
			synchronized (mtrs[i]) {
				mtrs[i].notify();
			}
			Thread.sleep(1000);
		}
//		Unsafe unsafe = Unsafe.getUnsafe();
//		unsafe.park(false, 0);
//		unsafe.unpark(arg0);
	}
	
	
	
}

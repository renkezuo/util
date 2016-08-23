package com.renke.util.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;



/**
 * ��[��ʼ���̣߳��߳��������������ʱ�䣬 ����]
 * �����̣߳����̼߳�����У���ʼ���������������̲߳���ʱ�������̣߳��̴߳ﵽ����ʱ���ȴ��߳�
 * �����߳̽�����������[��������ʹ�������Զ����߳�ʱ���ܺò�����]
 * ���г���
 * 
 * �ɷ��ʶ���
 * ���Կ���ReetriantLock�����
 * ��ȡ������ȡ���ˣ������У���ȡ�����������ߣ��ȴ����ͷű�����
 * 
 * 100����
 * ÿ������������״̬����������
 * 
 * 
 * ÿ��try ����size�Ƿ�>0����һ����ȡ�ɹ�
 * 
 * queue���������б����е����񶼵�������
 * unlock��ʱ��ֱ�ӻ��ѵ�һ��CLH
 * 
 * 
 * 
 * ���صĸ������Ĭ�Ͽ��Գ�����С���������������ˣ�ȥ���룬���������ĸ���Ϊ�����
 * ÿ���̹߳���������ʱ���ҿ���hasLock�����true���ͽ�������false����wait
 * �ȴ�������unlock�����ٻ�������ͬʱ��������
 * �Ŷӵȴ����ˣ�������һ������һ��һ��ȥ����[��ƽ��]
 * @author renke.zuo@foxmail.com
 * @version V1.0
 * @time 2016-07-11 09:21:58
 */
public class ThreadPool {
	private int minimum;			//��С�߳���
//	private int maximum;			//����߳���
//	private int interval;			//ÿ�δ����߳���
//	private Queue queue;			//�̶߳���
	
	public ThreadPool(){
		minimum = 16;
//		maximum = 32;
//		interval = 16;
//		queue = new DefaultQueue<>(minimum);
	}
	
	
	
	
	static class DefaultThreadFactory implements ThreadFactory{
		//һ���ǳصı��
		private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        //�����̵߳ı��
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

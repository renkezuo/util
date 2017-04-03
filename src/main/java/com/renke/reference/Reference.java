package com.renke.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * ����ǿ���ã������ã������ã�������
 * ���Խ����
 * 1����4������ͬʱGCʱ��phantom��GC֮��weak��GC��hard�������κ�ʱ��gc��soft���GC��Ҳ���ܲ���GC
 * 2��
 * @author renke.zuo@foxmail.com
 * @time 2017-03-19 16:07:46
 */
public class Reference {
	public static void main(String[] args) {
		Million h = new Million("Hardx");
		Million s = new Million("Softx",0);
		Million w = new Million("Weakx",0);
		Million p = new Million("Phantomx",0);
		byte[] buf = new byte[1024 * 1024 * 5];
		
		ReferenceQueue<Million> pQueue = new ReferenceQueue<Million>();
		ReferenceQueue<Million> wQueue = new ReferenceQueue<Million>();
		ReferenceQueue<Million> sQueue = new ReferenceQueue<Million>();
		SoftReference<Million> softRef = new SoftReference<Million>(s,sQueue);
		WeakReference<Million> weakRef = new WeakReference<Million>(w,wQueue);
		PhantomReference<Million> PhantomRef = new PhantomReference<Million>(p,pQueue);
		w = null;
		s = null;
		//��������ö���һ��ʹ��
		p = null;
		h = null;
		
		System.out.println(" gc  ready ...");
		System.out.println("my_gc_before");
//		System.gc();
		//���̶�ʹ���ڴ棬ʹ�䴥��GC
		maxUseMemory();
		
		circlePrint(wQueue);
		circlePrint(sQueue);
		circlePrint(pQueue);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println("my_gc_end");
//		System.gc();
		
	}
	
	public static void printQueue(ReferenceQueue<Million> queue){
		java.lang.ref.Reference<? extends Million> ref  = null;
		while((ref= queue.poll())!=null){
			System.out.println(ref + "--> " + ref.get());
		}
	}
	/**
	 * ����ϵĴ������󣬴�ʹ�ڴ治��
	 * @author renke.zuo@foxmail.com
	 * @time 2017-03-19 16:49:04
	 */
	public static void maxUseMemory(){
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			es.execute(new MaxUseMemory());
		}
	}
	
	/**
	 * while true��ʽ��ӡ���ö���
	 * @author renke.zuo@foxmail.com
	 * @time 2017-03-19 16:50:48
	 * @param queue
	 */
	public static void circlePrint(ReferenceQueue<Million> queue){
		new Thread(new Runnable() {
			@Override
			public void run() {
				long begin = System.currentTimeMillis();
				while(true){
					printQueue(queue);
					if(System.currentTimeMillis() - begin >= 12000){
						break;
					}
				}
			}
		}).start();
	}
}
class MaxUseMemory implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		for(int i= 0; i< 5;i++){
			Million hh = new Million("hard"+i,3);
			hh = null;
			try {
				//����GC������new���ٶ�
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

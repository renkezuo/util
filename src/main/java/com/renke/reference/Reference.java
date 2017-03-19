package com.renke.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

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
		Million h = new Million("Hard");
		Million s = new Million("Soft1",3);
		Million w = new Million("Weak2",0);
		Million p = new Million("Phantom3",0);
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
		printQueue("weak",wQueue);
		printQueue("soft",sQueue);
		printQueue("Phantom",pQueue);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i= 0; i< 10;i++){
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
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				long begin = System.currentTimeMillis();
				while(true){
					printQueue("weak",wQueue);
					printQueue("soft",sQueue);
					printQueue("Phantom",pQueue);
					if(System.currentTimeMillis() - begin >= 12000){
						break;
					}
				}
			}
		}).start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println("my_gc_end");
//		System.gc();
		printQueue("weak",wQueue);
		printQueue("soft",sQueue);
		printQueue("Phantom",pQueue);
		
	}
	
	public static void printQueue(String name,ReferenceQueue<Million> queue){
		java.lang.ref.Reference<? extends Million> ref  = null;
//		System.out.println("print queue begin--->"+name);
		while((ref= queue.poll())!=null){
			System.out.println(name + ":"+ ref);
		}
	}
	
}

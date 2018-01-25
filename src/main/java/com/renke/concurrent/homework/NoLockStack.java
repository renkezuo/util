package com.renke.concurrent.homework;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class NoLockStack<E> implements Iterator<E>{
	
	public AtomicReference<Node> linked = new AtomicReference<NoLockStack<E>.Node>();
	
	public AtomicInteger count = new AtomicInteger(0);
	
	public AtomicReference<Node> position;
	
	class Node {
		public Node(E e){
			this.e = e;
		}
		public AtomicReference<Node> prev = new AtomicReference<NoLockStack<E>.Node>();
		public E e;
	}
	
	public void push(E e){
		Node node = new Node(e);
		if(!linked.compareAndSet(null, node)){
			Node tail = linked.get();
			node.prev.set(tail);
			while(!linked.compareAndSet(tail, node)){
				tail = linked.get();
				node.prev.set(tail);
			}
		}
		count.incrementAndGet();
	}
	
	public E pop(){
		Node node = linked.get();
		Node prev = node.prev.get();
		while(!linked.compareAndSet(node, prev)){
			node = linked.get();
			prev = node.prev.get();
		}
		count.decrementAndGet();
		return node.e;
	}
	
	/***
	 * 以下方法线程不安全
	 * @return
	 * @author Z.R.K
	 */
	@Override
	public boolean hasNext() {
		if(position == null){
			position = linked;
		}
		if(position.get() == null){
			position = null;
			return false;
		}else{
			return true;
		}
	}

	@Override
	public E next() {
		Node pos = position.get();
		position = pos.prev;
		return pos.e;
	}
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		NoLockStack<String> nls = new NoLockStack<>();
		// 100 个线程，每个跑100次
		for(int i=0 ; i< 100 ; i++){
			Thread t = new Thread(new IncrementThread(nls));
			t.start();
		}

		Thread.sleep(2000);
		int m=0;
		
		while(nls.hasNext()){
			nls.next();
//			System.out.println(nls.next());
			m++;
		}
		
		System.out.println("count1:"+m);
//		while(nls.hasNext()){
//			System.out.println(nls.next());
//		}

		Thread.sleep(2000);
		
		for(int i=0 ; i< 100 ; i++){
			Thread t = new Thread(new IncrementThread(nls));
			t.start();
		}
		
//		Thread.sleep(3000);
		// 50个线程，每个跑100次
		for(int i=0 ; i< 50 ; i++){
			Thread t = new Thread(new DecrementThread(nls));
			t.start();
		}
		
//		Thread.sleep(2000);
		
//		System.out.println(nls.hasNext());
//		
//		System.out.println(nls.position);
//		
//		System.out.println(nls.linked);
//		
		Thread.sleep(10000);
		while(nls.count.get() > 15000){
			Thread.sleep(1000);
			System.out.println(nls.count);
		}
		
		m=0;
		
		while(nls.hasNext()){
			nls.next();
//			System.out.println(nls.next());
			m++;
		}
		
		System.out.println("count2:"+m);
		
	}
}


class IncrementThread implements Runnable{
	
	public NoLockStack<String> nls;
	
	
	public IncrementThread (NoLockStack<String> nls){
		this.nls = nls;
	}
	
	@Override
	public void run() {
		for (int index = 1; index <= 100; index++) {
			nls.push(Thread.currentThread().getName() + "----" + index);
			System.out.println("push:"+Thread.currentThread().getName() + "----" + index);
		}
	}
}

class DecrementThread implements Runnable{
	
	public NoLockStack<String> nls;
	
	public DecrementThread(NoLockStack<String> nls){
		this.nls = nls;
	}
	
	@Override
	public void run() {
		for (int index = 1; index <= 100; index++) {
			String str = nls.pop();
			System.out.println("pop:"+str);
		}
	}
}

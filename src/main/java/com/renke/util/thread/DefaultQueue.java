package com.renke.util.thread;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 默认的队列 加入 提取 查看 遍历 返回大小
 * 
 * @author renke.zuo@foxmail.com
 * @version V1.0
 * @time 2016-07-12 10:37:00
 * @param <T>
 */
public class DefaultQueue<T> extends AbstractQueue<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int size = 0;
	private Lock lock;
	private Object[] queue;

	public DefaultQueue() {
		//公平锁
		lock = new ReentrantLock(false);
		queue = new Object[16];
	}

	public DefaultQueue(int initSize) {
		//公平锁 
		lock = new ReentrantLock(false);
		queue = new Object[initSize];
	}

	@Override
	public boolean offer(T e) {
		lock.lock();
		// 如果满了，就扩展队列，一次扩展16位
		if(size == queue.length){
			queue = Arrays.copyOf(queue, queue.length + 16);
		}
		// 找到队列中空的位置，插入进去
		queue[size++] = e; 
		lock.unlock();
		// 插入进去，则size+1
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T poll() {
		if(size < 0) {
			return null;
		}
		lock.lock();
		// 取出队列中的第一个
		T t = (T)queue[0];
		// 将队列整体前移一位
		System.arraycopy(queue, 1, queue, 0, queue.length-1);
		queue[size--] = null;
		// 此处需要lock
		lock.unlock();
		// TODO Auto-generated method stub
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		// 获取队列第一个
		return (T) queue[0];
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIteratory<T>();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return queue.length;
	}

	@SuppressWarnings("hiding")
	private class ArrayIteratory<T> implements Iterator<T> {
		int cursor = 0; // index of next element to return

		public boolean hasNext() {
			return cursor <= size;
		}

		@SuppressWarnings("unchecked")
		public T next() {
			int i = cursor++;
			if (i > size)
				throw new NoSuchElementException();
			Object[] array = queue;
			if (i >= array.length)
				throw new ConcurrentModificationException();
			return (T) array[i];
		}

		public void remove() {
			//如果是链表，直接找到那个节点，然后将他的上下级勾搭起来就可以了
			//如果是数组，需要将数组坐标记录，然后将前一段数据和后一段数据组装到一个新数组里面
			//cursor不变，size变小
			//删除的时候要上锁
		}
	}
	
	public static void main(String[] args) {
		long b = System.currentTimeMillis();
//		for(int i=0;i<100000;i++){
			String[] strs = new String[10];
			for(int t=0;t<strs.length;t++){
				strs[t] = "str:"+t;
			}
			System.arraycopy(strs, 1, strs, 0, strs.length-1);
			for(int i =0 ;i < strs.length ; i++){
				System.out.println(strs[i]);
			}
//		}
		System.out.println("arrayCopy:"+(System.currentTimeMillis()-b) + "ms");
		
		
//		b = System.currentTimeMillis();
//		for(int i=0;i<100000;i++){
//			String[] strs = new String[10240];
//			for(int t=0;t<strs.length;t++){
//				strs[t] = "str:"+t;
//			}
//			for(int t=0;t<strs.length-1;t++){
//				strs[t] = strs[t+1];
//			}
//		}
//		System.out.println("move position:"+(System.currentTimeMillis()-b) + "ms");
	}

}

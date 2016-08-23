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
 * Ĭ�ϵĶ��� ���� ��ȡ �鿴 ���� ���ش�С
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
		//��ƽ��
		lock = new ReentrantLock(false);
		queue = new Object[16];
	}

	public DefaultQueue(int initSize) {
		//��ƽ�� 
		lock = new ReentrantLock(false);
		queue = new Object[initSize];
	}

	@Override
	public boolean offer(T e) {
		lock.lock();
		// ������ˣ�����չ���У�һ����չ16λ
		if(size == queue.length){
			queue = Arrays.copyOf(queue, queue.length + 16);
		}
		// �ҵ������пյ�λ�ã������ȥ
		queue[size++] = e; 
		lock.unlock();
		// �����ȥ����size+1
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T poll() {
		if(size < 0) {
			return null;
		}
		lock.lock();
		// ȡ�������еĵ�һ��
		T t = (T)queue[0];
		// ����������ǰ��һλ
		System.arraycopy(queue, 1, queue, 0, queue.length-1);
		queue[size--] = null;
		// �˴���Ҫlock
		lock.unlock();
		// TODO Auto-generated method stub
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		// ��ȡ���е�һ��
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
			//���������ֱ���ҵ��Ǹ��ڵ㣬Ȼ���������¼����������Ϳ�����
			//��������飬��Ҫ�����������¼��Ȼ��ǰһ�����ݺͺ�һ��������װ��һ������������
			//cursor���䣬size��С
			//ɾ����ʱ��Ҫ����
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

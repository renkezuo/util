package com.renke.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Z.R.K
 * @description
 * @create 2018-07-10 16:56:51
 **/
public class RedisTest {

	public static void main(String[] args) throws InterruptedException {
		JedisPool jp = new JedisPool("192.168.20.68",6379);
		Jedis j = jp.getResource();
		j.set("ok", "true");

		System.out.println(j.get("ok"));

//		Jedis j = new Jedis("192.168.20.208",6379);
//		Long x = j.del("userList_2018");
//		System.out.println("del : " + x);
//		Jedis[] jedis = new Jedis[20];
//		for(int i=0;i<20;i++){
//			jedis[i] = new Jedis("192.168.20.68",6379);
//		}
//
//		for(int i = 0; i < 10 ; i++ ){
//			Thread t = new Thread(new AddList(jedis[i], i * 1000000));
//			t.start();
//		}
//
//		for(int i = 0; i < 10 ; i++ ){
//			Thread t = new Thread(new GetList(jedis[i+10]));
//			t.start();
//		}
//
//		Thread.sleep(10000000);

	}

	static class AddList implements Runnable {

		Jedis jedis = null;
		int x = 0;

		public AddList(Jedis jedis, int x){
			this.jedis = jedis;
			this.x = x;
		}

		@Override
		public void run() {
			while(true){
				x ++;
				Long begin = System.currentTimeMillis();
				jedis.hset("userList_2018","name" + x, "abcdefg");
				System.out.println(Thread.currentThread().getName() + "AddList : "+ (System.currentTimeMillis() - begin ) + "ms");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


	static class GetList implements Runnable {
		Jedis jedis = null;

		public GetList(Jedis jedis){
			this.jedis = jedis;
		}

		@Override
		public void run() {
			while(true){
				Long begin = System.currentTimeMillis();

				jedis.hgetAll("userList_2018");
//				for (int i = 1; i <= 100; i++) {
//					jedis.lrange("userList_2018", (i - 1) * 1000, i * 1000);
//				}
				System.out.println(Thread.currentThread().getName() + " getList : " +(System.currentTimeMillis()-begin) + "ms");
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

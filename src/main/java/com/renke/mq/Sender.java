package com.renke.mq;

public interface Sender {
	public void send(String msg, String... properties);
	public void close();
}
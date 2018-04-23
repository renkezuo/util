package com.renke.mq;

public interface Receiver {
	public void listener(String... properties);
	public void close();
}

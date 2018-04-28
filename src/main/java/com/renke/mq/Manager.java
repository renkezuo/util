package com.renke.mq;

import java.util.Map;

public interface Manager {
	public void create(Map<String, Object> properties);
	public void close();
	public void closeAll();
	
	public int msgCount();
	
}

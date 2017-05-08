package com.renke.jdk8;

@FunctionalInterface
public interface MyTestFunInterface<M,T> {
	public T print(M m);
}

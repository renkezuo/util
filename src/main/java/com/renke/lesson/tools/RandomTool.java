package com.renke.lesson.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTool{
	
	private List<Integer> indexPool;
	
	private int[] indexes;
	
	public RandomTool(int[] indexes){
		this.indexes = indexes;
		initialize();
	}
	
	public void initialize(){
		indexPool = new ArrayList<>();
		for(int i=0 ; i< indexes.length ; i++){
			if(indexes[i] == 0 ){
				indexes[i] = 1;
			}
			for(int m=0;m<indexes[i];m++){
				indexPool.add(i);
			}
		}
	}
	
	public int nextIndex(){
		if(indexPool.size() == 0){
			initialize();
		}
		Random random = new Random();
		int next = random.nextInt(indexPool.size());
		random = null;
		return indexPool.remove(next);
	}
	
//	public static void main(String[] args) {
//		int[] initializeArray = new int[11];
//		for(int i=0;i<initializeArray.length;i++){
//			if(i<3){
//				initializeArray[i] = 2;
//			}else{
//				initializeArray[i] = 1;
//			}
//		}
//		RandomTool random = new RandomTool(initializeArray);
//		for(int i = 0 ; i < 14 ; i ++){
//			System.out.println(random.nextIndex());
//		}
//	}
//	
	
}

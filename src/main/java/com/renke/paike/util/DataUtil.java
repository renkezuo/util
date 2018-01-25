package com.renke.paike.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.renke.paike.model.Clear;

/**
 * 数据工具
 * @author Z.R.K
 * @time 2017-05-11 14:25:48
 */
public class DataUtil {
	
	public static final int CharIntX = '0';
	public static final int INVALID = -1;
	
	public static boolean existsArrayInt(int[] array, int i){
		for(int x : array){
			if(x == i){
				return true;
			}
		}
		return false;
	}

	public static <T> boolean existsArray(T[] array, T t){
		for(T item : array){
			if(t.equals(item)){
				return true;
			}
		}
		return false;
	}
	
	public static <T> void clearArray(T[][] array){
		if (array == null){
			return;
		}
		for (int x = 0; x < array.length; x++) {
			array[x] = null;
		}
	}
	
	public static void clearArrayInt(int[][] array){
		if (array == null){
			return;
		}
		for (int x = 0; x < array.length; x++) {
			array[x] = null;
		}
	}
	
	public static void clearArrayBl(boolean[][] array){
		if (array == null){
			return;
		}
		for (int x = 0; x < array.length; x++) {
			array[x] = null;
		}
	}
	
	public static void clearArrayData(Clear[] array){
		if(array == null){
			return ;
		}
		for(Clear c : array){
			if(c != null){
				c.clear();
				c = null;
			}
		}
	}
	
	/**
	 * arrayToList
	 * @param t
	 * @return
	 * @author Z.R.K
	 */
	public static <T> List<T> arrayToList(T[] t){
		List<T> list = new ArrayList<>(t.length);
		for(T item : t){
			list.add(item);
		}
		return list;
	}
	
	public static int getScoreByArray(int[] array){
		int score = 0;
		for(int i1=0;i1<array.length;i1++){
			for(int i2=0;i2<array.length;i2++){
				score += Math.abs(array[i1] - array[i2]);
			}
		}
		return score;
	}
	
	/**
	 * array重新排序
	 * @param array
	 * @param array2
	 * @param offset
	 * @author Z.R.K
	 */
	public static int[] getArrayByOffset(int[] array, int offset){
		int length = array.length;
		int[] result = new int[length];
		for (int i = 0; i < length; i++) {
			result[i] = array[(i+offset) % length];
		}
		return result;
	}
	
	/**
	 * 向List<List<E>>数据结构的list中新增元素
	 * @param map
	 * @param k
	 * @param e
	 */
	public static <E> void addListEle(List<List<E>> list, int index, E e) {
		List<E> itemList = list.get(index);
		if (itemList == null) {
			itemList = new ArrayList<E>();
			list.set(index, itemList);
		}
		itemList.add(e);
	}
	
	/**
	 * 向map<K,List<E>>数据结构的list中新增元素
	 * @param map
	 * @param k
	 * @param e
	 */
	public static <K, E> void putListEle(Map<K, List<E>> map, K k, E e) {
		List<E> list = map.get(k);
		if (list == null) {
			list = new ArrayList<E>();
			map.put(k, list);
		}
		list.add(e);
	}
	
	public static <K, E> void clearMapList(Map<K, List<E>> map){
		if(map == null){
			return ;
		}
		Iterator<Entry<K, List<E>>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<K,List<E>> entry = it.next();
			List<E> list = entry.getValue();
			if (list != null) {
				list.clear();
				list = null;
			}
		}
		map.clear();
	}
	
	/**
	 * 向map<K,List<E>>数据结构的list中新增list
	 * @param map
	 * @param k
	 * @param e
	 */
	public static <K, E> void putListAll(Map<K, List<E>> map, K k, List<E> l) {
		List<E> list = map.get(k);
		if (list == null) {
			list = new ArrayList<E>();
			map.put(k, list);
		}
		if(l != null){
			list.addAll(l);
		}
	}
	

	public static <T> void clearListData(List<List<T>> list){
		if(list == null){
			return ;
		}
		for(List<T> ts : list){
			if(ts != null){
				ts.clear();
				ts = null;
			}
		}
	}
	
	/**
	 * 向map<K,List<E>>数据结构的list中新增元素
	 * @param map
	 * @param k
	 * @param e
	 */
	public static <K,E> void putSetEle(Map<K, Set<E>> map,K k,E e) {
		Set<E> set = map.get(k);
		if(set == null){
			set = new HashSet<E>();
			map.put(k,set);
		}
		set.add(e);
	}
	
	/**
	 * 向map<K,List<E>>数据结构的list中新增元素
	 * @param map
	 * @param k
	 * @param e
	 */
	public static <K> void putArrayEle(Map<K, JsonArray> map,K k,JsonElement e) {
		JsonArray array = map.get(k);
		if(array == null){
			array = new JsonArray();
			map.put(k,array);
		}
		array.add(e);
	}
	
	/**
	 * map<K,Integer>自增
	 * @param map
	 * @param k
	 * @param e
	 * @param offset
	 */
	public static <K> void incrementMap(Map<K,Integer> map,K k,int offset) {
		Integer value = map.get(k);
		if(value == null){
			value = 0;
		}
		map.put(k,value+offset);
	}

	/**
	 * map<K,Integer>自减
	 * @param map
	 * @param k
	 * @param e
	 * @param offset
	 */
	public static <K> void decrementMap(Map<K,Integer> map,K k,int offset) {
		Integer value = map.get(k);
		if(value == null){
			value = 1;
		}
		map.put(k,value-offset);
	}
	
	/**
	 * map<K,Integer>自增
	 * @param map
	 * @param k
	 * @param e
	 */
	public static <K> void incrementMap(Map<K,Integer> map,K k) {
		incrementMap(map, k, 1);
	}
	
	/**
	 * map<K,Integer>自减
	 * @param map
	 * @param k
	 * @param e
	 */
	public static <K> void decrementMap(Map<K,Integer> map,K k) {
		decrementMap(map, k, 1);
	}
	
	/**
	 * map<K,Integer>自增
	 * @param map
	 * @param k
	 * @param e
	 */
	public static <K> void incrementList(List<Map<K,Integer>> list, int index, K k) {
		Map<K,Integer> map = null;
		if(index >= list.size()){
			map = new HashMap<>();
			list.add(map);
		}else{
			map = list.get(index);
		}
		incrementMap(map,k);
	}
	
	/**
	 * 检查对象是否存在与数组中
	 * @param ts
	 * @param obj
	 * @return
	 */
	public static <T> boolean checkExistsArray(T[] ts,T obj){
		for(T t:ts){
			if(t!=null && t.equals(obj)) return true;
		}
		return false;
	}
	
	
	public static <T> void moveList(int offset,List<T> list){
		for(int i=0;i<offset;i++){
			list.add(list.remove(0));
		}
	}
	
	/**
	 * 截取部分list
	 * @param list
	 * @param offset
	 * @param size
	 * @return
	 */
	public static <T> List<T> subList(List<T> list , int offset , int size){
		List<T> result = new ArrayList<>();
		for(int i=0;i<size;i++){
			if(i+offset >= list.size()) break;
			result.add(list.get(i+offset));
		}
		return result;
	}

	public static boolean checkIterator(Set<Long> set, Long obj){
		Iterator<Long> it = set.iterator();
		while(it.hasNext()){
			Long val = it.next();
			if(val != null && val.equals(obj)){
				return true;
			}
		}
		return false;
	}
	
	public static <K> K getMaxKey(Map<K,Integer> map){
		int maxValue = 0;
		K key = null;
		for(K k : map.keySet()){
			Integer value = map.get(k);
			if(value != null && value > maxValue){
				maxValue = value;
				key = k;
			}
		}
		return key;
	}
	

	/**
	 * 取数组的最大公约数
	 * @param cnts
	 * @param primeNumbers
	 * @return
	 * @author Z.R.K
	 */
	public static int getMaxCommonDivisor(int[] cnts) {
		for (int p = 100; p > 1; p++) {
			boolean check = true;
			for(int cnt : cnts){
				if(cnt % p > 0){
					check = false;
					break;
				}
			}
			if(check){
				return p;
			}
		}
		return 0;
	}
	
	/**
	 * 合计数组的值
	 * @param cnt
	 * @return
	 * @author Z.R.K
	 */
	public static int sum(int cnt[]){
		int result = 0;
		for(int x : cnt){
			result += x;
		}
		return result;
	}
	
	public static int[] getQuot(int[] cnt , int length){
		int[] quots = new int[cnt.length];
		for(int i=0;i<quots.length;i++){
			quots[i] = cnt[i] / length;
		}
		return quots;
	}
	
	public static int[] getRem(int[] cnt , int length){
		int[] rems = new int[cnt.length];
		for (int i = 0; i < rems.length; i++) {
			rems[i] = cnt[i] % length;
		}
		return rems;
	}
	
	
	/**
	 * 根据maxCnt,均分all
	 * 10 3返回 3,3,2,2
	 * @param all
	 * @param maxCnt
	 * @return
	 */
	public static int[] getAvg(int all , int maxCnt){
		int tmp = all / maxCnt;
		tmp = tmp + (all % maxCnt > 0 ? 1 : 0) ;
		int avg = all / tmp;
		int remainder = all % tmp;
		int[] result = new int[tmp];
		for (int index = 0; index < result.length; index++) {
			result[index] = avg;
			if (remainder > 0)
				result[index]++;
			remainder--;
		}
		return result;
	}
	
	/**
	 * 根据maxCnt,均分all
	 * 10 3返回 3,3,2,2
	 * @param all
	 * @param maxCnt
	 * @return
	 */
	public static void getAvg(int all , int[] result){
		int avg = all / result.length;
		int remainder = all % result.length;
		for(int index = 0;index<result.length ;index ++ ){
			result[index] = avg;
			if(remainder > 0)
				result[index] ++ ;
			remainder --;
		}
	}
	
	public static <T> JsonObject toJson(T t){
		JsonObject result = new JsonObject();
		Class<?> c = t.getClass();
		Field[] fields = c.getDeclaredFields();
		for(Field f : fields){
			f.setAccessible(true);
			try {
				result.addProperty(f.getName(), f.get(t)==null? "null" :  f.get(t).toString());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				result.addProperty(f.getName(), "null");
			}
		}
		return result;
	}
	
	public static <T> JsonObject toJson(T t,String ... fieldNames){
		JsonObject result = new JsonObject();
		Class<?> c = t.getClass();
		for(String fieldName : fieldNames){
			try {
				Field f = c.getDeclaredField(fieldName);
				f.setAccessible(true);
				result.addProperty(fieldName , f.get(t)==null? "null" :  f.get(t).toString());
			}catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
				result.addProperty(fieldName, "null");
			}
		}
		return result;
	}
	
	public static JsonObject mapToJson(Map<String,JsonArray> map){
		JsonObject result = new JsonObject();
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			result.add(key, map.get(key));
		}
		return result;
	}
	
	public static <T> JsonArray listToJsonArray(List<T> list, String ... fieldNames){
		JsonArray array = new JsonArray();
		if(list == null || list.size() == 0 ) return array;
		for(T t : list){
			array.add(toJson(t, fieldNames));
		}
		return array;
	}
	
	public static <T> List<T> reduceList(List<T> lists){
		List<T> t = new ArrayList<>(lists);
		return t;
	}
	
	/**
	 * 取数组中第一个最大值的索引
	 * @param array
	 * @return
	 * @author Z.R.K
	 */
	public static int getMaxIndex(int[] array){
		int max = Integer.MIN_VALUE;
		int index = 0;
		for(int i = 0;i < array.length;i++){
			if(array[i] > max){
				max = array[i];
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * 取数组中第一个最大值的索引
	 * @param array
	 * @return
	 * @author Z.R.K
	 */
	public static int getMaxIndex(long[] array){
		long max = Long.MIN_VALUE;
		int index = 0;
		for(int i = 0;i < array.length;i++){
			if(array[i] > max){
				max = array[i];
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * 取第一个最小数的索引
	 * @param array
	 * @param allNumber false时，仅取大于0的数
	 * @return
	 * @author Z.R.K
	 */
	public static int getMinIndex(int[] array , boolean allNumber){
		int min = Integer.MAX_VALUE;
		int index = 0;
		for(int i = 0;i < array.length;i++){
			if((!allNumber && array[i] > 0) || allNumber){
				if(array[i] < min){
					min = array[i];
					index = i;
				}
			}
		}
		return index;
	}
	
	public static int sumArray(int[] array){
		int sum = 0;
		for(int i : array){
			sum += i;
		}
		return sum;
	}
	
	public static <E> List<E> setToList(Set<E> set){
		if(set == null) return new ArrayList<>();
		List<E> list = new ArrayList<>();
		list.addAll(set);
		return list;
	}
	
	public static <E> void releaseArray(E[] array){
		if (array != null){
			for (int x = 0; x < array.length; x++) {
				array[x] = null;
			}
		}
	}
	
    /**
     * list<T>中抽取某字段组成Map<字段，T>
     * @param list
     * @param propertyName
     * @param propertyClass
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> getMapFromListByProperty(List<V> list, String propertyName, Class<K> propertyClass) {  
        if (CollectionUtils.isEmpty(list)) {
        	return new HashMap<>();  
        }
  
        Class<?> clz = list.get(0).getClass();  
        Map<K, V> resultMap = new HashMap<K, V>(list.size());  
        Method mth = getPropertyMethod(clz, propertyName);  
        for (Object obj : list) {  
            Object value = null;  
            try {  
                value = mth.invoke(obj);  
            } catch (Exception e) {  
                throw new RuntimeException(e);  
            }  
            if (value == null) {  
                continue;  
            }  
            resultMap.put(propertyClass.cast(value), (V) obj);  
        }  
        return resultMap;  
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Method getPropertyMethod(Class clz, String propertyName) {  
        Method mth = null;  
        try {  
            mth = clz.getMethod(upFirstCharacter(propertyName));  
        } catch (Exception e) {  
            System.out.println("获取类名发生错误！");  
        }  
        return mth;  
    }
    
    public static String upFirstCharacter(String propertyName) {  
        if (propertyName == null || propertyName.isEmpty()) {  
            System.out.println("获取类名发生错误！");  
            return null;  
        }  
        char x = propertyName.charAt(0);  
        String partOfPropertName = propertyName.substring(1);  
        StringBuffer newPropertName = new StringBuffer();  
        newPropertName.append("get").append(Character.toUpperCase(x)).append(partOfPropertName);  
        propertyName = newPropertName.toString();  
        return propertyName;  
    }
    
}

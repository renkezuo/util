package com.renke.office;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;
import com.renke.paike.DataUtil;
import com.renke.paike.model.LayerUserInfo;

public class ExcelHelper {
	@SuppressWarnings("resource")
	public static void exportExcel(){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("课表");
		HSSFRow row1 = sheet.createRow(0);
		HSSFRow row2 = sheet.createRow(1);
		
		HSSFCell cell = row1.createCell((short)1);
		HSSFCellStyle style = workbook.createCellStyle();
		setStyleBorder(style);
//		setCellBorder(cell);
		cell.setCellStyle(style);
		cell.setCellValue("123123123");
		cell = row2.createCell((short)1);
		cell.setCellValue("sdfklasjfkljsdf");
		try {
			workbook.write(new FileOutputStream(new File("D:/ok.xls")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void setStyleBorder(HSSFCellStyle style){
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	}
	
	public static String getStringValue(Cell cell) {
		return getStringValue(cell,"");
	}
	
	public static String getStringValue(Cell cell, String defaultVal) {
		if (cell == null) {
			return defaultVal;
		}
		cell.setCellType(CellType.STRING);
		String value = cell.getStringCellValue().trim();
		if("".equals(value)){
			return defaultVal;
		}else{
			return value;
		}
	}
	
	public static Workbook getWorkbook(String fileUrl) throws Exception {
		try {
			String fileId = getUploadFileId(fileUrl);
			File file = new File(fileUrl);
			FileInputStream fis = new FileInputStream(file);
			
			String suffix = fileId.split("\\.").length > 1 ? fileId.split("\\.")[1] : "";
			if ("xls".equalsIgnoreCase(suffix)) {
				return new HSSFWorkbook(fis);
			} else {
				return new XSSFWorkbook(fis);
			}
		} catch (Exception e) {
			throw new Exception("文件解析出错", e);
		}
	}
	
	private static String getUploadFileId(String fileUrl) {
		if (fileUrl.contains("http")) {
			String[] str = fileUrl.split("/");
			StringBuffer sb = new StringBuffer();
			for (int i = 3; i < str.length; i++) {
				if (i < str.length - 1) {
					sb.append(str[i]).append("/");
				} else {
					sb.append(str[i]);
				}
			}
			return sb.toString();
		} else {
			return fileUrl;
		}
	}
	
	public static Table<Integer,Integer,String> readWorkbookToTable(Workbook workbook, int sheetIndex, int colLength, int rowLength){
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		List<Integer> rowKeys = new ArrayList<>(rowLength);
		List<Integer> columnKeys = new ArrayList<>(colLength);
		for(int i=1;i<=rowLength;i++){
			rowKeys.add(i);
		}
		for(int i=1;i<=colLength;i++){
			columnKeys.add(i);
		}
		ArrayTable<Integer,Integer,String> at = ArrayTable.create(rowKeys, columnKeys);
		for(int i=0;i<rowLength;i++){
			Row row = sheet.getRow(i);
			for(int j=0;j<colLength;j++){
				String value = getStringValue(row.getCell(j));
				at.set(i, j, value);
			}
		}
		return at;
	}
	
	public static void filterCnt(Workbook workbook, int sheetIndex, int rowLength, int colLength
					,int[] filterColKey, int[] filterColVal){
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		int count = 0;
		for(int i=0;i<rowLength;i++){
			Row row = sheet.getRow(i);
			boolean check = true;
			for(int j=0;j<filterColKey.length;j++){
				String value = getStringValue(row.getCell(filterColKey[j]));
				if(!value.equals(""+filterColVal[j])){
					check = false;
				}
			}
			if(check){
				count ++;
			}
		}
		
		String result = "";
		for(int i=0;i<filterColKey.length;i++){
			result += (filterColVal[i] == 1? "," : ",非") + getTitle(filterColKey[i]);
		}
		
		System.out.println(result.substring(1) + ":" + count);
	}

	
	/**
	 * @param workbook
	 * @param sheetIndex
	 * @param rowLength
	 * @param colLength
	 * @param colIndexes	表示信息对应在excel中的列索引，-1表示无该列
	 * 	0-4 分别表示：乐号，学号，班级，姓名，性别
	 * 	5-11 	科目1-7成绩
	 * 	12-18	科目1-7的选择情况
	 * @return
	 * @author Z.R.K
	 */
	public static LayerUserInfo[] loadUser(Workbook workbook, int sheetIndex, int rowLength, int colLength
									, int[] colIndexes){
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		LayerUserInfo[] users = new LayerUserInfo[rowLength - 1];
		for(int i=1;i<rowLength;i++){
			Row row = sheet.getRow(i);
			LayerUserInfo user = new LayerUserInfo();
			if(colIndexes[0] != -1){
				user.lekeNo = getStringValue(row.getCell(colIndexes[0]));
			}
			if(colIndexes[1] != -1){
				user.stuNo = getStringValue(row.getCell(colIndexes[1]));
			}
			if(colIndexes[2] != -1){
				user.classId = Long.parseLong(getStringValue(row.getCell(colIndexes[2]), "0"));
			}
			if(colIndexes[3] != -1){
				user.userName = getStringValue(row.getCell(colIndexes[3]));
			}
			if(colIndexes[4] != -1){
				if(getStringValue(row.getCell(colIndexes[4])).equals("男")){
					user.isMan = 1;
				}
			}
			for (int k = 5; k < 12; k++) {
				if (colIndexes[k] != -1) {
					user.scores[k - 5] = Double.parseDouble(getStringValue(row.getCell(colIndexes[k]), "0"));
				}
			}
			for (int j = 12; j < 19; j++) {
				if (colIndexes[j] == -1) {
					continue;
				}
				String value = getStringValue(row.getCell(colIndexes[j]));
				if (value.equals("1")) {
					user.choices[j - 12] = true;
				}
			}
			users[i-1] = user;
		}
		return users;
	}
	
	
	public static Map<Integer, Integer> mappingCount(Workbook workbook, int sheetIndex, int rowLength, int colLength
									, int[] keys){
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		Map<Integer, Integer> mapping = new HashMap<>();
		for(int i=1;i<rowLength;i++){
			Row row = sheet.getRow(i);
			int key = 0;
			int key1 = 0;
			int key2 = 0;
			int key3 = 0;
			for(int j=0;j<keys.length;j++){
				String value = getStringValue(row.getCell(keys[j]));
				if(value.equals("1")){
					if((key >> 16) > 0 && ((key >> 8) & 255)  > 0){
						key += keys[j];
						key3 = keys[j] << 24;
					}else if((key >> 16) > 0){
						key += (keys[j] << 8);
						key2 = keys[j] << 24;
					}else{
						key += (keys[j] << 16);
						key1 = keys[j] << 24;
					}
				}
			}
			DataUtil.incrementMap(mapping, key);
			DataUtil.incrementMap(mapping, key1);
			DataUtil.incrementMap(mapping, key2);
			DataUtil.incrementMap(mapping, key3);
		}
		
//		Map<Integer, Integer> cntMapping1 = ExcelService.getCountMapping(data.subs, data.subs1, data.mapping);
		Iterator<Map.Entry<Integer, Integer>> it = mapping.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Integer> entry = it.next();
			System.out.println(ExcelHelper.getConcatTitle(entry.getKey()) + "	" + entry.getValue());
		}
		
		return mapping;
	}
	
	
	public static void printSum3(Workbook workbook, int sheetIndex, int rowLength, String subKey){
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		String sum1 = "=SUM(";
		String sum2 = "=SUM(";
		String sum3 = "=SUM(";
		for (int i = 1; i < rowLength; i++) {
			Row row = sheet.getRow(i);
			String combKey = getStringValue(row.getCell(0));
			String[] key = combKey.split(",");
			if(key[1].equals(subKey)){
				sum1 += "B"+(i+1) + ",";
				sum2 += "C"+(i+1) + ",";
				sum3 += "D"+(i+1) + ",";
			}
			if(key[2].equals(subKey)){
				sum1 += "C"+(i+1) + ",";
				sum2 += "D"+(i+1) + ",";
				sum3 += "B"+(i+1) + ",";
			}
			if(key[3].equals(subKey)){
				sum1 += "D"+(i+1) + ",";
				sum2 += "B"+(i+1) + ",";
				sum3 += "C"+(i+1) + ",";
			}
		}
		sum1 = sum1.substring(0, sum1.length() - 1) + ")";
		sum2 = sum2.substring(0, sum2.length() - 1) + ")";
		sum3 = sum3.substring(0, sum3.length() - 1) + ")";
		System.out.println(sum1);
		System.out.println(sum2);
		System.out.println(sum3);
	}
	

	public static void printSum4(Workbook workbook, int sheetIndex, int rowLength, String subKey){
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		String sum1 = "=SUM(";
		String sum2 = "=SUM(";
		String sum3 = "=SUM(";
		String sum4 = "=SUM(";
		for (int i = 1; i < rowLength; i++) {
			Row row = sheet.getRow(i);
			String combKey = getStringValue(row.getCell(0));
			String[] key = combKey.split(",");
			if(key[1].equals(subKey)){
				sum1 += "B"+(i+1) + ",";
				sum2 += "C"+(i+1) + ",";
				sum3 += "D"+(i+1) + ",";
				sum4 += "E"+(i+1) + ",";
			}
			if(key[2].equals(subKey)){
				sum1 += "C"+(i+1) + ",";
				sum2 += "D"+(i+1) + ",";
				sum3 += "E"+(i+1) + ",";
				sum4 += "B"+(i+1) + ",";
			}
			if(key[3].equals(subKey)){
				sum1 += "D"+(i+1) + ",";
				sum2 += "E"+(i+1) + ",";
				sum3 += "B"+(i+1) + ",";
				sum4 += "C"+(i+1) + ",";
			}
			if(key[4].equals(subKey)){
				sum1 += "E"+(i+1) + ",";
				sum2 += "B"+(i+1) + ",";
				sum3 += "C"+(i+1) + ",";
				sum4 += "D"+(i+1) + ",";
			}
		}
		sum1 = sum1.substring(0, sum1.length() - 1) + ")";
		sum2 = sum2.substring(0, sum2.length() - 1) + ")";
		sum3 = sum3.substring(0, sum3.length() - 1) + ")";
		sum4 = sum4.substring(0, sum4.length() - 1) + ")";
		System.out.println(sum1);
		System.out.println(sum2);
		System.out.println(sum3);
		System.out.println(sum4);
	}
	

	
	
	public static Map<Integer, Integer> readExcel(Workbook workbook, int sheetIndex, int rowLength, int colLength) {
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		Map<Integer, Integer> mapping = new HashMap<>();
		for (int i = 1; i < rowLength; i++) {
			Row row = sheet.getRow(i);
			String result = getStringValue(row.getCell(3));
			System.out.println(result);
		}

		// Map<Integer, Integer> cntMapping1 =
		// ExcelService.getCountMapping(data.subs, data.subs1, data.mapping);
		Iterator<Map.Entry<Integer, Integer>> it = mapping.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> entry = it.next();
			System.out.println(ExcelHelper.getConcatTitle(entry.getKey()) + "	" + entry.getValue());
		}

		return mapping;
	}
	
	public static void printResult(int[] allKey, Map<Integer, Integer> countMapping){
		for(int i=0;i<allKey.length;i++){
			for(int j=0;j<allKey.length;j++){
				for(int k=0;k<allKey.length;k++){
					if(j > i && k > j){
						Integer key = (allKey[i] << 16) + (allKey[j] << 8) + allKey[k];
						String title = getConcatTitle(key);
						Integer val = countMapping.get(key);
						if(val != null){
							System.out.println(title + "	" + val);
						}
					}
				}
			}
		}
		
		for(int i=0;i<allKey.length;i++){
			int count = 0;
			for(int j=0;j<allKey.length;j++){
				for(int k=0;k<allKey.length;k++){
					if(j > i && k > j){
						Integer key = (allKey[i] << 16) + (allKey[j] << 8) + allKey[k];
						Integer val = countMapping.get(key);
						if(val != null){
							count += val;
						}
					}
				}
			}
			System.out.println("第一志愿	"+getTitle(allKey[i]) + "	" + count);
		}
		

		for(int j=0;j<allKey.length;j++){
			int count = 0;
			for(int i=0;i<allKey.length;i++){
				for(int k=0;k<allKey.length;k++){
					if(j > i && k > j){
						Integer key = (allKey[i] << 16) + (allKey[j] << 8) + allKey[k];
						Integer val = countMapping.get(key);
						if(val != null){
							count += val;
						}
					}
				}
			}
			System.out.println("第二志愿	"+getTitle(allKey[j]) + "	" + count);
		}
		
		for(int k=0;k<allKey.length;k++){
			int count = 0;
			for(int i=0;i<allKey.length;i++){
				for(int j=0;j<allKey.length;j++){
					if(j > i && k > j){
						Integer key = (allKey[i] << 16) + (allKey[j] << 8) + allKey[k];
						Integer val = countMapping.get(key);
						if(val != null){
							count += val;
						}
					}
				}
			}
			System.out.println("第三志愿	"+getTitle(allKey[k]) + "	" + count);
		}
		
	}
	
	
	public static void printResult(int[] allKey, int[] filterColKey, Map<Integer, Integer> countMapping){
		Map<Integer, Integer> filterMapping = new HashMap<>();
		for(int i=0;i<allKey.length;i++){
			for(int j=0;j<allKey.length;j++){
				for(int k=0;k<allKey.length;k++){
					if(j > i && k > j){
						Integer key = (allKey[i] << 16) + (allKey[j] << 8) + allKey[k];
						Integer val = countMapping.get(key);
						boolean check[] = new boolean[filterColKey.length];
						// 存在一个key
						for(int f=0;f<filterColKey.length;f++){
							int fKey = filterColKey[f];
							if(fKey == allKey[i] || fKey == allKey[j] || fKey == allKey[k]){
								check[f] = true;
							}
						}
						
						int otherKey = 0;
						for(int c = 0 ; c < check.length ; c++){
							if(check[c]){
								if(otherKey == 0){
									otherKey += filterColKey[c];
								}else if((otherKey >> 8 & 255) == 0){
									otherKey += (filterColKey[c] << 8);
								}else if((otherKey >> 16 & 255) == 0){
									otherKey += (filterColKey[c] << 16);
								}
							}
						}
						Integer tmp = filterMapping.get(otherKey);
						filterMapping.put(otherKey, tmp == null ? val : (tmp+val));
					}
				}
			}
		}
		
		Iterator<Map.Entry<Integer, Integer>> it = filterMapping.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Integer> entry = it.next();
			System.out.println(getConcatTitle(entry.getKey()) + "	" + entry.getValue());
		}
		
	}
	
	
	/**
	 * @param workbook
	 * @param sheetIndex 表格index
	 * @param colBegin beginIndex
	 * @param colEnd endIndex
	 * @param rowBegin
	 * @param rowEnd
	 * @param subNo
	 * @return
	 * @author Z.R.K
	 */
	public static void printSum(Workbook workbook, 
					int sheetIndex, String subName){
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		String[] sums = new String[]{"=SUM(","=SUM(","=SUM("};
		for(int r=0;r<36;r++){
			Row row = sheet.getRow(r);
			String key = getStringValue(row.getCell(0));
			String[] subNames = key.split(",");
			if(subNames.length < 3){
				continue;
			}

			// A1B2C3
			if(subName.equals(subNames[0])){
				sums[0] += "C" + (r+1) + ",";
			}
			if(subName.equals(subNames[1])){
				sums[0] += "D" + (r+1) + ",";
			}
			if(subName.equals(subNames[2])){
				sums[0] += "E" + (r+1) + ",";
			}

			// B1C2A3
			if(subName.equals(subNames[2])){
				sums[1] += "C" + (r+1) + ",";
			}
			if(subName.equals(subNames[0])){
				sums[1] += "D" + (r+1) + ",";
			}
			if(subName.equals(subNames[1])){
				sums[1] += "E" + (r+1) + ",";
			}

			// C1A2B3
			if(subName.equals(subNames[1])){
				sums[2] += "C" + (r+1) + ",";
			}
			if(subName.equals(subNames[2])){
				sums[2] += "D" + (r+1) + ",";
			}
			if(subName.equals(subNames[0])){
				sums[2] += "E" + (r+1) + ",";
			}
			
		}
		
		for(String sum : sums){
			System.out.println(sum.substring(0, sum.length() - 1) + ")");
		}
		
	}
	
	public static void printSumBlend(Workbook workbook, int sheetIndex, String subName) {
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		String[] sums = new String[] { "=SUM(", "=SUM(", "=SUM(" };
		for (int r = 24; r < 44; r++) {
			Row row = sheet.getRow(r);
			String key = getStringValue(row.getCell(0));
			String[] subNames = key.split(",");
			if (subNames.length < 3) {
				continue;
			}

			// A1B2C3
			if (key.indexOf(subName) > -1) {
				sums[0] += "C" + (r + 1) + ",";
				sums[1] += "D" + (r + 1) + ",";
				sums[2] += "E" + (r + 1) + ",";
			}
		}

		for (String sum : sums) {
			System.out.println(sum.substring(0, sum.length() - 1) + ")");
		}

	}
	
	public static String getConcatTitle(Integer key){
		int key1 = key >> 16;
		int key2 = key >> 8 & 255;
		int key3 = key & 255;
		String title = getTitle(key1) + "," + getTitle(key2) + "," + getTitle(key3);
		return title;
	} 
	
	public static String getTitle(int index){
		switch (index) {
			case 3 :
				return "物理";
			case 4 :
				return "化学";
			case 5 :
				return "生物";
			case 6 :
				return "政治";
			case 7 :
				return "历史";
			case 8 :
				return "地理";
				
			case 14 :
				return "物理";
			case 15 :
				return "化学";
			case 16 :
				return "生物";
			case 17 :
				return "政治";
			case 18 :
				return "历史";
			case 19 :
				return "地理";
			case 20 :
				return "技术";
			default: 
				return "其它";
		}
	}
	
	public static void main(String[] args) {
		
		try {
			
//			Workbook workbook = getWorkbook("C:/Users/Administrator/Desktop/木斋中学/选课调查学生名单.xlsx");
//			Workbook workbook = getWorkbook("C:/Users/Administrator/Desktop/萧山9中分班结果 1022.xlsx");
//			Workbook workbook = getWorkbook("C:/Users/Administrator/Desktop/计算.xlsx");
			Workbook workbook = getWorkbook("C:/Users/Administrator/Desktop/1.xlsx");
			// 物，化，生，政，史，地
//			int[] keys =  new int[]{3,4,5,6,7,8};
			// 选考
//			int[] keys =  new int[]{3,5,4,7,6,8};
//			int[] filterKeys = new int[]{3,5,4};
			// 学考
//			int[] keys =  new int[]{7,6,8,3,5,4};
			// 物理14，化学15，生物16，政治17，历史18，地理19，技术20
//			int[] keys =  new int[]{14,15,16,17,18,19,20};
//			int[] keys =  new int[]{14,16,18,19,17,20,15};
//			int[] filterKeys = new int[]{17,19,15};
//			printResult(keys, count(workbook, 1, 213, 9, keys));
//			printResult(keys, filterKeys, count(workbook, 1, 213, 9, keys));
//			printResult(keys, mappingCount(workbook, 0, 491, 21, keys));
//			printResult(keys, filterKeys, mappingCount(workbook, 0, 491, 21, keys));
//			
//			String[] subNames = new String[]{"物理","化学","生物","政治","历史","地理","技术"};
//			for(String subName : subNames){
//				System.out.println("-------------" + subName + "----------------");
//				printSum(workbook, 2, subName);
//				printSumBlend(workbook, 2, subName);
//				System.out.println("-----------------------------------------end");
//			}
//			int[] filterColKey =  new int[]{14};
//			int[] filterColVal =  new int[]{1};
//			filterCnt(workbook, 1, 213, 9, filterColKey, filterColVal);
//			filterCnt(workbook, 0, 491, 21, new int[]{14}, filterColVal);
//			filterCnt(workbook, 0, 491, 21, new int[]{15}, filterColVal);
//			filterCnt(workbook, 0, 491, 21, new int[]{16}, filterColVal);
//			filterCnt(workbook, 0, 491, 21, new int[]{17}, filterColVal);
//			filterCnt(workbook, 0, 491, 21, new int[]{18}, filterColVal);
//			filterCnt(workbook, 0, 491, 21, new int[]{19}, filterColVal);
//			filterCnt(workbook, 0, 491, 21, new int[]{20}, filterColVal);
//			String[] keys = {"14","15","16","17","18","19","20"};
//			for(String str : keys){
//				System.out.println("---------"+str+"-------------");
//				printSum4(workbook, 7, 36, str);
//			}
			
			String[] keys = {"4","5","6","7","8","9"};
			for(String str : keys){
				System.out.println("---------"+str+"-------------");
				printSum3(workbook, 0, 21, str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		exportExcel();
	}
	
}

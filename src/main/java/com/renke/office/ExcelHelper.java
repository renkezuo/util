package com.renke.office;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelHelper {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void setStyleBorder(HSSFCellStyle style){
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	}
	public static void main(String[] args) {
		exportExcel();
	}
	
}

package com.renke.paike;


import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.renke.office.ExcelHelper;
import com.renke.paike.model.LayerData;
import com.renke.paike.model.SplitKlass;
import com.renke.paike.util.LayerUtil;

public class Controller {
	
	
	public static void main(String[] args) throws Exception {
		String fileUrl = "C:/Users/Administrator/Desktop/临时文件/萧山9中分班结果 1022.xlsx";
		// 第二个
		fileUrl = "C:/Users/Administrator/Desktop/临时文件/木斋中学/选课调查学生名单.xlsx";
		Workbook workbook = ExcelHelper.getWorkbook(fileUrl);
		// 初始化数据
		LayerData data = LayerUtil.getBaseData(workbook);
		// 检查数据
		LayerUtil.checkData(data);
		List<SplitKlass> klasses = divideClass(data);
//			if (data.layerType == 2) {
//				// 混合分班
//				LayerUtil.layerBlend(data);
//			} else {
//				// 选考分班
//				klasses.addAll(LayerUtil.layerIndep(data, true));
//				data.resetPart();
//				if(data.layerType == 0){
//					// 学考分班
//					klasses.addAll(LayerUtil.layerIndep(data, false));
//				}
//				data.clearAll();
//				// 
//				
//			}
		
		// insert into klasses
		// select id from ls_klass
		
//			List<KlassUser> users = new ArrayList<>(data.userCnt);
//			for(SplitKlass klass : klasses){
//				for(KlassUser user : klass.getStudents()){
//					user.setClassId(klass.getClassId());
//				}
//			}
		
		
		// set klasses id
		
		// insert into ls_klass_subject
		// insert into ls_choice_exam_klass
		// insert into ls_klass_student
		
		System.out.println("1,2,3,4".indexOf(""));
		
	}
		
	private static List<SplitKlass> divideClass(LayerData data) throws Exception {
		
		LayerUtil.checkData(data);
		
		List<SplitKlass> klasses = new ArrayList<>(data.subCnt * data.classCnt);
		
		if (data.layerType < 2) {
			// 选考分班
			klasses.addAll(LayerUtil.layerIndep(data, true));
			data.resetPart();
			if(data.layerType == 0){
				// 学考分班
				klasses.addAll(LayerUtil.layerIndep(data, false));
			}
			data.clearAll();
		} else {
			// 混合分班
			LayerUtil.layerBlend(data);
		}
		
		return klasses;
	}

}

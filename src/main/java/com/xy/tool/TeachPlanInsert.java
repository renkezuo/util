package com.xy.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeachPlanInsert {
	public static DBHelper lessonDB = new DBHelper("com.mysql.jdbc.Driver"
			,"jdbc:mysql://192.168.20.21:3306/lesson?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull"
			,"exschool_test"
			,"exschool2012");
	public static DBHelper tutorDB = new DBHelper("com.mysql.jdbc.Driver"
			,"jdbc:mysql://192.168.20.21:3306/tutor?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull"
			,"exschool_test"
			,"exschool2012");
	
	public static void main(String[] args) {
		//获取name like '%老师xy%' 的老师
//		List<Teacher> teachers = getTeachers("老师xy",43183L);
		//给老师设置科目
//		setTeacherSub(teachers, 494L);
		//排课任务ID，学校ID，分班任务ID
		initSplitTeachPlan(1068L,494L,1012L);
	}
	
	public static void initSplitTeachPlan(Long paikeTaskId, Long schoolId, Long taskId) {
		String planSql = "insert into ls_paike_teach_plan(paikeTaskId,gradeId,gradeName,classId,className,classType,teamId"
				+ ",weekType,subId,subName,teacherId,teacherName,weekCnt,seriesCnt,isDeleted)" + " values("
				+ paikeTaskId + ",11,'高二',?,?,3,?,1,?,?,?,?,?,?,0)";
		DBConstants[] types = new DBConstants[9];
		types[0] = DBConstants.SET_LONG;
		types[1] = DBConstants.SET_STRING;
		types[2] = DBConstants.SET_INT;
		types[3] = DBConstants.SET_LONG;
		types[4] = DBConstants.SET_STRING;
		types[5] = DBConstants.SET_LONG;
		types[6] = DBConstants.SET_STRING;
		types[7] = DBConstants.SET_INT;
		types[8] = DBConstants.SET_INT;

		// 获取班级
		List<Klass> splitKlasses = getSplitKlass(schoolId, taskId);
		// 获取老师
		Map<Long, List<Teacher>> subTeachers = getSubTeachers(schoolId);
		// 获取场地
		// List<Area> areas = getAreas();
		List<TeachPlan> teachPlans = new ArrayList<>();
		// 行政班 -- 科目-- 教室[固定] -- 老师-- 课时-- 连堂
		// 获取行政班，获取科目，获取科目对应老师，分配老师，场地
		int teamId = 1;
		for (int k = 0; k < splitKlasses.size(); k++) {
			Klass klass = splitKlasses.get(k);
			if (klass.getSubId() <= 3)
				continue;
			TeachPlan tp = new TeachPlan();
			tp.setClassId(klass.getClassId());
			tp.setClassName(klass.getClassName());
			tp.setSubId(klass.getSubId());
			tp.setSubName(klass.getSubName());
			tp.setTeamId(klass.getTeamId());
			if (klass.getTeamId() != teamId) {
				subTeachers = getSubTeachers(schoolId);
				teamId = klass.getTeamId();
			}
			Teacher teacher = getTeacherBySub(subTeachers, klass.getSubId());
			if (teacher != null) {
				tp.setTeacherId(teacher.getTeacherId());
				tp.setTeacherName(teacher.getTeacherName());
			}
			//修改授课计划课时数
			tp.setWeekCnt(3);
			
			
			teachPlans.add(tp);
		}
		Object[][] objs = new Object[teachPlans.size()][9];
		for (int t = 0; t < teachPlans.size(); t++) {
			TeachPlan tp = teachPlans.get(t);
			// cast
			objs[t][0] = tp.getClassId();
			objs[t][1] = tp.getClassName();
			objs[t][2] = tp.getTeamId();
			objs[t][3] = tp.getSubId();
			objs[t][4] = tp.getSubName();
			objs[t][5] = tp.getTeacherId();
			objs[t][6] = tp.getTeacherName();
			objs[t][7] = tp.getWeekCnt();
			objs[t][8] = tp.getSeriesCnt();
		}
		lessonDB.updateBatch(planSql, types, objs);
	}

	// 获取分层班
	public static List<Klass> getSplitKlass(Long schoolId, Long taskId) {
		String sql = "select a.classId,className,b.teamId,c.subjectId,c.subjectName "
				+ " from ls_klass a ,ls_choice_exam_klass b,ls_klass_subject c"
				+ " where a.classId=b.classId and a.classId = c.classId and a.isDeleted=0 and a.schoolId=" + schoolId
				+ " and a.type=3 and a.gradeId=11 and b.taskId = " + taskId + " order by b.teamId";
		List<Map<String, Object>> list = lessonDB.select(sql, null, null);
		List<Klass> klasses = new ArrayList<>();
		for (Map<String, Object> map : list) {
			Klass klass = new Klass();
			klass.setClassId(Long.parseLong(map.get("CLASSID") == null ? "0" : map.get("CLASSID").toString()));
			klass.setClassName(map.get("CLASSNAME") == null ? "null" : map.get("CLASSNAME").toString());
			klass.setTeamId(map.get("TEAMID") == null ? 0 : Integer.parseInt(map.get("TEAMID").toString()));
			klass.setSubId(map.get("SUBJECTID") == null ? 0L : Long.parseLong(map.get("SUBJECTID").toString()));
			klass.setSubName(map.get("SUBJECTNAME") == null ? "null" : map.get("SUBJECTNAME").toString());
			klasses.add(klass);
		}
		return klasses;
	}
	
	public static Map<Long,List<Teacher>> getSubTeachers(Long schoolId){
		Map<Long,List<Teacher>> subTeachers = new HashMap<>();
		String sql = "select teacherId,userName,subjectId from tutor.tutor_user_teacher_subject_grade a left join tutor.tutor_user b on a.teacherId=b.userId"
						+ " where a.gradeId=11 and a.isDeleted=0 and a.schoolId="+schoolId+" order by subjectId,teacherId";
		List<Map<String,Object>> teachers = tutorDB.select(sql, null, null);
		for(Map<String,Object> map : teachers){
			Teacher teacher = new Teacher();
			teacher.setTeacherId(Long.parseLong(map.get("TEACHERID").toString()));
			teacher.setTeacherName(map.get("USERNAME").toString());
			teacher.setSubId(Long.parseLong(map.get("SUBJECTID").toString()));
			if(subTeachers.get(teacher.getSubId()) == null ){
				subTeachers.put(teacher.getSubId(),new ArrayList<Teacher>());
			}
			subTeachers.get(teacher.getSubId()).add(teacher);
		}
		return subTeachers;
	}
	
	public static Teacher getTeacherBySub(Map<Long,List<Teacher>> subTeachers,Long subId){
		List<Teacher> teachers = subTeachers.get(subId);
		return teachers.remove(0);
	}
	
	public static void setTeacherSub(List<Teacher> teachers, Long schoolId){
		//4QrcOUm6Wau+VuBX8g+IPg==
		String sql = "insert into tutor_user_teacher_subject_grade(teacherId,gradeId,subjectId,roleId,schoolId,schoolStageId,isDeleted)"
				+ " values(?,11,?,101,"+schoolId+",62,0) ";
		DBConstants[] subTypes = new DBConstants[2];
		Object[][] subjects = new Object[teachers.size()][2];
		subTypes[0] = DBConstants.SET_LONG;
		subTypes[1] = DBConstants.SET_LONG;
		for(int i=0;i<teachers.size();i++){
			subjects[i][0] = teachers.get(i).getTeacherId();
			subjects[i][1] = getSubNo(teachers.get(i).getTeacherName());
		}
		tutorDB.updateBatch(sql, subTypes, subjects);
	}
	
	public static List<Teacher> getTeachers(String teacherName,Long minUserId){
		String sql = "select userId,userName from tutor_user where isDeleted=0 and userName like '%"+
				teacherName+"%'  and userId >= "+minUserId;
		List<Map<String,Object>> list = tutorDB.select(sql, null, null);
		List<Teacher> teachers = new ArrayList<>();
		for(Map<String,Object> map : list){
			Teacher teacher = new Teacher();
			teacher.setTeacherId(Long.parseLong(map.get("USERID")==null?"0":map.get("USERID").toString()));
			teacher.setTeacherName(map.get("USERNAME")==null?"null":map.get("USERNAME").toString());
			teachers.add(teacher);
		}
		return teachers;
	}
	
	public static Long getSubNo(String name){
		if(name.indexOf("语文") > -1){
			return 1L;
		}else if(name.indexOf("数学") > -1){
			return 2L;
		}else if(name.indexOf("外语") > -1){
			return 3L;
		}else if(name.indexOf("物理") > -1){
			return 4L;
		}else if(name.indexOf("化学") > -1){
			return 5L;
		}else if(name.indexOf("生物") > -1){
			return 6L;
		}else if(name.indexOf("政治") > -1){
			return 7L;
		}else if(name.indexOf("历史") > -1){
			return 8L;
		}else if(name.indexOf("地理") > -1){
			return 9L;
		}else if(name.indexOf("技术") > -1){
			return 19L;
		}else if(name.indexOf("体育") > -1){
			return 29L;
		}else{
			return -1L;
		}
	}
}


class Teacher {
	public Long teacherId;
	public String teacherName;
	public Long subId;
	public int cnt=3;
	
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Long getSubId() {
		return subId;
	}
	public void setSubId(Long subId) {
		this.subId = subId;
	}
}



class Klass {
	// 班级ID
	private Long classId;
	// 班级名称
	private String className;
	// 班级类型
	private Integer type;
	// 关联ID
	private Long relationId;
	// 年级ID
	private Long gradeId;
	// 年级名称
	private String gradeName;
	// 学段ID
	private Long schoolStageId;
	// 学校ID
	private Long schoolId;
	// 班级人数
	private Integer headCount;
	
	private Integer teamId;
	
	private Long subId;
	
	private String subName;
	
	public Long getSubId() {
		return subId;
	}
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getRelationId() {
		return relationId;
	}
	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Long getSchoolStageId() {
		return schoolStageId;
	}
	public void setSchoolStageId(Long schoolStageId) {
		this.schoolStageId = schoolStageId;
	}
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getHeadCount() {
		return headCount;
	}
	public void setHeadCount(Integer headCount) {
		this.headCount = headCount;
	}
	@Override
	public String toString() {
		return "Klass [className=" + className + "]";
	}
}
enum DBConstants {
	SET_STRING,
	SET_BIGDECIMAL,
	SET_STREAM,
	SET_DATE,
	SET_DOUBLE,
	SET_INT,
	SET_LONG,
	SET_OBJECT,
	SET_TIMESTAMP
}

class TeachPlan {
	//年级
	private Long gradeId;
	private String gradeName;
	//班级
	private Long classId;
	private String className;
	//场地
	private Long areaId;
	private String areaName;
	//科目
	private Long subId;
	private String subName;
	//老师
	private Long teacherId;
	private String teacherName;
	//周课时数
	private int weekCnt;
	//连堂数
	private int seriesCnt;
	//常规1，单周2，双周3
	private int weekType;
	//用户列表
	private int teamId;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getSubId() {
		return subId;
	}
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(int weekCnt) {
		this.weekCnt = weekCnt;
	}
	public int getSeriesCnt() {
		return seriesCnt;
	}
	public void setSeriesCnt(int seriesCnt) {
		this.seriesCnt = seriesCnt;
	}
	public int getWeekType() {
		return weekType;
	}
	public void setWeekType(int weekType) {
		this.weekType = weekType;
	}
}
class User {
	private Long userId ;
	private String userName;
	private List<Long> checkSubs;
	private double score1;
	private double score2;
	private double score3;
	private double score4;
	private double score5;
	private double score6;
	private double score7;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Long> getCheckSubs() {
		return checkSubs;
	}
	public void setCheckSubs(List<Long> checkSubs) {
		this.checkSubs = checkSubs;
	}
	
	public double getScore1() {
		return score1;
	}
	public void setScore1(double score1) {
		this.score1 = score1;
	}
	public double getScore2() {
		return score2;
	}
	public void setScore2(double score2) {
		this.score2 = score2;
	}
	public double getScore3() {
		return score3;
	}
	public void setScore3(double score3) {
		this.score3 = score3;
	}
	public double getScore4() {
		return score4;
	}
	public void setScore4(double score4) {
		this.score4 = score4;
	}
	public double getScore5() {
		return score5;
	}
	public void setScore5(double score5) {
		this.score5 = score5;
	}
	public double getScore6() {
		return score6;
	}
	public void setScore6(double score6) {
		this.score6 = score6;
	}
	public double getScore7() {
		return score7;
	}
	public void setScore7(double score7) {
		this.score7 = score7;
	}
	
	@Override
	public String toString() {
		String str = "User [userId=" + userId + ", userName=" + userName + ", score1=" + score1
				+ ", score2=" + score2 + ", score3=" + score3 + ", score4=" + score4 + ", score5=" + score5
				+ ", score6=" + score6 + ", score7=" + score7 + ", checkSubs='";
		if(checkSubs!=null && !checkSubs.isEmpty()){
			for(int i=0;i<checkSubs.size();i++){
				str += checkSubs.get(i)+",";
			}
			str = str.substring(0,str.length()-1);
		}
		str += "']";
		return str;
	}
}


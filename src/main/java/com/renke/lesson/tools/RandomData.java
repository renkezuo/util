package com.renke.lesson.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.renke.lesson.pojo.Area;
import com.renke.lesson.pojo.Klass;
import com.renke.lesson.pojo.KlassExt;
import com.renke.lesson.pojo.Subject;
import com.renke.lesson.pojo.TeachPlan;
import com.renke.lesson.pojo.Teacher;
import com.renke.lesson.pojo.User;
import com.renke.util.db.DBConstants;

/**
 * ���ɶ��߳�ִ��
 * @author Z.R.K
 * @time 2017-05-03 16:00:35
 */
public class RandomData {
	//��ʼ������ѡ����Ŀ
	public final static List<Long> subIds = new ArrayList<>(7);
	//��ʼ�����п�Ŀ
	public final static Long[] subs = new Long[12];
	//ѡ����Ŀ��Ӧѡ��ѧ��
	public static List<List<User>> subList = new ArrayList<>(7);
	public static DBHelper lessonDB = null;
	public static DBHelper tutorDB = null;
	static{
		for(int i=0;i<7;i++){
			subList.add(new ArrayList<User>());
			if(i == 6){
				subIds.add(19L);
			}else{
				subIds.add(i+4L);
			}
		}
		for(int i=0;i<12;i++){
			if(i<9){
				subs[i] = i+1L;
			}else if(i==9){
				subs[i] = 19L;
			}else if(i==10){
				subs[i] = 29L;
			}else{
				subs[i] = -1L;
			}
		}
		lessonDB = new DBHelper("com.mysql.jdbc.Driver"
				,"jdbc:mysql://192.168.20.21:3306/lesson?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull"
				,"exschool_test"
				,"exschool2012");
		tutorDB = new DBHelper("com.mysql.jdbc.Driver"
				,"jdbc:mysql://192.168.20.21:3306/tutor?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull"
				,"exschool_test"
				,"exschool2012");
	}

	//��ʼ��ѧ��
	public static List<User> getRandomUser(int count){
		//4QrcOUm6Wau+VuBX8g+IPg==
		String sql = "insert into tutor_user(userName,nick,loginName,oldLoginName,password,isDeleted) values ";
		List<User> list = new ArrayList<>();
		for(int i=0;i<count;i++){
			User user = new User();
			user.setUserId(i+1L);
			user.setUserName("ѧ��"+(i+1));
			sql += "('"+user.getUserName()+"','"+user.getUserName()+"','"+user.getUserName()+"','"+user.getUserName()+"','4QrcOUm6Wau+VuBX8g+IPg==',5),";
			list.add(user);
		}
		sql = sql.substring(0, sql.length() -1);
		Long[] ids = tutorDB.updateReturnIds(sql,null,null,list.size());
		sql = "";
		for(int i =0;i<ids.length;i++){
			list.get(i).setUserId(ids[i]);
		}
		return list;
	}
	
	//��ʼ����ʦ[ÿ��Ŀ4��ʦ]
	public static List<User> getRandomTeacher(){
		//4QrcOUm6Wau+VuBX8g+IPg==
		String teacher_sql = "insert into tutor_user(userName,nick,loginName,oldLoginName,password,isDeleted) values ";
		List<User> list = new ArrayList<>();
		for(int i=0;i<subs.length;i++){
			for(int t=1;t<5;t++){
				User user = new User();
				user.setUserName(getSubName(subs[i])+"��ʦ"+t);
				teacher_sql += "('"+user.getUserName()+"','"+user.getUserName()+"','"+user.getUserName()+"','"+user.getUserName()+"','4QrcOUm6Wau+VuBX8g+IPg==',5),";
				list.add(user);
			}
		}
		teacher_sql = teacher_sql.substring(0, teacher_sql.length() -1);
		Long[] ids = tutorDB.updateReturnIds(teacher_sql,null,null,list.size());
		for(int i =0;i<ids.length;i++){
			list.get(i).setUserId(ids[i]);
		}
		return list;
	}
	
	//��ȡ��ʼ��ѧ��
	public static List<User> getUser(){
		String sql = "select userId,userName from tutor_user where isDeleted=5 and userName like '%ѧ��%'";
		List<Map<String,Object>> list = tutorDB.select(sql, null, null);
		List<User> users = new ArrayList<>();
		for(Map<String,Object> map : list){
			User user = new User();
			user.setUserId(Long.parseLong(map.get("USERID")==null?"0":map.get("USERID").toString()));
			user.setUserName(map.get("USERNAME")==null?"null":map.get("USERNAME").toString());
			users.add(user);
		}
		return users;
	}
	
	//��ȡ��ʼ����ʦ
	public static List<User> getTeacher(){
		String sql = "select userId,userName from tutor_user where isDeleted=5 and userName like '%��ʦ%'";
		List<Map<String,Object>> list = tutorDB.select(sql, null, null);
		List<User> users = new ArrayList<>();
		for(Map<String,Object> map : list){
			User user = new User();
			user.setUserId(Long.parseLong(map.get("USERID")==null?"0":map.get("USERID").toString()));
			user.setUserName(map.get("USERNAME")==null?"null":map.get("USERNAME").toString());
			users.add(user);
		}
		return users;
	}
	
	/**
	 * ��ȡ������
	 * @return
	 */
	public static List<Klass> getNomalKlass(){
		String sql = "select classId,className from ls_klass where relationId>4253 and relationId<4264";
		List<Map<String,Object>> list = lessonDB.select(sql, null, null);
		List<Klass> klasses = new ArrayList<>();
		for(Map<String,Object> map : list){
			Klass klass = new Klass();
			klass.setClassId(Long.parseLong(map.get("CLASSID")==null?"0":map.get("CLASSID").toString()));
			klass.setClassName(map.get("CLASSNAME")==null?"null":map.get("CLASSNAME").toString());
			klasses.add(klass);
		}
		return klasses;
	}
	
	//����10��������[struct] �ֶ���ҳ�����
	//��ѧ������10����������[user_struct] 
	//����ѧ����Ȩ��
	public static void setUserStructAndRole(List<User> users,Long roleId){
		String struct_sql = "insert into tutor_user_struct (userId,depId,isDeleted,roleId,userName) values(?,?,5,"+roleId+",?)";
		String role_sql = "insert into tutor_user_role_school(userId,roleId,schoolId,isDeleted) values(?,"+roleId+",476,5)";
		DBConstants[] studentTypes = new DBConstants[3];
		Object[][] students = new Object[users.size()][3];
		DBConstants[] roleTypes = new DBConstants[1];
		Object[][] roles = new Object[users.size()][1];
		studentTypes[0] = DBConstants.SET_LONG;
		studentTypes[1] = DBConstants.SET_LONG;
		studentTypes[2] = DBConstants.SET_STRING;
		roleTypes[0] = DBConstants.SET_LONG;
		Long depId = 4254L;
		for(int index = 0;index< users.size();index++){
			if(index!=0 && index % 50 == 0){
				depId++;
			}
			students[index][0] = users.get(index).getUserId();
			students[index][2] = users.get(index).getUserName();
			students[index][1] = depId;
			roles[index][0] = users.get(index).getUserId();
		}
		tutorDB.update("delete from tutor_user_struct where roleId="+roleId+" and isDeleted=5",null,null);
		tutorDB.update("delete from tutor_user_role_school where roleId="+roleId+" and isDeleted=5",null,null);
		tutorDB.updateBatch(struct_sql, studentTypes, students);
		tutorDB.updateBatch(role_sql, roleTypes, roles);
	}
	
	//�ְ࣬ͬʱд��ѧ��
	public static void splitClass(List<User> users,List<KlassExt> klasses){
		//����༶����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(System.currentTimeMillis());
		String sql = "insert into ls_klass(className,type,gradeId,gradeName,schoolStageId,schoolId,headCount,isDeleted,createdBy,createdOn,modifiedBy,modifiedOn)"
				+ " values ";
		Object[] objs = new Object[klasses.size()];
		DBConstants[] types = new DBConstants[klasses.size()];
//			Object[] objs = new Object[2];
//			DBConstants[] types = new DBConstants[2];
		for(int i=0;i<klasses.size();i++){
			sql += "(?,3,11,'�߶�',62,476,0,5,'888','"+now+"','888','"+now+"'),";
			//�����༶
			KlassExt ext = klasses.get(i);
			objs[i] = ext.getKlass().getClassName();
			types[i] = DBConstants.SET_STRING;
		}
		sql = sql.substring(0, sql.length() -1);
		Long[] ids = lessonDB.updateReturnIds(sql,types,objs,klasses.size());
		//����༶ѧ������
		sql = "insert into ls_klass_student(classId,userId,userName,schoolId,isDeleted) values(?,?,?,476,5)";
		DBConstants[] studentTypes = new DBConstants[3];
		studentTypes[0] = DBConstants.SET_LONG;
		studentTypes[1] = DBConstants.SET_LONG;
		studentTypes[2] = DBConstants.SET_STRING;
		if(klasses.size() != ids.length) return ;
		for(int i=0;i<klasses.size();i++){
			KlassExt ext = klasses.get(i);
			ext.getKlass().setClassId(ids[i]);
			Object[][] students = new Object[ext.getUsers().size()][3];
			for(int u=0;u <ext.getUsers().size();u++){
				students[u][0] = ext.getKlass().getClassId();
				students[u][1] = ext.getUsers().get(u).getUserId();
				students[u][2] = ext.getUsers().get(u).getUserName();
			}
			lessonDB.updateBatch(sql, studentTypes, students);
		}
		//���°༶ѧ������
		sql = "update ls_klass a set headCount=(select count(1) from ls_klass_student b where a.classId=b.classId) where isDeleted=5";
		lessonDB.update(sql, null, null);
	}
	

	//��ȡ�ֲ��
	public static List<Klass> getSplitKlass(){
		String sql = "select classId,className from ls_klass where isDeleted=5";
		List<Map<String,Object>> list = lessonDB.select(sql, null, null);
		List<Klass> klasses = new ArrayList<>();
		for(Map<String,Object> map : list){
			Klass klass = new Klass();
			klass.setClassId(Long.parseLong(map.get("CLASSID")==null?"0":map.get("CLASSID").toString()));
			klass.setClassName(map.get("CLASSNAME")==null?"null":map.get("CLASSNAME").toString());
			klasses.add(klass);
		}
		return klasses;
	}
	
	//���ѧ��ѡ���Ŀ
	//���ѧ���Ŀ�Ŀ�ɼ�
	public static void setRandomSubInfo(List<User> users,Long taskId){
		Object[][] objs = new Object[users.size()][9];
		DBConstants[] types = new DBConstants[9];
		
		String sql = "insert into ls_choice_exam_task_student(taskId,userId,score1,score2,score3,score4,score5,score6,score7,status,choiceSubs,isDeleted)"
					+" values ('"+taskId+"',?,?,?,?,?,?,?,?,2,?,5)";
		
		types[0] = DBConstants.SET_LONG;
		types[1] = DBConstants.SET_DOUBLE;
		types[2] = DBConstants.SET_DOUBLE;
		types[3] = DBConstants.SET_DOUBLE;
		types[4] = DBConstants.SET_DOUBLE;
		types[5] = DBConstants.SET_DOUBLE;
		types[6] = DBConstants.SET_DOUBLE;
		types[7] = DBConstants.SET_DOUBLE;
		types[8] = DBConstants.SET_STRING;
		
		for(int i =0;i<users.size();i++){
			User user = users.get(i);
			setUserSub(user);
			setUserScore(user);
			objs[i][0] = user.getUserId();
			objs[i][1] = user.getScore1();
			objs[i][2] = user.getScore2();
			objs[i][3] = user.getScore3();
			objs[i][4] = user.getScore4();
			objs[i][5] = user.getScore5();
			objs[i][6] = user.getScore6();
			objs[i][7] = user.getScore7();
			String subs = ",";
			for(Long subId : user.getCheckSubs()){
				subs += subId +",";
			}
			objs[i][8] = subs;
		}
		lessonDB.updateBatch(sql, types, objs);
	}
	
	//����������ѧ��
	public static void setUserClass(List<User> users){
		String user_sql = "insert into ls_klass_student (classId,userId,userName,schoolId,isDeleted) values(?,?,?,476,5)";
		String class_sql = "update ls_klass a set headCount=(select count(1) from ls_klass_student b where a.classId = b.classId) where classId =?";
		DBConstants[] studentTypes = new DBConstants[3];
		Object[][] students = new Object[users.size()][3];
		
		DBConstants[] types = new DBConstants[1];
		Object[][] objs = new Object[users.size()][1];
		
		studentTypes[0] = DBConstants.SET_LONG;
		studentTypes[1] = DBConstants.SET_LONG;
		studentTypes[2] = DBConstants.SET_STRING;
		types[0] = DBConstants.SET_LONG;
		Long relationId = 4254L;
		Long classId = getClassId(relationId);
		for(int index = 0;index < users.size();index++){
			if(index != 0 && index % 50 == 0){
				relationId++;
				classId = getClassId(relationId);
			}
			students[index][0] = classId;
			students[index][1] = users.get(index).getUserId();
			students[index][2] = users.get(index).getUserName();
			objs[index][0] = classId;
		}
		lessonDB.updateBatch("delete from ls_klass_student where classId=? and isDeleted=5 and schoolId=476", types, objs);
		lessonDB.updateBatch(user_sql, studentTypes, students);
		lessonDB.updateBatch(class_sql, types, objs);
	}
	

	
	//���°༶��Ӧ��Ŀ
	public static void updateClassSub(){
		List<Klass> klasses = getNomalKlass();
		
		//����༶��Ŀ��Ϣ
		String add_sql = "insert into ls_klass_subject(classId,subjectId,subjectName,total,plan,finish,schoolId,isDeleted)"
					+" values(?,1,'����',0,0,0,476,5)";
		String del_sql = "delete from ls_klass_subject where isDeleted=5";
		DBConstants[] types = new DBConstants[1];
		types[0] = DBConstants.SET_LONG;
		Object[][] objs = new Object[klasses.size()][1];
		for(int i =0;i<klasses.size() ;i++){
			objs[i][0] = klasses.get(i).getClassId();
		}
		lessonDB.update(del_sql, null, null);
		lessonDB.updateBatch(add_sql, types, objs);
		lessonDB.updateBatch(add_sql.replaceAll("1,'����'","2,'��ѧ'"), types, objs);
		lessonDB.updateBatch(add_sql.replaceAll("1,'����'","3,'����'"), types, objs);
		lessonDB.updateBatch(add_sql.replaceAll("1,'����'","29,'����'"), types, objs);
		
		klasses = getSplitKlass();
		add_sql = "insert into ls_klass_subject(classId,subjectId,subjectName,total,plan,finish,schoolId,isDeleted)"
				+" values(?,?,?,0,0,0,476,5)";
		types = new DBConstants[3];
		types[0] = DBConstants.SET_LONG;
		types[1] = DBConstants.SET_LONG;
		types[2] = DBConstants.SET_STRING;
		objs = new Object[klasses.size()][3];
		for(int i =0;i<klasses.size() ;i++){
			objs[i][0] = klasses.get(i).getClassId();
			objs[i][1] = getSubNo(klasses.get(i).getClassName());
			objs[i][2] = getSubName((Long)objs[i][1]);
		}
		lessonDB.updateBatch(add_sql, types, objs);
	}
	
	//������ʦ��Ŀ
	public static void setTeacherSub(List<User> teachers){
		//4QrcOUm6Wau+VuBX8g+IPg==
		String sql = "insert into tutor_user_teacher_subject_grade(teacherId,gradeId,subjectId,roleId,schoolId,schoolStageId,isDeleted)"
				+ " values(?,11,?,101,476,62,5) ";
		DBConstants[] subTypes = new DBConstants[2];
		Object[][] subjects = new Object[teachers.size()][2];
		subTypes[0] = DBConstants.SET_LONG;
		subTypes[1] = DBConstants.SET_LONG;
		for(int i=0;i<teachers.size();i++){
			subjects[i][0] = teachers.get(i).getUserId();
			subjects[i][1] = getSubNo(teachers.get(i).getUserName());
		}
		tutorDB.updateBatch(sql, subTypes, subjects);
	}
	//��ѧ����
	public static void initArea(){
		String sql = "insert into ls_teach_area(areaName,building,stuCnt,classCnt,schoolId,isDeleted)"
				+" values(?,?,50,1,476,5)";
		DBConstants[] types = new DBConstants[2];
		types[0] = DBConstants.SET_STRING;
		types[1] = DBConstants.SET_STRING;
		Object[][] objs = new Object[12][2];
		for(int i=1 ;i < 13 ; i++){
			objs[i-1][0] = 200+i + "";
			objs[i-1][1] = "1��¥";
		}
		lessonDB.updateBatch(sql, types, objs);
	}
	//�ڿμƻ�
	//������---5��ʱ
	//��������ʷ�أ�����ѡ��4��ѧ��2
	//1�壬3����
	public static void initTeachPlanAndArea(){
		String planSql = "insert into ls_teach_plan(paikeTaskId,gradeId,gradeName,classId,className,areaId,areaName,weekType,subId,subName,teacherId,teacherName,weekCnt,seriesCnt)"
						+" values(999,11,'�߶�',?,?,?,?,1,?,?,?,?,?,?)";
		String areaSql = "insert into ls_paike_task_area select 999,areaId from ls_teach_area where isDeleted=5";
		
		DBConstants[] types = new DBConstants[10];
		types[0] = DBConstants.SET_LONG;
		types[1] = DBConstants.SET_STRING;
		types[2] = DBConstants.SET_LONG;
		types[3] = DBConstants.SET_STRING;
		types[4] = DBConstants.SET_LONG;
		types[5] = DBConstants.SET_STRING;
		types[6] = DBConstants.SET_LONG;
		types[7] = DBConstants.SET_STRING;
		types[8] = DBConstants.SET_INT;
		types[9] = DBConstants.SET_INT;
		
		//��ȡ�༶
		List<Klass> nomalKlasses = getNomalKlass();
		//��ȡ��Ŀ
		List<Subject> subjects = getSubjects();
		//��ȡ��ʦ
		Map<Long,List<Teacher>> subTeachers = getSubTeachers();
		//��ȡ����
		List<Area> areas = getAreas();
		List<TeachPlan> teachPlans = new ArrayList<>();
		//������ -- ��Ŀ-- ����[�̶�] -- ��ʦ-- ��ʱ-- ����
		//��ȡ�����࣬��ȡ��Ŀ����ȡ��Ŀ��Ӧ��ʦ��������ʦ������
		for(int k=0;k<nomalKlasses.size();k++){
			Klass klass = nomalKlasses.get(k);
			Area area = areas.get(k);
			for(Subject subject : subjects){
				TeachPlan tp = new TeachPlan();
				tp.setAreaId(area.getAreaId());
				tp.setAreaName(area.getAreaName());
				tp.setClassId(klass.getClassId());
				tp.setClassName(klass.getClassName());
				tp.setSubId(subject.getSubjectId());
				tp.setSubName(subject.getSubjectName());
				Teacher teacher = getTeacherBySub(subTeachers, subject.getSubjectId());
				if(teacher != null){
					tp.setTeacherId(teacher.getTeacherId());
					tp.setTeacherName(teacher.getTeacherName());
				}
				if(subject.getSubjectId() == 29L){
					tp.setWeekCnt(1);
					tp.setSeriesCnt(0);
				}else{
					tp.setWeekCnt(5);
					tp.setSeriesCnt(1);
				}
				teachPlans.add(tp);
			}
		}
		//�ֲ��
		//�����༶
		//���ÿ�Ŀ��ʦ�����ÿ�ʱ����������
		List<Klass> splitKlasses = getSplitKlass();
		for(Klass klass : splitKlasses){
			TeachPlan tp = new TeachPlan();
			//cast
			tp.setClassId(klass.getClassId());
			tp.setClassName(klass.getClassName());
			tp.setSubId(getSubNo(klass.getClassName()));
			tp.setSubName(getSubName(getSubNo(klass.getClassName())));
			Teacher teacher = getTeacherBySub(subTeachers, tp.getSubId());
			if(teacher != null){
				tp.setTeacherId(teacher.getTeacherId());
				tp.setTeacherName(teacher.getTeacherName());
			}
			if(klass.getClassName().indexOf("ѡ��")!=-1){
				tp.setWeekCnt(4);
			}else{
				tp.setWeekCnt(2);
			}
			tp.setSeriesCnt(0);
			teachPlans.add(tp);
		}
		Object[][] objs = new Object[teachPlans.size()][10];
		for(int t=0;t<teachPlans.size();t++){
			TeachPlan tp = teachPlans.get(t);
			//cast
			objs[t][0] = tp.getClassId();
			objs[t][1] = tp.getClassName();
			objs[t][2] = tp.getAreaId();
			objs[t][3] = tp.getAreaName();
			objs[t][4] = tp.getSubId();
			objs[t][5] = tp.getSubName();
			objs[t][6] = tp.getTeacherId();
			objs[t][7] = tp.getTeacherName();
			objs[t][8] = tp.getWeekCnt();
			objs[t][9] = tp.getSeriesCnt();
		}
		lessonDB.update("delete from ls_paike_task_area where paikeTaskId=999", null, null);
		lessonDB.update("delete from ls_teach_plan where paikeTaskId=999", null, null);
		lessonDB.update(areaSql, null, null);
		lessonDB.updateBatch(planSql, types, objs);
	}
	
	/**
	 * ���ѧ��ѡ��Ŀ�Ŀ
	 * @param user
	 */
	public static void setUserSub(User user){
		Random random = new Random();
		List<Long> list = new ArrayList<>();
		for(int i=0;i<3;i++){
			int id = random.nextInt(100) % 7;
			Long subId = subIds.get(id);
			while(list.contains(subId)){
				id = random.nextInt(100) % 7;
				subId = subIds.get(id);
			}
			list.add(subId);
			subList.get(id).add(user);
		}
		list.sort(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				if(o1 < o2){
					return -1;
				}
				return 1;
			}
		});
		user.setCheckSubs(list);
	}

	//����ѡ���Ŀ��ѧ�����ֲ�[50��ÿ�࣬50�˵�һ�㣬100�˵ڶ��㣬ʣ������㣬����ѧ��Ϊѧ��]
	//�����༶�б�ͬʱ��ѧ������ĳ���༶
	public static List<KlassExt> assemble(List<User> users,List<Long> subIds){
		List<KlassExt> klasses = new ArrayList<>();
		//FIXME ��ȡ��Ŀ��Ӧ�ķְ����
		
		for(Long subId : subIds){
			//FIXME ʹ�û�ȡ�Ŀ�Ŀ��Ӧ�ְ����ְ࣬�˴����ԣ�ʹ��level1 = 50 level2 = 100 level3 = last level4��ʾѧ��
			
			//��ȡѡ�иÿ�Ŀ���û��б�
			List<User> checkedUser = new ArrayList<>();
			List<User> uncheckedUser = new ArrayList<>();
			for(User user : users){
				if(user.getCheckSubs().contains(subId)){
					checkedUser.add(user);
				}else{
					uncheckedUser.add(user);
				}
			}
			//����ѧ����Ŀ�ɼ�����
			checkedUser.sort(new SortUser(subId));
//			if(subId == 19L)
//			for(User user : checkedUser){
//				System.out.println(user);
//			}
			
			//�����ְ�����
			int userCnt = checkedUser.size();
			int personCnt = 50;
			int level1 = 50;
			int level2 = 100;
			int level3 = userCnt - level2 - level1;
			int level4 = users.size() - checkedUser.size();
			String baseName = "�߶�ѡ��"+getSubName(subId)+"levelindex��";
			String otherName = "�߶�ѧ��"+getSubName(subId)+"levelindex��";

			split(klasses, checkedUser, baseName, subId, personCnt, level1,0,1);
			
			split(klasses, checkedUser, baseName, subId, personCnt, level2,level1,2);
			
			split(klasses, checkedUser, baseName, subId, personCnt, level3,level1 + level2,3);
			
			split(klasses, uncheckedUser, otherName, subId, personCnt, level4,0,5);
		}
		klasses.forEach(System.out::println);
		
		return klasses;
	}

	public static void split(List<KlassExt> klasses, List<User> users,String baseName
				, Long subId, int personCnt, int count,int begin,int level) {
		int classCnt = (count % personCnt>0? 1:0) + count / personCnt;
		List<KlassExt> splitClass = splitClass(subId,classCnt, level, baseName);
		personCnt = count % classCnt > 0 ? 1 : 0+ count/classCnt ;
		setSplitKlassUser(users, splitClass, personCnt, count, classCnt,begin);
		klasses.addAll(splitClass);
	}

	//Ĭ�ϰ༶�������õȼ����������༶������ʼ����
	public static void setSplitKlassUser(List<User> users,
			List<KlassExt> splitClass, int personCnt, int count, int classCnt,int begin) {
		//��ȡ���зְ�༶
		for(int i=0;i<classCnt;i++){
			//�༶ѧ��
			List<User> klassUsers = new ArrayList<>();
			//���һ���༶����
			if(i+1 == classCnt){
				personCnt = count - (personCnt * i);
			}
			klassUsers = subList(users, begin, personCnt);
			splitClass.get(i).setUsers(klassUsers);
			begin = begin+personCnt;
		}
	}
	
	public static List<KlassExt> splitClass(Long subId,int classCnt,int level,String baseName){
		List<KlassExt> klasses = new ArrayList<>();
		if(classCnt <= 0 ) return klasses;
		if(level == 1){
			baseName = baseName.replaceAll("level", "רҵ");
		}else if (level == 2){
			baseName = baseName.replaceAll("level", "���");
		}else if (level == 3){
			baseName = baseName.replaceAll("level", "����");
		}else{
			baseName = baseName.replaceAll("level", "");
		}
		for(int i = 1;i<= classCnt;i++){
			Klass klass = new Klass();
			klass.setClassName(baseName.replaceAll("index", "" + i));
			KlassExt ext = new KlassExt();
			ext.setKlass(klass);
			ext.setSubId(subId);
			ext.setLevel(level);
			klasses.add(ext);
		}
		return klasses;
	}

	//����ѧ������
	public static void setUserScore(User user){
		user.setScore1(getRandomScore(-1));
		user.setScore2(getRandomScore(user.getScore1()));
		user.setScore3(getRandomScore(user.getScore1()));
		user.setScore4(getRandomScore(user.getScore1()));
		user.setScore5(getRandomScore(user.getScore1()));
		user.setScore6(getRandomScore(user.getScore1()));
		user.setScore7(getRandomScore(user.getScore1()));
	}
	
	public static void printClass(Map<String,String[][]> map){
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String[][] classes = map.get(key);
			for(int i=0;i<classes.length;i++){
				System.out.println(key+"{"+"�༶����:"+classes[i][0]+",ѧ������:"+classes[i][1]+"}");
			}
		}
	}
	
	public static Map<Long,List<Teacher>> getSubTeachers(){
		Map<Long,List<Teacher>> subTeachers = new HashMap<>();
		String sql = "select teacherId,userName,subjectId from tutor.tutor_user_teacher_subject_grade a left join tutor.tutor_user b on a.teacherId=b.userId where a.gradeId=11 and a.isDeleted=5 order by subjectId,teacherId";
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
	
	public static void setRule(){
		String sql = "insert into ls_paike_rule(paikeTaskId,ruleType,dayIndex,timeStage,lessonIndex,subId,classId,areaId,teacherId,level)"
					+ " values(999,?,?,?,?,?,?,?,?,?)";
		DBConstants[] types = new DBConstants[9];
		types[0] = DBConstants.SET_INT;
		types[1] = DBConstants.SET_INT;
		types[2] = DBConstants.SET_INT;
		types[3] = DBConstants.SET_INT;
		types[4] = DBConstants.SET_LONG;
		types[5] = DBConstants.SET_LONG;
		types[6] = DBConstants.SET_LONG;
		types[7] = DBConstants.SET_LONG;
		types[8] = DBConstants.SET_INT;
		
		Object[][] objs = new Object[7][9];
		//��ֹ�ſ�10ͨ��
		for(int i=0;i<4;i++){
			objs[i][0] = 10;
			objs[i][1] = 5;
			objs[i][2] = 2;
			objs[i][3] = i+1;
		}
		//Ԥ�ſΣ�������ʦ
		//��һ�ܶ�����
		//���Ŀ�ʱ
		for(int i=0;i<3;i++){
			objs[i+4][0] = 20;
			objs[i+4][1] = 1 + i;
			objs[i+4][2] = 2;
			objs[i+4][3] = 4;
			objs[i+4][4] = 29L;
			if(i==0){
				objs[i+4][5] = 3736L;
			}else if(i==1){
				objs[i+4][5] = 3742L;
			}else{
				objs[i+4][5] = 3738L;
			}
			objs[i+4][7] = 37496L + i;
		}
		lessonDB.updateBatch(sql, types, objs);
		sql = "insert into ls_paike_merge_rule(paikeTaskId,subId,classId,teacherId,parentId) values(999,29,?,?,?)";
		types = new DBConstants[3];
		Object[] obj = new Object[3];
		types[0] = DBConstants.SET_LONG;
		types[1] = DBConstants.SET_LONG;
		types[2] = DBConstants.SET_LONG;
		//�ϰ࣬����
		//������ʦ�ϰ�
		//147|258|369��ֱ�ϰ�/10���Լ���
		obj[0] = 3736L;//3742 //3738
		obj[1] = 37496L;//3740 //3737 //3744
//		obj[2] = 3736L;//3735 //3741 //3739
		Long parentId = lessonDB.updateReturnId(sql, types, obj);
		obj[0] = 3742L;
		obj[1] = 37496+1L;
		obj[2] = parentId;
		lessonDB.update(sql, types, obj);
		obj[0] = 3738L;
		obj[1] = 37496+2L;
		obj[2] = parentId;
		lessonDB.update(sql, types, obj);
		lessonDB.update("update ls_paike_merge_rule set parentId = mergeRuleId where mergeRuleId="+parentId, null, null);
		
		obj[0] = 3740L;//3742 //3738
		obj[1] = 37496L;//3740 //3737 //3744
//		obj[2] = 3736L;//3735 //3741 //3739
		parentId = lessonDB.updateReturnId(sql, types, obj);
		obj[0] = 3737L;
		obj[1] = 37496+1L;
		obj[2] = parentId;
		lessonDB.update(sql, types, obj);
		obj[0] = 3744L;
		obj[1] = 37496+2L;
		obj[2] = parentId;
		lessonDB.update(sql, types, obj);
		lessonDB.update("update ls_paike_merge_rule set parentId = mergeRuleId where mergeRuleId="+parentId, null, null);
		
		obj[0] = 3735L;//3742 //3738
		obj[1] = 37496L;//3740 //3737 //3744
//		obj[2] = 3736L;//3735 //3741 //3739
		parentId = lessonDB.updateReturnId(sql, types, obj);
		obj[0] = 3741L;
		obj[1] = 37496+1L;
		obj[2] = parentId;
		lessonDB.update(sql, types, obj);
		obj[0] = 3739L;
		obj[1] = 37496+2L;
		obj[2] = parentId;
		lessonDB.update(sql, types, obj);
		lessonDB.update("update ls_paike_merge_rule set parentId = mergeRuleId where mergeRuleId="+parentId, null, null);
		
	}
	
	public static Teacher getTeacherBySub(Map<Long,List<Teacher>> subTeachers,Long subId){
		List<Teacher> teachers = subTeachers.get(subId);
		for(Teacher teacher : teachers){
			if(teacher.cnt > 0){
				teacher.cnt --;
				return teacher;
			}
		}
		return null;
	}
	
	/**
	 * ��ȡ�����б�
	 * @return
	 */
	public static List<Area> getAreas(){
		List<Area> areas = new ArrayList<Area>();
		String sql = "select areaId,areaName,building from ls_teach_area where isDeleted=5 order by areaId";
		List<Map<String,Object>> result = lessonDB.select(sql, null, null);
		for(Map<String,Object> map : result){
			Area area = new Area();
			area.setAreaId(Long.parseLong(map.get("AREAID").toString()));
			area.setAreaName(map.get("AREANAME").toString());
			area.setBuilding(map.get("BUILDING").toString());
			areas.add(area);
		}
		return areas;
	}
	
	/**
	 * ��ȡ�����ڿμƻ��еķֲ�࣬�޳�ѡ�ްࡣ
	 * @return
	 */
	public static List<Klass> getTeachPlanClass(){
		List<Klass> klasses = new ArrayList<>();
		String sql = "select a.classId,a.className,a.gradeId,a.gradeName from ls_klass a ,ls_teach_plan b"
				+ " where a.classId=b.classId and b.paikeTaskId=999 and a.`type`=3 and b.subId<>-1 order by classId";
		List<Map<String,Object>> result = lessonDB.select(sql, null, null);
		for(Map<String,Object> map : result){
			Klass klass = new Klass();
			klass.setClassId(Long.parseLong(map.get("CLASSID").toString()));
			klass.setClassName(map.get("CLASSNAME").toString());
			klass.setGradeId(Long.parseLong(map.get("GRADEID").toString()));
			klass.setGradeName(map.get("GRADENAME").toString());
			klasses.add(klass);
		}
		return klasses;
	}
	
	/**
	 * ���а༶��ѧ���б�
	 * ��ѧ����������Ի�ȡ��ͻ�����б�
	 * @return
	 */
	public static Map<Long,List<Long>> getUsersByClasses(List<Long> classIds){
		StringBuilder sb = new StringBuilder();
		Map<Long,List<Long>> except = new HashMap<>();
		for(Long classId : classIds){
			sb.append(",").append(classId);
			except.put(classId,new ArrayList<Long>());
		}
		String sql = "select classId,userId,userName from ls_klass_student where classId in ("+sb.substring(1)+" ) order by userId";
		List<Map<String,Object>> result = lessonDB.select(sql, null, null);
		Long oldUserId = -1L;
		List<Long> exceptList = new ArrayList<>();
		for(Map<String,Object> map : result){
			Long nowUserId = Long.parseLong(map.get("USERID").toString());
			Long userClassId = Long.parseLong(map.get("CLASSID").toString());
			if(oldUserId == -1L) oldUserId = nowUserId;
			if(!nowUserId.equals(oldUserId)){
				oldUserId = nowUserId;
				for(Long classId : exceptList){
					addAllNoRepeat(except.get(classId),exceptList);
				}
				exceptList = new ArrayList<>();
			}
			exceptList.add(userClassId);
		}
		for(Long classId : exceptList){
			addAllNoRepeat(except.get(classId),exceptList);
		}
		return except;
	}
	
	public static List<Subject> getSubjects(){
		List<Subject> subjects = new ArrayList<Subject>();
		Subject subject = new Subject();
		subject.setSubjectId(1L);
		subject.setSubjectName("����");
		subjects.add(subject);
		subject = new Subject();
		subject.setSubjectId(2L);
		subject.setSubjectName("��ѧ");
		subjects.add(subject);
		subject = new Subject();
		subject.setSubjectId(3L);
		subject.setSubjectName("����");
		subjects.add(subject);
		subject = new Subject();
		subject.setSubjectId(29L);
		subject.setSubjectName("����");
		subjects.add(subject);
		return subjects;
	}
	
	public static String getSubName(Long subNo){
		if(subNo == 1){
			return "����";
		}else if(subNo == 2){
			return "��ѧ";
		}else if(subNo == 3){
			return "����";
		}else if(subNo == 4){
			return "����";
		}else if(subNo == 5){
			return "��ѧ";
		}else if(subNo == 6){
			return "����";
		}else if(subNo == 7){
			return "����";
		}else if(subNo == 8){
			return "��ʷ";
		}else if(subNo == 9){
			return "����";
		}else if(subNo == 19){
			return "����";
		}else if(subNo == 29){
			return "����";
		}else{
			return "����";
		}
	}
	
	public static Long getSubNo(String name){
		if(name.indexOf("����") > -1){
			return 1L;
		}else if(name.indexOf("��ѧ") > -1){
			return 2L;
		}else if(name.indexOf("����") > -1){
			return 3L;
		}else if(name.indexOf("����") > -1){
			return 4L;
		}else if(name.indexOf("��ѧ") > -1){
			return 5L;
		}else if(name.indexOf("����") > -1){
			return 6L;
		}else if(name.indexOf("����") > -1){
			return 7L;
		}else if(name.indexOf("��ʷ") > -1){
			return 8L;
		}else if(name.indexOf("����") > -1){
			return 9L;
		}else if(name.indexOf("����") > -1){
			return 19L;
		}else if(name.indexOf("����") > -1){
			return 29L;
		}else{
			return -1L;
		}
	}
	
	public static Long getClassId(Long relationId){
		if(relationId == 4256){
			return 3735L;
		}else if(relationId == 4254){
			return 3736L;
		}else if(relationId == 4258){
			return 3737L;
		}else if(relationId == 4260){
			return 3738L;
		}else if(relationId == 4262){
			return 3739L;
		}else if(relationId == 4255){
			return 3740L;
		}else if(relationId == 4259){
			return 3741L;
		}else if(relationId == 4257){
			return 3742L;
		}else if(relationId == 4263){
			return 3743L;
		}else if(relationId == 4261){
			return 3744L;
		}else{
			return -1L;
		}
	}
	
	public static double getRandomScore(double score){
		Random random = new Random();
		if(score <= 60){
			return random.nextInt(101);
		}else if(score <= 80){
			return 60 + random.nextInt(20);
		}else{
			return 80 + random.nextInt(20);
		}
	}
	
	public static <T> List<T> subList(List<T> list , int offset , int size){
		List<T> result = new ArrayList<>();
		for(int i=0;i<size;i++){
			result.add(list.get(i+offset));
		}
		return result;
	}
	
	public static <T> void addAllNoRepeat(List<T> list , List<T> list2){
		for(T t : list2){
			if(!list.contains(t)){
				list.add(t);
			}
		}
	}
	
	//����
	public static void reset(){
//		String sql = "delete from ls_klass where isDeleted=5";
//		lessonDB.update(sql, null, null);
//		sql = "delete from ls_klass_student where isDeleted=5";
//		lessonDB.update(sql, null, null);
//		sql = "delete from ls_choice_exam_task_student where isDeleted=5";
//		lessonDB.update(sql, null, null);
//		sql = "delete from ls_teach_area where isDeleted=5";
//		lessonDB.update(sql, null, null);
//		sql = "delete from tutor_user where isDeleted=5";
//		tutorDB.update(sql, null, null);
//		sql = "delete from tutor_user_role_school where isDeleted=5";
//		tutorDB.update(sql, null, null);
//		sql = "delete from tutor_user_struct where isDeleted=5";
//		tutorDB.update(sql, null, null);
//		sql = "delete from tutor_user_teacher_subject_grade where isDeleted=5";
//		tutorDB.update(sql, null, null);
	}
	
	//FIXME
	public static void main(String[] args) {
//		reset();
		//����user��
//		List<User> users = getRandomUser(487);
//		List<User> teachers = getRandomTeacher();
		
//		List<User> users = getUser();
//		List<User> teachers = getTeacher();
		
		//����struct��role_school
//		setUserStructAndRole(users,100L);
//		setUserStructAndRole(teachers,101L);
//		setUserClass(users);
		
		//����tutor_user_teacher_subject_grade
//		setTeacherSub(getTeacher());
		//����ls_choice_exam_task_student��
//		setRandomSubInfo(users, 999L);
		//��ʼ������
//		initArea();
		
		//���ð༶
//		List<KlassExt> klasses = assemble(users, subIds);
//		splitClass(users,klasses);
//		updateClassSub();
		

//		initTeachPlanAndArea();
		
		//����Ԥ�ſΣ����ſΣ����úϰ�
//		setRule();
		
		long begin = System.currentTimeMillis();
		List<Klass> klasses = getTeachPlanClass();
		List<Long> classIds = new ArrayList<>();
		for(Klass klass : klasses){
			classIds.add(klass.getClassId());
		}
		Map<Long,List<Long>> exceptClass = getUsersByClasses(classIds);
		//419ms
		System.out.println(System.currentTimeMillis() - begin + "ms");
		Print.printMapList(exceptClass);
		
//		users.forEach(System.out::println);
//		for(int i = 0 ; i< 7 ; i++){
//			System.out.println("-----------------------------------------------"+i);
//			subList.get(i).sort(new SortUser(i+1L));
//			subList.get(i).forEach(System.out::println);
//			System.out.println("subcheck [sub:"+getSubName(i+1L)+", cnt:"+subList.get(i).size()+"]");
//			printClass(assemble("�߶�",i+1L,getSubName(i+1L),users.size(),subList.get(i).size()));
//		}
	}
	
	static class SortUser implements Comparator<User>{
		Long subNo = 0L;
		public SortUser(Long subNo){
			this.subNo = subNo;
		}

		@Override
		public int compare(User u1, User u2) {
			double result = 0 ;
			if(subNo == 4L){
				result = u2.getScore1() - u1.getScore1();
			}else if(subNo == 5L){
				result = u2.getScore2() - u1.getScore2();
			}else if(subNo == 6L){
				result = u2.getScore3() - u1.getScore3();
			}else if(subNo == 7L){
				result = u2.getScore4() - u1.getScore4();
			}else if(subNo == 8L){
				result = u2.getScore5() - u1.getScore5();
			}else if(subNo == 9L){
				result = u2.getScore6() - u1.getScore6();
			}else if(subNo == 19L){
				result = u2.getScore7() - u1.getScore7();
			}
			return result > 0 ? 1 : -1;
		}
		
	}
}

package com.renke.mq.rabbit.consumer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseSingleRemote implements Serializable {

	private static final long serialVersionUID = 876969371833350594L;

	/**
	 * Description: 单课主键
	 */
	private Long courseSingleId;

	/**
	 * Description: 单课名称
	 */
	private String courseSingleName;

	/**
	 * Description: 单课开始时间
	 */
	private Date startTime;

	/**
	 * Description: 单课结束时间
	 */
	private Date endTime;

	/**
	 * Description: 单课课时
	 */
	private Integer count;

	/**
	 * Description: 主键
	 */
	private Long courseSetId;

	/**
	 * Description: 名称
	 */
	private String courseSetName;

	/**
	 * Description: 课程主键
	 */
	private Long courseId;

	/**
	 * Description: 课程名称
	 */
	private String courseName;

	private Long subjectId;

	private Long gradeId;

	private String subjectName;

	private String gradeName;

	private Long schoolStageId;

	private String schoolStageName;

	private Long teacherId;

	private String teacherName;

	private Long schoolId;

	private Boolean isEnd;

	/**
	 * 备课状态
	 */
	private Boolean isPrepared;
	/**
	 * 课件数量
	 */
	private Integer cwCount;
	/**
	 * 微课数量
	 */
	private Integer mccwCount;
	/**
	 * 作业数量
	 */
	private Integer hwCount;

	public Long getCourseSingleId() {
		return courseSingleId;
	}

	public void setCourseSingleId(Long courseSingleId) {
		this.courseSingleId = courseSingleId;
	}

	public String getCourseSingleName() {
		return courseSingleName;
	}

	public void setCourseSingleName(String courseSingleName) {
		this.courseSingleName = courseSingleName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public String getStartTimeStr() {
		if (startTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(startTime);
		} else {
			return null;
		}
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getEndTimeStr() {
		if (endTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(endTime);
		} else {
			return null;
		}
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the courseSetId
	 */
	public Long getCourseSetId() {
		return courseSetId;
	}

	/**
	 * @param courseSetId
	 *            the courseSetId to set
	 */
	public void setCourseSetId(Long courseSetId) {
		this.courseSetId = courseSetId;
	}

	/**
	 * @return the courseSetName
	 */
	public String getCourseSetName() {
		return courseSetName;
	}

	/**
	 * @param courseSetName
	 *            the courseSetName to set
	 */
	public void setCourseSetName(String courseSetName) {
		this.courseSetName = courseSetName;
	}

	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * @param subjectName
	 *            the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * @return the gradeName
	 */
	public String getGradeName() {
		return gradeName;
	}

	/**
	 * @param gradeName
	 *            the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	/**
	 * @return the teacherId
	 */
	public Long getTeacherId() {
		return teacherId;
	}

	/**
	 * @param teacherId
	 *            the teacherId to set
	 */
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	/**
	 * @return the schoolId
	 */
	public Long getSchoolId() {
		return schoolId;
	}

	/**
	 * @param schoolId
	 *            the schoolId to set
	 */
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * @return the isEnd
	 */
	public Boolean getIsEnd() {
		return isEnd;
	}

	/**
	 * @param isEnd
	 *            the isEnd to set
	 */
	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}

	/**
	 * @return the subjectId
	 */
	public Long getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the gradeId
	 */
	public Long getGradeId() {
		return gradeId;
	}

	/**
	 * @param gradeId
	 *            the gradeId to set
	 */
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	/**
	 * @return the isPrepared
	 */
	public Boolean getIsPrepared() {
		return isPrepared;
	}

	/**
	 * @param isPrepared
	 *            the isPrepared to set
	 */
	public void setIsPrepared(Boolean isPrepared) {
		this.isPrepared = isPrepared;
	}

	/**
	 * @return the cwCount
	 */
	public Integer getCwCount() {
		return cwCount;
	}

	/**
	 * @param cwCount
	 *            the cwCount to set
	 */
	public void setCwCount(Integer cwCount) {
		this.cwCount = cwCount;
	}

	/**
	 * @return the mccwCount
	 */
	public Integer getMccwCount() {
		return mccwCount;
	}

	/**
	 * @param mccwCount
	 *            the mccwCount to set
	 */
	public void setMccwCount(Integer mccwCount) {
		this.mccwCount = mccwCount;
	}

	/**
	 * @return the hwCount
	 */
	public Integer getHwCount() {
		return hwCount;
	}

	/**
	 * @param hwCount
	 *            the hwCount to set
	 */
	public void setHwCount(Integer hwCount) {
		this.hwCount = hwCount;
	}

	public Long getSchoolStageId() {
		return schoolStageId;
	}

	public void setSchoolStageId(Long schoolStageId) {
		this.schoolStageId = schoolStageId;
	}

	public String getSchoolStageName() {
		return schoolStageName;
	}

	public void setSchoolStageName(String schoolStageName) {
		this.schoolStageName = schoolStageName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}

/**
 * 
 */
package com.sjsu.group1.StudentCourseRegistration.model;

import java.sql.Date;
import java.sql.Time;

/**
 * @author pavanibaradi
 *
 */
public class Schedule {
	private int scheduleId;
	private Date startDate;
	private Date endDate;
	private Time startTime;
	private Time endTime;
	/**
	 * @param startDate
	 * @param endDate
	 * @param startTime
	 * @param endTime
	 */
	public Schedule(Date startDate, Date endDate, Time startTime, Time endTime) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	/**
	 * @return the scheduleId
	 */
	public int getScheduleId() {
		return scheduleId;
	}
	/**
	 * @param scheduleId the scheduleId to set
	 */
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the startTime
	 */
	public Time getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public Time getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

}

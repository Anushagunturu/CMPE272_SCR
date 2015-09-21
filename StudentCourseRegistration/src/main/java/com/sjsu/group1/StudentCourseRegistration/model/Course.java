/**
 * 
 */
package com.sjsu.group1.StudentCourseRegistration.model;

import java.util.List;

/**
 * @author pavanibaradi
 *
 */
public class Course {
	private int courseID;
	private String courseName;
	private int amount;
	private List<Schedule> scheduleList;
	private List<Book> bookList;
	private List<Curriculum> curriculumList;
	
	/**
	 * @param courseName
	 * @param amount
	 */
	public Course(String courseName, int amount) {
		super();
		this.courseName = courseName;
		this.amount = amount;
	}
	/**
	 * @param courseName
	 * @param amount
	 * @param scheduleList
	 * @param bookList
	 * @param curriculumList
	 */
	public Course(String courseName, int amount, List<Schedule> scheduleList, List<Book> bookList,
			List<Curriculum> curriculumList) {
		super();
		this.courseName = courseName;
		this.amount = amount;
		this.scheduleList = scheduleList;
		this.bookList = bookList;
		this.curriculumList = curriculumList;
	}
	/**
	 * @return the scheduleList
	 */
	public List<Schedule> getScheduleList() {
		return scheduleList;
	}
	/**
	 * @param scheduleList the scheduleList to set
	 */
	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}
	/**
	 * @return the bookList
	 */
	public List<Book> getBookList() {
		return bookList;
	}
	/**
	 * @param bookList the bookList to set
	 */
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	/**
	 * @return the curriculumList
	 */
	public List<Curriculum> getCurriculumList() {
		return curriculumList;
	}
	/**
	 * @param curriculumList the curriculumList to set
	 */
	public void setCurriculumList(List<Curriculum> curriculumList) {
		this.curriculumList = curriculumList;
	}
	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}
	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	

}

/**
 * 
 */
package com.sjsu.group1.StudentCourseRegistration.model;

/**
 * @author pavanibaradi
 *
 */
public class Course {
	private int courseID;
	private String courseName;
	private int amount;
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

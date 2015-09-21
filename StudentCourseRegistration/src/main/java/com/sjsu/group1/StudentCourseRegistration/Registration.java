/**
 * 
 */
package com.sjsu.group1.StudentCourseRegistration;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.sjsu.group1.StudentCourseRegistration.model.Book;
import com.sjsu.group1.StudentCourseRegistration.model.Course;
import com.sjsu.group1.StudentCourseRegistration.model.Curriculum;
import com.sjsu.group1.StudentCourseRegistration.model.Schedule;

/**
 * @author pavanibaradi
 *
 */
public class Registration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.util.Date utilStartDate = new java.util.Date("2015/10/20");
	    java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
	    java.util.Date utilEndDate = new java.util.Date("2015/11/20");
	    java.sql.Date sqlEndDate = new java.sql.Date(utilStartDate.getTime());
		StudentCourseController controller = new StudentCourseController();
		Schedule schedule = new Schedule(sqlStartDate, sqlEndDate, new Time(9, 0, 0), new Time(11, 0, 0));
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		scheduleList.add(schedule);
		Curriculum curriculum = new Curriculum("Data Structures");
		List<Curriculum> curriculumList = new ArrayList<Curriculum>();
		curriculumList.add(curriculum);
		Book book = new com.sjsu.group1.StudentCourseRegistration.model.Book("Python Cookbook");
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book);
		Course course = new Course("Python", 250, scheduleList, bookList, curriculumList);
		controller.addCourses(course);
		System.out.println("Course Succesfully added ");
	}
	

}

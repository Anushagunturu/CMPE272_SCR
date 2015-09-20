/**
 * 
 */
package com.sjsu.group1.StudentCourseRegistration;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.sql.Date;

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
		// TODO Auto-generated method stub
		StudentCourseController controller = new StudentCourseController();
		Schedule schedule = new Schedule(new Date(2015, 9, 18), new Date(2015, 10, 18), new Time(9, 0, 0), new Time(11, 0, 0));
		controller.addScheduleForCourse("Java", schedule);
		System.out.println();
	}
	

}

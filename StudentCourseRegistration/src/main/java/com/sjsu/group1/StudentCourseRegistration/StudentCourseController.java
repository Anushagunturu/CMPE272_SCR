/**
 * 
 */
package com.sjsu.group1.StudentCourseRegistration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import com.sjsu.group1.StudentCourseRegistration.model.Course;
import com.sjsu.group1.StudentCourseRegistration.model.Schedule;
import com.sjsu.group1.StudentCourseRegistration.model.Student;

/**
 * @author pavanibaradi
 *
 */
public class StudentCourseController {

	private Properties properties = new Properties();
	private DatabaseAccess dbAccess = DatabaseAccess.getConnection();
	private String query;
	private int result;
	
	public StudentCourseController() {
		// TODO Auto-generated constructor stub
		try {
			properties.load(new FileInputStream("conf.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * This method Registers Student
	 * @param Student object that should be persisted
	 */
	public void registerStudent(Student student) {

		try {
			student = dbAccess.addStudent(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			dbAccess.close();
		}

	}
	
	public void addCourses(Course course){
		try{
			course = dbAccess.addCourse(course);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbAccess.close();
		}
	}
	
	public void addScheduleForCourse(String courseName, Schedule schedule){
		int scheduleId=0;
		//String courseName=course.getCourseName();;
		try {
			schedule = dbAccess.addSchedule(schedule);
			scheduleId=schedule.getScheduleId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getSQLState().equals("23505")){
				try {
					 scheduleId = dbAccess.getScheduleID(schedule);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		try {
			if(scheduleId!=0){
				dbAccess.addScheduleForCourse(courseName,scheduleId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getErrorCode() == 23505){
				try {
					 dbAccess.updateStudentCourseScheduleCount();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}

}

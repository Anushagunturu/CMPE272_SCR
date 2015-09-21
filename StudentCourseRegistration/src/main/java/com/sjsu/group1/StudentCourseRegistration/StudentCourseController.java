/**
 * 
 */
package com.sjsu.group1.StudentCourseRegistration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.sjsu.group1.StudentCourseRegistration.model.Book;
import com.sjsu.group1.StudentCourseRegistration.model.Course;
import com.sjsu.group1.StudentCourseRegistration.model.Curriculum;
import com.sjsu.group1.StudentCourseRegistration.model.Schedule;
import com.sjsu.group1.StudentCourseRegistration.model.Student;

/**
 * @author pavanibaradi
 *
 */
public class StudentCourseController {

	private Properties properties = new Properties();
	private DatabaseAccess dbAccess = DatabaseAccess.getConnection();

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

	/*
	 * This method adds course and its schedule 
	 * books related to course and course curriculum
	 * @param Course that needs to be added
	 */
	public void addCourses(Course course){
		try{
			course = dbAccess.addCourse(course);
			addScheduleForCourse(course);
			addCurriculumForCourse(course);
			addBooksForCourse(course);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbAccess.close();
		}
	}

	public void addScheduleForCourse(Course course){
		String courseName=course.getCourseName();
		List<Schedule> scheduleList = course.getScheduleList();
		for (Iterator<Schedule> iterator = scheduleList.iterator(); iterator.hasNext();) {
			Schedule schedule = (Schedule) iterator.next();
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

	public void addCurriculumForCourse(Course course){
		String courseName=course.getCourseName();
		List<Curriculum> curriculumList = course.getCurriculumList();
		for (Iterator<Curriculum> iterator = curriculumList.iterator(); iterator.hasNext();) {
			Curriculum curriculum = (Curriculum) iterator.next();
			int curriculumId=0;
			//String courseName=course.getCourseName();;
			try {
				curriculum = dbAccess.addCurriculum(curriculum);
				curriculumId=curriculum.getCurriculumId();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(e.getSQLState().equals("23505")){
					try {
						curriculumId = dbAccess.getCurriculumID(curriculum);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
			try {
				if(curriculumId!=0){
					dbAccess.addCurriculumForCourse(courseName,curriculumId);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(e.getSQLState().equals("23505"))
					System.out.println("Curriculum already added to course");
			}
		}
	}

	public void addBooksForCourse(Course course){
		String courseName=course.getCourseName();
		List<Book> bookList = course.getBookList();
		for (Iterator<Book> iterator = bookList.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();

			int bookId=0;
			//String courseName=course.getCourseName();;
			try {
				book = dbAccess.addBook(book);
				bookId=book.getBookID();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(e.getSQLState().equals("23505")){
					try {
						bookId = dbAccess.getBookID(book);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
			try {
				if(bookId!=0){
					dbAccess.addBookForCourse(courseName,bookId);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(e.getSQLState().equals("23505"))
					System.out.println("Book already added to course");
			}
		}
	}
}

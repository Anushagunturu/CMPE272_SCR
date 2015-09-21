/**
 * 
 */
package com.sjsu.group1.StudentCourseRegistration;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public final class DatabaseAccess {

	private Connection connection = null;
	private Properties properties = new Properties();
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private int result;
	public static DatabaseAccess dbAcccess;

	private DatabaseAccess() {
		try {
			// Load Properties file
			properties.load(new FileInputStream("conf.properties"));
			Class.forName("org.postgresql.Driver");

			System.out.println("Connecting to Database...");

			// Get Connection to database
			this.connection = DriverManager.getConnection(properties.getProperty("db_url"),
					properties.getProperty("db_username"), properties.getProperty("db_password"));

			System.out.println("Connection successfull");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @return get DatabaseAccess Database connection object
	 */
	public static synchronized DatabaseAccess getConnection() {
		if (dbAcccess == null) {
			dbAcccess = new DatabaseAccess();
		}
		return dbAcccess;

	}

	/**
	 *
	 * @param query
	 *            String The query to be executed
	 * @return a ResultSet object containing the results or null if not
	 *         available
	 * @throws SQLException
	 */
	public ResultSet query(String query) throws SQLException {
		preparedStatement = dbAcccess.connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery(query);
		return resultSet;
	}

	/**
	 * Method to insert data to a table
	 * 
	 * @param insertQuery
	 *            String The Insert query
	 * @return boolean
	 * @throws SQLException
	 */
	public int insert(String insertQuery) throws SQLException {
		preparedStatement = dbAcccess.connection.prepareStatement(insertQuery);
		result = preparedStatement.executeUpdate(insertQuery);
		return result;

	}

	public void getCourses() throws SQLException {
		System.out.println("Listing Courses..");
		preparedStatement = connection.prepareStatement(properties.getProperty("list.courses"));
		resultSet = preparedStatement.executeQuery();
		System.out.println("Courses " + resultSet.getMetaData());
	}

	public void getStudents() throws SQLException {
		System.out.println("Listing Studnets..");
		preparedStatement = connection.prepareStatement(properties.getProperty("list.students"));
		resultSet = preparedStatement.executeQuery();
		System.out.println("Students " + resultSet.getMetaData());
	}

	public void getCourseID() throws SQLException {
		System.out.println("Listing Courses by ID..");
		preparedStatement = connection.prepareStatement(properties.getProperty("get.courseid.by.name"));
		resultSet = preparedStatement.executeQuery();
		System.out.println("Courses " + resultSet.getMetaData());
	}

	public void getStudentID() throws SQLException {
		System.out.println("Listing Students by ID..");
		preparedStatement = connection.prepareStatement(properties.getProperty("get.studentid.by.name"));
		resultSet = preparedStatement.executeQuery();
		System.out.println("Student " + resultSet.getMetaData());
	}

	public Student addStudent(Student student) throws SQLException {
		System.out.println("Add Student...");
		preparedStatement = connection.prepareStatement(properties.getProperty("register.student"),
				PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, student.getFirstName());
		preparedStatement.setString(2, student.getLastName());
		preparedStatement.setString(3, student.getEmail());
		preparedStatement.setString(4, student.getPassword());
		System.out.println(preparedStatement.toString());
		result = preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		if (resultSet.next())
			student.setStudentId(resultSet.getInt("student_id"));
		System.out.println("Student Added " + result);
		return student;
	}

	public Course addCourse(Course course) throws SQLException {
		System.out.println("Add Course...");
		preparedStatement = connection.prepareStatement(properties.getProperty("add.courses"),
				PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, course.getCourseName());
		preparedStatement.setInt(2, course.getAmount());
		System.out.println(preparedStatement.toString());
		result = preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		if (resultSet != null && resultSet.next())
			course.setCourseID(resultSet.getInt("course_id"));
		System.out.println("Course Added " + course.toString());
		return course;
	}

	public Book addBook(Book book) throws SQLException {
		System.out.println("Add Book...");
		preparedStatement = connection.prepareStatement(properties.getProperty("add.books"),
				PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, book.getBookName());
		System.out.println(preparedStatement.toString());
		result = preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		if (resultSet.next())
			book.setBookID(resultSet.getInt("book_id"));
		System.out.println("Book Added " + result);
		return book;
	}

	public Curriculum addCurriculum(Curriculum curriculum) throws SQLException {
		System.out.println("Add Curriculum...");
		preparedStatement = connection.prepareStatement(properties.getProperty("add.curriculum"),
				PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, curriculum.getTopicName());
		System.out.println(preparedStatement.toString());
		result = preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		if (resultSet.next())
			curriculum.setCurriculumId(resultSet.getInt("curriculum_id"));
		System.out.println("Curriculum Added " + result);
		return curriculum;
	}

	public Schedule addSchedule(Schedule schedule) throws SQLException {
		System.out.println("Add Schedule...");
		preparedStatement = connection.prepareStatement(properties.getProperty("add.schedule"),
				PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setDate(1, schedule.getStartDate());
		preparedStatement.setDate(2, schedule.getEndDate());
		preparedStatement.setTime(3, schedule.getStartTime());
		preparedStatement.setTime(4, schedule.getEndTime());
		System.out.println(preparedStatement.toString());
		result = preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		if (resultSet.next())
			schedule.setScheduleId(resultSet.getInt("schedule_id"));
		System.out.println("Schedule Added " + result);
		return schedule;
	}

	public int getScheduleID(Schedule schedule) throws SQLException {
		System.out.println("Getting Schedule ID..");
		preparedStatement = connection.prepareStatement(properties.getProperty("fetch.schedule.id"));
		preparedStatement.setDate(1, schedule.getStartDate());
		preparedStatement.setDate(2, schedule.getEndDate());
		preparedStatement.setTime(3, schedule.getStartTime());
		preparedStatement.setTime(4, schedule.getEndTime());
		resultSet = preparedStatement.executeQuery();
		if (null != resultSet && resultSet.next())
			result = resultSet.getInt(1);
		return result;
	}

	public void addScheduleForCourse(String courseName, int scheduleID) throws SQLException {
		preparedStatement = connection.prepareStatement(properties.getProperty("add.course.schedule"),
				PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, courseName);
		preparedStatement.setInt(2, scheduleID);
		System.out.println(preparedStatement.toString());
		result = preparedStatement.executeUpdate();
	}

	public void updateStudentCourseScheduleCount() throws SQLException {
		preparedStatement = connection
				.prepareStatement(properties.getProperty("increment.student.course.schedule.count"));
		System.out.println(preparedStatement.toString());
		result = preparedStatement.executeUpdate();
	}
	
	public int getCurriculumID(Curriculum curriculum) throws SQLException {
		System.out.println("Getting Schedule ID..");
		preparedStatement = connection.prepareStatement(properties.getProperty("fetch.curriculum.id"));
		preparedStatement.setString(1, curriculum.getTopicName());
		resultSet = preparedStatement.executeQuery();
		if (null != resultSet && resultSet.next())
			result = resultSet.getInt(1);
		return result;
	}
	
	public void addCurriculumForCourse(String courseName, int curriculumId) throws SQLException {
		preparedStatement = connection.prepareStatement(properties.getProperty("add.course.curriculum"),
				PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, courseName);
		preparedStatement.setInt(2, curriculumId);
		System.out.println(preparedStatement.toString());
		result = preparedStatement.executeUpdate();
	}
	
	public int getBookID(Book book) throws SQLException {
		System.out.println("Getting Schedule ID..");
		preparedStatement = connection.prepareStatement(properties.getProperty("fetch.book.id"));
		preparedStatement.setString(1, book.getBookName());
		resultSet = preparedStatement.executeQuery();
		if (null != resultSet && resultSet.next())
			result = resultSet.getInt(1);
		return result;
	}
	
	public void addBookForCourse(String courseName, int bookId) throws SQLException {
		preparedStatement = connection.prepareStatement(properties.getProperty("add.course.book"),
				PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, bookId);
		preparedStatement.setString(2, courseName);
		System.out.println(preparedStatement.toString());
		result = preparedStatement.executeUpdate();
	}

	/*
	 * Close the connections
	 */
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {

		}
	}

}

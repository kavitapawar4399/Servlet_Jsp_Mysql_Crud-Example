package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbConnect;
import model.Course;

public class CourseDao {
//for data accessing
	Connection conn;
	public CourseDao() throws ClassNotFoundException, SQLException{
		conn=DbConnect.getConnection();
	}
	
	public void addCourse(Course course) {
		try {
	System.out.println("before prepared statement");		
	PreparedStatement pst = conn.prepareStatement("insert into course(coursename,fees,category,duration)" + "values(?,?,?,?)");
		System.out.println("after prepared statement");
	    pst.setString(1, course.getCoursename());
		pst.setInt(2, course.getfees());
		pst.setString(3, course.getCategory());
		pst.setString(4, course.getDuration());
	
		pst.executeUpdate();
		
		
		
		}catch (SQLException e) {
		System.out.println("exception"+e);
			e.printStackTrace();
		}
}
	
	public List<Course>getAllCourses(){
		List<Course> courses=new ArrayList<Course>();
		try {
			Statement statement = conn.createStatement();
			
			
			ResultSet rs =statement.executeQuery("select * from course");
			while((rs).next()) {
				Course course = new Course();
				course.setCoursename((rs).getString("coursename"));
				course.setCourseid(rs.getInt("courseid"));
				course.setCategory(rs.getString("category"));
				course.setfees(rs.getInt("fees"));
				course.setDuration(rs.getString("duration"));
				
			courses.add(course);	
			}
		}
		
	catch(SQLException e) {
		e.printStackTrace();
	}
	return courses;
		
		
	}
	
	
	//for delete
	public void deleteCourse(int courseid) {
		try {			
		
		PreparedStatement pst = conn.prepareStatement("delete from course where courseid=?");
		pst.setInt(1,courseid);
		
		pst.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//for edit
	
	public Course getCourseByID(int courseid) {
		Course course= new Course();
		
		try {
			
			PreparedStatement pst = conn.prepareStatement("select *  from course where courseid=?");
			pst.setInt(1,courseid);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				course.setCoursename((rs).getString("coursename"));
				course.setCourseid(rs.getInt("courseid"));
				course.setCategory(rs.getString("category"));
				course.setfees(rs.getInt("fees"));
				course.setDuration(rs.getString("duration"));
				
			}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		return course;
	}
	
	
	
	//for update courses
	public void updateCourse(Course course) {
		try {
			PreparedStatement pst = conn.prepareStatement("update course set"
					+ "coursename=?, category=?, fees=?,duration=?" +"where courseid=?");
			    pst.setString(1, course.getCoursename());
				
				pst.setString(2, course.getCategory());
				pst.setInt(3, course.getfees());
				
				pst.setString(4, course.getDuration());
				pst.setInt(5, course.getCourseid());
			
				pst.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	}


package web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;

import java.io.IOException;
import java.sql.SQLException;

import Dao.CourseDao;


public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private CourseDao dao;
    public CourseServlet() throws ClassNotFoundException, SQLException {
        super();
        // TODO Auto-generated constructor stub
        
        dao = new CourseDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		String forward="";
		if(action==null) {
			action="default";
		}
		switch(action) {
		case "delete":
		//	System.out.print(action);
			int courseid = Integer.parseInt(request.getParameter("courseid"));
			dao.deleteCourse(courseid);
			
			RequestDispatcher del = request.getRequestDispatcher("view.jsp");
			request.setAttribute("courses", dao.getAllCourses());
			del.forward(request,response);
			break;
			
		case "edit":
			//System.out.print(action);
			
			RequestDispatcher edit = request.getRequestDispatcher("index.jsp");
			courseid = Integer.parseInt(request.getParameter("courseid"));
			Course course = dao.getCourseByID(courseid);
			request.setAttribute("courses", course);
			edit.forward(request, response);
			break;
	
			
			default :
			
		
		RequestDispatcher view = request.getRequestDispatcher("view.jsp");
		request.setAttribute("courses", dao.getAllCourses());
		view.forward(request, response);
		break;
	}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	//	System.out.println("inside do post");
	Course	course = new Course();
	//System.out.println("coursename"+request.getParameter("coursename"));
	course.setCoursename(request.getParameter("coursename"));
	
	course.setfees(Integer.parseInt(request.getParameter("fees")));
	
	course.setCategory(request.getParameter("category"));
	course.setDuration(request.getParameter("duration"));

	String courseid = request.getParameter("courseid");
	
	if(courseid==null  || courseid.isEmpty()) {
	
	dao.addCourse(course);
	}
	else {
		
	  course.setCourseid(Integer.parseInt(courseid));
	  dao.updateCourse(course);
	  
	  RequestDispatcher list = request.getRequestDispatcher("view.jsp");
		request.setAttribute("courses", dao.getAllCourses());
		list.forward(request, response);
	}
	//response.sendRedirect("success.jsp");
	}

}

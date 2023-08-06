//data transfer object
package model;

public class Course {
private int courseid;
private String coursename;
private int fees;
private String duration;
private String category;

public int getCourseid() {
	return courseid;
}
public void setCourseid(int courseid) {
	this.courseid = courseid;
}
public String getCoursename() {
	return coursename;
}
public void setCoursename(String coursename) {
	this.coursename = coursename;
}
public int getfees() {
	return fees;
}
public void setfees(int fees) {
	this.fees = fees;
}
public String getDuration() {
	return duration;
}
public void setDuration(String duration) {
	this.duration = duration;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
@Override
public String toString() {
	return "Course [courseid=" + courseid + ", coursename=" + coursename + ", duration=" + duration + ", category="
			+ category + "]";
}
}

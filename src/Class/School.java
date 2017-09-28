package Class;

import java.io.Serializable;
import java.util.*;
/**
 * Represent a school
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class School implements Serializable {
	/**
	 * Unique identifier for a specific school
	 */
	private String schoolID;
	private String name;
	/**
	 * Start date of the access period
	 */
	private Date startDate;
	/**
	 * End date of the access period
	 */
	private Date endDate;
	/**
	 * List of courses under a school
	 */
	private ArrayList<Course> course;
	/**
	 * List of students registered under the school
	 */
	private ArrayList<Student> student;

	/**
	 * Constructor to create a school
	 * @param schoolID
	 * @param name
	 */
	public School(String schoolID, String name) {
		this.schoolID = schoolID;
		this.name = name;
		this.endDate = null;
		this.course = null;
		this.student = null;

	}
	
	public School(String schoolID, String name, Date startDate, Date endDate, ArrayList<Course> course,
			ArrayList<Student> student) {
		this.schoolID = schoolID;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.course = course;
		this.student = student;
	}
	
	/**
	 * method to remove a specific student from the school
	 * @param student
	 */
	public void removeStudent(Student student){
		
		if(this.student != null){
			
			for (int i = 0 ; i < this.student.size(); i ++){
				
				if(this.student.get(i).getUsername().equals(student.getUsername())){
					
					this.student.remove(i);
				}
			}	
		}
	}
	
	/**
	 * method to add a student to the school
	 * @param student
	 */
	public void addStudent(Student student){
		
		if(this.student == null){
			this.student = new ArrayList<Student>();
			this.student.add(student);

		}
		else{
			this.student.add(student);
		}
	}
	/**
	 * method to add a course to the school
	 * @param cs
	 */
	public void addCourse(Course cs)
	{
		if(this.course == null)
		{
			this.course = new ArrayList<Course>();
			this.course.add(cs);
		}
		else{
			this.course.add(cs);
		}
	}
	/**
	 * method to remove a course from the school
	 * @param cs
	 */
	public void removeCourse(Course cs)
	{
		if(this.course != null){
			
			for (int i = 0 ; i < this.course.size(); i ++){
				if(this.course.get(i).getCourseID().equals(cs.getCourseID())){
					
					this.course.remove(i);
					course.removeAll(Collections.singleton(null));  
				}
			}	
		}
	}

	public String getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ArrayList<Course> getCourse() {
		return course;
	}

	public void setCourse(ArrayList<Course> course) {
		this.course = course;
	}

	public ArrayList<Student> getStudent() {
		return student;
	}

	public void setStudent(ArrayList<Student> student) {
		this.student = student;
	}


	

}

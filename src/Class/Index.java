package Class;

import java.io.Serializable;
import java.util.*;
/**
 * Each index represents a specific group under a course
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class Index implements Serializable {
	/**
	 * Unique identifier of a specific index
	 */
	private String indexID;

	/**
	 * Unique identifier of a course that the index belongs to
	 */
	private String  courseID;
	/**
	 * Available slots of the index
	 */

	private int vacancy;
	/**
	 * List of students who are enrolled into the index
	 */
	private ArrayList<Student> student;
	/**
	 * List of students who are under the waiting list for the index
	 */
	private ArrayList<Student> waitingList;
	/**
	 * A schedule for the index
	 */
	private Schedule schedule;
	
	/**
	 * Constructor to add an index
	 * @param indexID
	 * @param courseID
	 * @param vacancy
	 * @param schedule
	 */

	public Index(String indexID, String courseID, int vacancy, Schedule schedule) {
		super();
		this.indexID = indexID;
		this.courseID = courseID;
		this.vacancy = vacancy;
		this.student = null;
		this.waitingList = null;
		this.schedule = schedule;
	}

	public Schedule getSchedule() {
		return schedule;
	}
	
	public String getIndexID() {
		return indexID;
	}

	public void setIndexID(String indexID) {
		this.indexID = indexID;
	}
	
	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getVacancy() {
		return vacancy;
	}

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	public ArrayList<Student> getStudent() {
		return student;
	}

	public void setStudent(ArrayList<Student> student) {
		this.student = student;
	}

	public ArrayList<Student> getWaitingList() {
		return waitingList;
	}

	public void setWaitingList(ArrayList<Student> waitingList) {
		this.waitingList = waitingList;
	}
	
}

    package Class;

import java.io.Serializable;
import java.util.*;

/**
 * Each course represents a specific subject under the school
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class Course implements Serializable {

	/**
	 * Full name of the course
	 */
	private String name;
	/**
	 * Unique identifier of the course
	 */
	private String courseID;
	/**
	 * A list of index under the course
	 */
	private ArrayList<Index> index;

	/**
	 * Constructor to add a course
	 * @param name
	 * @param courseID
	 * @param index
	 */
	public Course(String name, String courseID, ArrayList<Index> index) {
		super();
		this.name = name;
		this.courseID = courseID;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public ArrayList<Index> getIndex() {
		return index;
	}

	public void setIndex(ArrayList<Index> index) {
		this.index = index;
	}
	

}

package Class;

import java.io.Serializable;
import java.util.*;
/**
 * Represents a student user enrolled in the school
 * A student can be enrolled in many indexes and courses.
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class Student extends User implements Serializable {
/**
 * A unique identifier to identify a specific school where a student is enrolled in
 */
	private String schoolID;
	
	/**
	 * Constructor to create a new student
	 * @param name
	 * @param username
	 * @param password
	 * @param dob
	 * @param gender
	 * @param nationality
	 * @param email
	 * @param schoolID
	 */
	public Student(String name, String username, String password, Date dob, char gender, String nationality,
			String email, String schoolID) {
		super(name, username, password, dob, gender, nationality, email);
		this.schoolID = schoolID;
	}

	public String getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}
	
}

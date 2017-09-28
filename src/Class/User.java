package Class;

import java.io.Serializable;
import java.util.*;
/**
 * An user in the system
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class User implements Serializable {
	
	private String name;
	/**
	 * username for a student will be his/her matriculation number, whereas an admin's username will have a String 'admin-' appended infront 
	 */
	private String username;
	private String password;
	private Date dob;
	private char gender;
	private String nationality;
	private String email;
	
	/**
	 * Constructor to add an user
	 * @param name
	 * @param username
	 * @param password
	 * @param dob
	 * @param gender
	 * @param nationality
	 * @param email
	 */
	public User(String name, String username, String password, Date dob, char gender, String nationality, String email) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.gender = gender;
		this.nationality = nationality;
		this.email = email;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

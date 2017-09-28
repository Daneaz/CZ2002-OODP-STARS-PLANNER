package Class;

import java.io.Serializable;
import java.util.*;
/**
 * An admin user who is able to manage the system
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class Admin extends User implements Serializable {
/**
 * Indicates privilege level for a admin user in the system, added for scalability
 */
	private String admin;
	/**
	 * Constructor for Admin class
	 * @param name
	 * @param username
	 * @param password
	 * @param dob
	 * @param gender
	 * @param nationality
	 * @param email
	 * @param admin
	 */
	public Admin(String name, String username, String password, Date dob, char gender, String nationality, String email, String admin){
		super(name, username, password, dob, gender, nationality, email);
		this.admin = admin;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
}

package Class;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;
/**
 * Manage the ability of what a user is able to do
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class UserController {
	
	/**
	 * relative path name for admin data file
	 */
	private final static String adminSerialPath = "./source/Admin.dat";
	/**
	 * relative path name for data file
	 */
	private final static String dataSerialPath = "./source/Data.dat";

	/**
	 * relative path name for student data file
	 */
	private final static String studentSerialPath = "./source/Student.dat";
	/**
	 * all existing students  
	 */
	private static ArrayList<Student> listOfStudents = new ArrayList<Student>();
	/**
	 * all existing admins
	 */
	private static ArrayList<Admin> listOfAdmins = new ArrayList<Admin>();
	/**
	 * all data 
	 */
	private static ArrayList<School> listOfSchools = new ArrayList<School>();
	private static User currentUser;
	
	public User getCurrentUser() {
		return currentUser;
	}
		
	public ArrayList<School> getlistOfSchool() {
		return listOfSchools;
	}
	
	public ArrayList<Admin> getlistOfAdmins() {
		return listOfAdmins;
	}
	
	public ArrayList<Student> getlistOfStudents() {
		
		return listOfStudents;
	}

	
	/**
	 * method to send email to a specific user 
	 * @param message
	 * @param head
	 * @param recipient
	 */
	public void sendEmail(String message , String head, String recipient){
		try {
			Email em = new Email();
			em.send(head, message , recipient);
			System.out.println("Email notification has been sent.");
		} catch (Exception e){
			System.out.println("Failed to send email notification.");
		}
		
	}
	
	/**
	 * find a specific student from all students based on username
	 * @param username
	 * @return
	 */
	public Student findByUsername(String username)
	{
		for (Student s : listOfStudents) {
		    if (s.getUsername() != null && s.getUsername() .equals(username))
				return s;
		}
		return null;
	}
	/**
	 * find a specific admin from list of admins based on username
	 * @param username
	 * @return
	 */
	public Admin findByAdminUsername(String username)
	{
		for (Admin s : listOfAdmins) {
		    if (s.getUsername() != null && s.getUsername() .equals(username))
				return s;
		}
		return null;
	}
	
	/**
	 * save the list of admins to data file
	 */
	public void saveAdminUserList(){
		try {
			FileOutputStream fos = new FileOutputStream(adminSerialPath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listOfAdmins);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			//ioe.printStackTrace();
		}
	}
	
	
	/**
	 * method to check for date format
	 * @param s
	 * @return
	 */
	public Date convertDate(String s)
	{
		DateFormat df = new SimpleDateFormat("HH:mm");
		Date date = null;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Eror!! Pleas Enter the Correct Date Format");
			System.out.println();
			return null;
		}
		return date;
	}
	
	public Date convertDate1(String s)
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Eror!! Pleas Enter the Correct Date Format");
			System.out.println();
			return null;
		}
		return date;
	}
	
	
	public void populate(){ // do once only
		
		/*
		Schedule schdule1,schdule2,schdule3,schdule4,schdule5;
		String lab,lect,tut;
		Date datelab, datelect, datetut;
		lab = "15:00";
		lect = "17:00";
		tut = "11:00";
		datelab = convertDate(lab);
		datelect = convertDate(lect);
		datetut = convertDate(tut);
		
		schdule1 = new Schedule(2,2,2,datelab, datelect, datetut, "1234");
		School sch = new School("EEE", "Electrical and Electronics Engineering");
		String sdate = "11/11/2016";
		String edate = "11/12/2019";
		
		sch.setStartDate(convertDate1(sdate));
		sch.setEndDate(convertDate1(edate));
		Index dex = new Index("1234", "2002", 20, schdule1);
		
		lab = "10:00";
		lect = "08:00";
		tut = "12:00";
		datelab = convertDate(lab);
		datelect = convertDate(lect);
		datetut = convertDate(tut);
		
		schdule2 = new Schedule(3,1,4,datelab, datelect, datetut,"2345");
		
		Index dex2 = new Index("2345", "2002", 30,schdule2);
		ArrayList<Index> ind = new ArrayList<Index>();
		
		ind.add(dex);
		ind.add(dex2);
		Course cur = new Course("OODP", "2002", ind);
		ArrayList<Course>  listofcourse = new ArrayList<Course>();
		 listofcourse.add(cur);
		sch.setCourse( listofcourse);
		listOfSchools.add(sch);
		
		sch = new School("SCSE" , "School of Computer Science and Engineering");
		
		sch.setStartDate(convertDate1(sdate));
		sch.setEndDate(convertDate1(edate));
		lab = "15:00";
		lect = "17:00";
		tut = "11:00";
		datelab = convertDate(lab);
		datelect = convertDate(lect);
		datetut = convertDate(tut);
		
		schdule3 = new Schedule(3,1,2,datelab, datelect, datetut,"1111");
		
		dex = new Index("1111" , "2004" , 50, schdule3);
		
		lab = "10:00";
		lect = "08:00";
		tut = "12:00";
		datelab = convertDate(lab);
		datelect = convertDate(lect);
		datetut = convertDate(tut);
		
		schdule4 = new Schedule(2,2,2,datelab, datelect, datetut,"2222");
		
		dex2 = new Index("2222", "2004", 80, schdule4);
		ind = new ArrayList<Index>();
		ind.add(dex);
		ind.add(dex2);
		cur = new Course("HCI", "2004", ind);
		 listofcourse = new ArrayList<Course>();
		 listofcourse.add(cur);
		sch.setCourse(listofcourse);
		listOfSchools.add(sch);
		
		 sch = new School("MSE" , "School of Materials Science And Engineering");
		 sch.setStartDate(convertDate1(sdate));
			sch.setEndDate(convertDate1(edate));
		lab = "10:00";
		lect = "09:00";
		tut = "13:00";
		datelab = convertDate(lab);
		datelect = convertDate(lect);
		datetut = convertDate(tut);
		
		schdule5 = new Schedule(1,2,3,datelab, datelect, datetut,"1423");
		dex2 = new Index("1423" , "1002" , 99, schdule5);
		ind = new ArrayList<Index>();
		listofcourse = new ArrayList<Course>();
		ind.add(dex2);
		cur = new Course("EM2","1002",ind);
		 listofcourse.add(cur);
		sch.setCourse( listofcourse);
		
		listOfSchools.add(sch);
		*/
		
		String nationality = "Singapore";
		String password = "123";
		try {
			password = encrypt("123");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//  dd/MM/yyyy

		Student stud1 = new Student("Joseph", "U1627588L", password, convertDate1("11/02/1992"), 'M', nationality, "fluxthesky@gmail.com", "EEE");
		Student stud2 = new Student("Heath", "U1827348K", password, convertDate1("12/02/1992"), 'M', nationality, "HN@example.com", "EEE");
		Student stud3 = new Student("Ken", "U1837284L", password, convertDate1("13/02/1993"), 'F', nationality, "KEN@example.com", "EEE");
		Student stud4 = new Student("Marilyn", "U1231231C", password, convertDate1("25/02/1990"), 'F', nationality, "fluxthesky@gmail.com", "EEE");
		Student stud5 = new Student("Leo", "U12348271M", password, convertDate1("21/02/1992") , 'M', nationality, "LEO@example.c.om", "EEE");
		Student stud6 = new Student("Aaron" , "U8726163B", password, convertDate1("21/03/1993"), 'F', nationality, "AA@example.com", "EEE");
		Student stud7= new Student("Pamela", "U1928376L", password, convertDate1("23/05/1992"), 'F', nationality, "PAM@example.com", "SCSE");
		Student stud8 = new Student("Tim", "U8272634I", password, convertDate("12/04/1992"), 'M', nationality, "TIM@example.com", "MSE");
		Student stud9 = new Student("Cook", "U8798786M", password, convertDate1("03/02/1930"), 'M', nationality, "COK@example.com", "MSE");
		Student stud10 = new Student("James", "U7261523H", password, convertDate1("13/02/1990"), 'M', nationality, "JM@example.com", "SCSE");
		Student stud11= new Student("Lorenec", "U5213123G", password, convertDate1("19/09/1992"), 'F', nationality, "LOL@example.com", "SCSE");
		Student stud12 = new Student("Kenny", "K1231231K", password, convertDate1("23/02/1993"), 'M', nationality, "fluxthesky@gmail.com", "SCSE");
		Student stud13 = new Student("Valarie", "L1231231L", password, convertDate1("23/01/1992"), 'F', nationality, "fluxthesky@gmail.com", "EEE");
		Student stud14 = new Student("Collin", "M2323498J", password, convertDate1("27/01/1992"), 'M', nationality, "COL@example.com", "EEE");
		Student stud15 = new Student("Harry", "H2839281U", password,convertDate("12/03/1993"), 'M', nationality, "HAR@example.com", "EEE");

		
		listOfStudents.add(stud1);
		listOfStudents.add(stud2);
		listOfStudents.add(stud3);
		listOfStudents.add(stud4);
		listOfStudents.add(stud5);
		listOfStudents.add(stud6);
		listOfStudents.add(stud7);
		listOfStudents.add(stud8);
		listOfStudents.add(stud9);
		listOfStudents.add(stud10);
		listOfStudents.add(stud11);
		listOfStudents.add(stud12);
		listOfStudents.add(stud13);
		listOfStudents.add(stud14);
		listOfStudents.add(stud15);
		
		saveStudentUserList();

		
		
		
		
		
		
		/*
		listOfSchools.add(new School("EEE", "Electrical and Electronics Engineering"));
		listOfSchools.add(new School("SCSE", "School of Computer Science and Engineering"));
		listOfSchools.add(new School("ADM", "Arts Design and Media"));
		listOfSchools.add(new School("SPMS", "School of Physics, Mathematics and Science"));


		try {
			FileOutputStream fos = new FileOutputStream(dataSerialPath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listOfSchools);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
*/
		
	}
	
	/**
	 * save all school data from the application to data file
	 */
	public void saveData(){

		try {
			FileOutputStream fos = new FileOutputStream(dataSerialPath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listOfSchools);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	/**
	 * load all school data from data file to a list of schools
	 */
	public void LoadData(){// load dataserial.dat
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(dataSerialPath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			listOfSchools = (ArrayList) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//System.out.println("STUDENTSERIAL NOT FOUND, NOT ATTEMPTING TO LOAD");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * load all admin users from data file to a list of admin
	 */
	public void loadAdminUserList(){


		FileInputStream fis;
		try {
			fis = new FileInputStream(adminSerialPath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			listOfAdmins = (ArrayList) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//System.out.println("ADMIN NOT FOUND, NOT ATTEMPTING TO LOAD");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * save all students data from application to data file
	 */
	public void saveStudentUserList() {

		try {
			FileOutputStream fos = new FileOutputStream(studentSerialPath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listOfStudents);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	/**
	 * load all students data from data file to a list of students
	 */
	public void loadStudentUserList() {

		FileInputStream fis;
		try {
			fis = new FileInputStream(studentSerialPath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			listOfStudents = (ArrayList) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//System.out.println("STUDENTSERIAL NOT FOUND, NOT ATTEMPTING TO LOAD");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * method to hash clear text passwords using sha-1
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String encrypt(String data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		md.update(data.getBytes());

		return byteToHex(md.digest());
	}
	/**
	 *  method to open console for hidden password input
	 * @return
	 */
	
	public String pass() {
		java.io.Console c = System.console();
		char[] passString;
		//System.out.println("Please Enter Password");
		passString = c.readPassword();
		String pass = new String(passString);
		return pass;
	}
	/**
	 * Convert data bytes into hexadecimal 
	 * @param hash
	 * @return
	 */
	
	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
	
	
	/**
	 * method to authenticate the current user
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public User login() throws NoSuchAlgorithmException{
		
		
		
		Scanner scan = new Scanner(System.in);
		String username;
		int location = 0; // used to know which user we need to get from list
		String password;
		boolean found = false;
		boolean isAdmin = false;
		String admin = "admin-";
		System.out.println("Enter username");
		username = scan.nextLine();
		username = admin + username;
		if(username.startsWith(admin)){ //check if username starts with admin:
			for(int i = 0 ; i < listOfAdmins.size() ; i++){
				if(username.equals(listOfAdmins.get(i).getUsername())){ // if username exists
					location = i;
					found = true;
					isAdmin = true;
					break;
				}
			}
		}
		if (isAdmin == false){
			username = username.substring(6);
			for(int i = 0; i< listOfStudents.size(); i++){
				if(username.equals(listOfStudents.get(i).getUsername())){
					found = true;
					location = i;
					break;
				}
			}
		}
		System.out.println("Enter password");
		password = pass();
		//password = scan.nextLine();
		password = encrypt(password);
		if(isAdmin && found){
			if(listOfAdmins.get(location).getPassword().equals(password)){
				currentUser = listOfAdmins.get(location);
				System.out.println("logged in!");
				return currentUser;
			}else{
				System.out.println("Login failed");
			}
			
		}else if (found){
			
			StudentController sc = new StudentController();

			try {
				if(!sc.checkAccessPeriod(listOfStudents.get(location).getSchoolID())){
					
 					System.out.println("Currently not in access period!");
					return null;
				}
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(listOfStudents.get(location).getPassword().equals(password)){
				
				currentUser = listOfStudents.get(location);
				System.out.println("logged in!");
				return currentUser;
			}else{
				System.out.println("Login failed");
			}
		}else{
			System.out.println("Login failed");
		}
		return null;
	}
	/**
	 * overloaded method to authenticate the current user 
	 * @param username
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	
	public User login(String username, String password) throws NoSuchAlgorithmException{
		User u;
		int location = 0; // used to know which user we need to get from list
		boolean found = false;
		boolean isAdmin = false;
		String admin = "admin-";
		username = admin + username;
		if(username.startsWith(admin)){ //check if username starts with admin:
			for(int i = 0 ; i < listOfAdmins.size() ; i++){
				if(username.equals(listOfAdmins.get(i).getUsername())){ // if username exists
					location = i;
					found = true;
					isAdmin = true;
					break;
				}
			}
		}
		if (isAdmin == false){
			username = username.substring(6);
			for(int i = 0; i< listOfStudents.size(); i++){
				if(username.equals(listOfStudents.get(i).getUsername())){
					found = true;
					location = i;
					break;
				}
			}
		}
		
		
		
		
		
		
		password = encrypt(password);
		if(isAdmin && found){
			if(listOfAdmins.get(location).getPassword().equals(password)){
				u = listOfAdmins.get(location);
				System.out.println("logged in!");
				return u;
			}else{
				System.out.println("Login failed");
			}
			
		}else if (found){
			
			StudentController sc = new StudentController();

			try {
				if(!sc.checkAccessPeriod(listOfStudents.get(location).getSchoolID())){
					
 					System.out.println("Currently not in access period!");
					return null;
				}
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			
			
			
			if(listOfStudents.get(location).getPassword().equals(password)){
				
				u = listOfStudents.get(location);
				System.out.println("logged in!");
				return u;
			}else{
				System.out.println("Login failed");
			}
		}else{
			System.out.println("Login failed");
		}
		
	
		
		
		return null;
	}
	
	/**
	 * method to add a new admin into the list of admins
	 * @throws NoSuchAlgorithmException
	 * @throws ParseException
	 */
	public void createAdminUserSerial() throws NoSuchAlgorithmException, ParseException{
		String name;
		String username;
		String password;
		String dob;
		char gender;
		String nationality;
		String email;

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter name");
		name = scan.nextLine();

		System.out.println("Enter username");
		username = scan.nextLine();
		username = "admin-" + username;
		
		
		if(findByAdminUsername(username) != null){
			System.out.println("Username already exists!");
			return;
		}

		System.out.println("Enter password");

		password = pass();
		password = encrypt(password);

		/*
		 * System.out.println("Enter password"); password = scan.nextLine();
		 */

		System.out.println("Enter date of birth mm/dd/yyyy");
		dob = scan.nextLine();

		System.out.println("Enter gender M/F");
		gender = scan.nextLine().charAt(0);

		System.out.println("Enter nationality");
		nationality = scan.nextLine();

		System.out.println("Enter email");
		email = scan.nextLine();

		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = (Date) formatter.parse(dob);

		Admin adm = new Admin(name, username, password, startDate, gender, nationality, email, "admin");

		listOfAdmins.add(adm);
		 
		
		
		
		
		
	}
	
	/**
	 * method to add a new student into the list of students
	 * @throws NoSuchAlgorithmException
	 * @throws ParseException
	 */
	public void createStudentUserSerial(  ) throws NoSuchAlgorithmException, ParseException {
		String name;
		String username;
		String password;
		String dob;
		char gender = 'M';
		String nationality;
		String email;
		String schoolID;

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter name");
		name = scan.nextLine();

		System.out.println("Enter username");
		username = scan.nextLine();
		
		if(findByUsername(username) != null){
			System.out.println("Username already exists!");
			return;
		}

		System.out.println("Enter password");

		password = pass();
		password = encrypt(password);

		/*
		 * System.out.println("Enter password"); password = scan.nextLine();
		 */

		System.out.println("Enter date of birth mm/dd/yyyy");
		dob = scan.nextLine();

		System.out.println("Enter gender M/F");
		try {
			gender = scan.nextLine().charAt(0);
		} catch (StringIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			System.out.println("Gender not set correctly!");
			return;
		}

		System.out.println("Enter nationality");
		nationality = scan.nextLine();

		System.out.println("Enter email");
		email = scan.nextLine();

		System.out.println("Enter school ID");
		schoolID = scan.nextLine();

		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = (Date) formatter.parse(dob);

		Student stud = new Student(name, username, password, startDate, gender, nationality, email, schoolID);

		
		
		
		listOfStudents.add(stud);
		
		
		AdminController ac = new AdminController();
		ac.showAllStudentInfo();
		
		
		
		
	}
	/**
	 * get a specific index from the list of schools
	 * @param index
	 * @return
	 */
	
	public Index findIndexFromSchools(String index){
		Index ix = null ;
		for (int s=0; s<listOfSchools.size();s++){
			for (int c=0; c<listOfSchools.get(s).getCourse().size();c++){
				for (int i=0; i<listOfSchools.get(s).getCourse().get(c).getIndex().size();i++){
					if (listOfSchools.get(s).getCourse().get(c).getIndex().get(i).getIndexID().equalsIgnoreCase(index)){
						ix = listOfSchools.get(s).getCourse().get(c).getIndex().get(i);
					}
				}
			}
		}
		return ix;
	}
	/**
	 * get a specific course from the list of schools
	 * @param course
	 * @return
	 */
	
	public Course findCourseFromSchools(String course){

		Course cs = null ;
		for (int s=0; s<listOfSchools.size();s++){
			for (int c=0; c<listOfSchools.get(s).getCourse().size();c++){
				if (listOfSchools.get(s).getCourse().get(c).getCourseID().equalsIgnoreCase(course)){
					cs = listOfSchools.get(s).getCourse().get(c);
				}
			}
		}
		return cs;
	}
	/**
	 * get a school from a list of schools 
	 * @param SchoolID
	 * @return
	 */
	
	public School findSchoolFromSchoolID(String SchoolID){
	
		for(int i=0; i<listOfSchools.size();i++) {
			if (listOfSchools.get(i).getSchoolID().equalsIgnoreCase(SchoolID))
				return listOfSchools.get(i);
		}
		return null;	
	}
	/**
	 * find all registered courses of a specific student from all schools
	 * @param stu
	 * @return
	 */
	
	public ArrayList<Index> findRegisteredCourseFromSchools(Student stu) {
		ArrayList<Index> indexList=new ArrayList<Index>();
		int count=0;
		for (int s=0; s<listOfSchools.size();s++){
			for (int c=0; c<listOfSchools.get(s).getCourse().size();c++){
				for (int i=0; i<listOfSchools.get(s).getCourse().get(c).getIndex().size();i++){
					if (listOfSchools.get(s).getCourse().get(c).getIndex().get(i).getStudent()==null ||
							listOfSchools.get(s).getCourse().get(c).getIndex().get(i).getStudent().size()==0) {
						
						continue;
					}
					else {
						for (int j=0;j<listOfSchools.get(s).getCourse().get(c).getIndex().get(i).getStudent().size();j++) {
							if (listOfSchools.get(s).getCourse().get(c).getIndex().get(i).getStudent().get(j).getUsername().equals(stu.getUsername())) {
								count++;
								indexList.add(listOfSchools.get(s).getCourse().get(c).getIndex().get(i));
							}
						}
					}
				}
			}
		}
		if (count==0) return null;
		else return indexList;
	}
	
	
}

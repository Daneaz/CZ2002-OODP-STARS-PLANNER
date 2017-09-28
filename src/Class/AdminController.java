package Class;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * Manage the ability of what a admin is able to do
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class AdminController {

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	Scanner scan = new Scanner(System.in);
	UserController userController = new UserController();
	StudentController studentController = new StudentController();
	/**
	 * display menu of admin functions
	 */
	public void printMenu() {
		System.out.println("Enter your choice");
		System.out.println("1. Edit Student Access Period");
		System.out.println("2. Add a Student(name, metric number, gender, nationality, etc");
		System.out.println("3. Remove a Student(name, metric number, gender, nationality, etc");
		System.out.println("4. Add a course(course code, school, index number, vacancy");
		System.out.println("5. Update a course(course code, school, index number, vacancy");
		System.out.println("6. Remove a course(course code, school, index number, vacancy");
		System.out.println("7. Show student list by index");
		System.out.println("8. Show student list by course");
		System.out.println("9. Check available slot for an Index number");
		System.out.println("10. Logout");
	}
	/**
	 * change add drop period for a specific school
	 * @param school
	 * @param sd
	 * @param ed
	 */
	public void editAccessPeriod(School school, Date sd, Date ed) {
		
		if (school != null ) {
			school.setStartDate(sd);
			school.setEndDate(ed);
			userController.saveData();
			System.out.println("Access Peroid Updated ");
		} else {
			
		}
	}
	/**
	 * check existence of a specific student in a list of school by username
	 * @param username
	 * @return
	 */
	public boolean checkusername(String username)
	{
		for(int i =0;i<userController.getlistOfStudents().size();i++)
		{
			if(userController.getlistOfStudents().get(i).getUsername().equals(username))
			{
				System.out.println("Student already exists!");
				return true;
			}
		}
		return false;
	}
	/**
	 * add a specific student to a specific school
	 * @param student
	 * @param school
	 */
	public void addStudent(Student student, School school) {
		
		boolean exsit = false;

		if (student != null && school != null) {
			if (!exsit) {
				userController.getlistOfStudents().add(student);
				school.addStudent(student);
				userController.saveStudentUserList();
				userController.saveData();
				System.out.println("Student added");
			} else {
				System.out.println("Student already exist");
			}
		} else {
			System.out.println("Student not found or school not found");
		}

	}
	/**
	 * remove a specific student from school
	 * @param username
	 */
	public void removeStudent(String username){
		
		Student student = userController.findByUsername(username);
		if (student != null) {
			School school = userController.findSchoolFromSchoolID(student.getSchoolID());

			userController.getlistOfStudents().remove(student);
			school.removeStudent(student);
			userController.saveStudentUserList();
			userController.saveData();
			System.out.println("Student removed");
		} else {
			System.out.println("Student does not exist");
		}
	}
	/**
	 * check existence of a specific index from a list of school
	 * @param indexid
	 * @return
	 */
	public boolean checkIndex(String indexid)
	{
		ArrayList<School> schoollist =userController.getlistOfSchool();
		for(int i =0;i<schoollist.size();i++)
		{
			for(int j=0; j<schoollist.get(i).getCourse().size();j++)
			{
				for(int k=0; k<schoollist.get(i).getCourse().get(j).getIndex().size();k++)
				{
					if(schoollist.get(i).getCourse().get(j).getIndex().get(k).getIndexID().equals(indexid))
					{
						System.out.println("IndexID exist ");
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * check existence of a specific course from a list of school
	 * @param courseid
	 * @return
	 */
	public boolean checkcourse(String courseid)
	{
		
		ArrayList<School> schoollist =userController.getlistOfSchool();
		for (int i = 0; i < schoollist.size(); i++) {
			for (int j = 0; j < schoollist.get(i).getCourse().size(); j++) {
				if (schoollist.get(i).getCourse().get(j).getCourseID().equals(courseid)) {
					System.out.println("Course already exists! ");
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * add a specific course to a specific school
	 * @param course
	 * @param school
	 */
	public void addCourse(Course course, School school) {
		// newCourse variable to identify adding or updating course
		
		if (course != null) {
			
				school.addCourse(course);
				userController.saveData();
				System.out.println("Course added");
			
		} else {
			System.out.println("Course not found");
		}
	}
	/**
	 * update a specific course in a specific school
	 * @param courseid
	 * @param school
	 */
	public void UpdateCourse(String courseid, School school) {
		
		Course course = userController.findCourseFromSchools(courseid);
		
		
		if (course != null) {
			String newcoursename, newcourseid;

			System.out.println("Please Enter what to edit:");
			System.out.println("1. Course name");
			System.out.println("2. Course ID");
			System.out.println("3. Course index");
			System.out.println("4. Add an IndexID");
			System.out.println("5. Remove an IndexID");
			int coursechoice, indexidchoice, indexdetailchoice;
			coursechoice = scan.nextInt();
			scan.nextLine();
			switch (coursechoice) {
			case 1:
				
				System.out.println("Please Enter new Course name");
				newcoursename = scan.nextLine();
				course.setName(newcoursename);
				System.out.println("Course name Updated");
				break;
			case 2:
				System.out.println("Please Enter new CourseID");
				newcourseid = scan.nextLine();
				
				if(checkcourse(newcourseid))
					break;
				
				course.setCourseID(newcourseid);
				System.out.println("CourseID Updated");
				break;
			case 3:
				System.out.println("Please Choose which index to edit");
				for (int i = 0; i < course.getIndex().size(); i++) {

					System.out.println(i + 1 + ": " + course.getIndex().get(i).getIndexID());
				}
				indexidchoice = scan.nextInt();

				scan.nextLine();
				
				System.out.println("1. Change of IndexID");
				System.out.println("2. Vacancy");
				

				indexdetailchoice = scan.nextInt();
				scan.nextLine();

				switch (indexdetailchoice) {
				case 1:
					System.out.println("Please Enter new indexID:");
					String newindexid;
					newindexid = scan.nextLine();
					if(checkIndex(newindexid))
						break;
					course.getIndex().get(indexidchoice - 1).setIndexID(newindexid);
					System.out.println("IndexID Updated");
					break;
				case 2:
					System.out.println("Please Enter new Vacancy:");
					int newvacancy;
					newvacancy = scan.nextInt();
					scan.nextLine();
					course.getIndex().get(indexidchoice - 1).setVacancy(newvacancy);
					System.out.println("Vacancy Updated");
					break;

				default:
					break;
				}

				break;
			case 4:
				System.out.println("Please Enter new indexID:");
				String addindexid;
				int addvacancy;
				addindexid = scan.nextLine();
				if(checkIndex(addindexid))
					break;
				System.out.println("Please Enter Vacancy:");
				addvacancy = scan.nextInt();
				scan.nextLine();
				
				Random rand = new Random();
				
				int randomday = rand.nextInt((5-1) + 1) + 1;
				int randomday1 = rand.nextInt((5-1) + 1) + 1;
				int randomday2 = rand.nextInt((5-1) + 1) + 1;
				
				int randomhour = rand.nextInt((20-8)+1) + 8;
				int randomhour1 = rand.nextInt((20-8)+1) + 8;
				int randomhour2= rand.nextInt((20-8)+1) + 8;


				Date startlab = convertTime(String.valueOf(randomhour) +":00");

				
				Date starttut = convertTime(String.valueOf(randomhour1) +":00");

				
				Date startlect = convertTime(String.valueOf(randomhour2) +":00");
				
				Schedule newschedule = new Schedule(randomday,randomday1,randomday2,startlab, startlect, starttut,addindexid);
				
				Index addindex = new Index(addindexid,courseid,addvacancy,newschedule);
				course.getIndex().add(addindex);
				System.out.println("IndexID added");
				break;
			case 5:
				System.out.println("Please Enter indexID:");
				String reindexid;
				reindexid = scan.nextLine();
				Index reindex =  userController.findIndexFromSchools(reindexid);
				if(reindex ==null)
				{
					System.out.println("IndexID not found");
					break;
				}
				course.getIndex().remove(reindex);
				System.out.println("IndexID removed");
				break;
			default:
				break;
			}
			userController.saveData();
			
		} else {
			System.out.println("Course not found");
		}
	}
	/**
	 * remove a course from a specific school
	 * @param courseid
	 * @param school
	 */
	public void removeCourse(String courseid, School school) {
		
		Course course = userController.findCourseFromSchools(courseid);
		boolean exsit = false;
		if (course != null) {
			for (int i = 0; i < school.getCourse().size(); i++) {
				if (school.getCourse().get(i).getCourseID().equals(courseid))
					exsit = true;
			}

			if (exsit) {
				school.removeCourse(course);
				userController.saveData();
				System.out.println("Course removed");
			} else {
				System.out.println("Course not exist");
			}
		} else {
			System.out.println("Course not found");
		}
	}
	/**
	 * check available slots in a specific index
	 * @param index
	 * @return
	 */
	public int checkVacancy(Index index) {
		return index.getVacancy();
	}
	
	/**
	 * print all students who are enrolled into a specific index
	 * @param index
	 */
	public void printStudentListByIndex(Index index) {
		if (index == null || index.getStudent() == null) {
			System.out.println("Invalid index");
			return;
		}
		for (int i = 0; i < index.getStudent().size(); i++) {
			System.out.println((i + 1) + ". " + index.getStudent().get(i).getName());
		}
	}
	
	/**
	 * print all students who are enrolled into a specific course
	 * @param course
	 */
	public void printStudentListByCourse(Course course) {
		if (course == null || course.getIndex() == null) {
			System.out.println("Invalid course");
			return;
		}
		for (int i = 0; i < course.getIndex().size(); i++) {
			if (course.getIndex().get(i) == null) {
				return;
			}
			if (course.getIndex().get(i).getStudent() == null) {
				return;
			}
			for (int k = 0; k < course.getIndex().get(i).getStudent().size(); k++) {
				System.out.println((i + 1) + ". " + course.getIndex().get(i).getStudent().get(k).getName());
			}
		}
	}
	
	/**
	 * convert a string to a date
	 * @param s
	 * @return
	 */	
	public Date convertDate(String s)
	{
		
		Date date = null;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Eror!! Pleas Enter the Correct Date Format with /");
			System.out.println();
			return null;
		}
		return date;
	}
	
	/**
	 * convert a string to a time formated date
	 * @param s
	 * @return
	 */
	public Date convertTime(String s)
	{
		DateFormat df = new SimpleDateFormat("HH:mm");
		Date date = null;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Eror!! Pleas Enter the Correct Time Format with :");
			System.out.println();
			return null;
		}
		return date;
	}
	
	/**
	 * display all students information
	 */
	public void showAllStudentInfo()
	{
		String name, username, nationality, schoolid, password;
		char gender;
		System.out.println("Name			Matrics No(Username)		Gender		Nationalily		School");
		System.out.println("===============================================================================================================");
		for(int i=0;i<userController.getlistOfStudents().size();i++)
		{
			name=userController.getlistOfStudents().get(i).getName();
			username=userController.getlistOfStudents().get(i).getUsername();
			gender=userController.getlistOfStudents().get(i).getGender();
			nationality=userController.getlistOfStudents().get(i).getNationality();
			schoolid=userController.getlistOfStudents().get(i).getSchoolID();
			password=userController.getlistOfStudents().get(i).getPassword();
			System.out.println(name+"			" + username +"			 " + gender+"		" +nationality+"			" +schoolid );
		
		}
		System.out.println("===============================================================================================================");
	}
	
	/**
	 * display all course information
	 */
	public void showAllCourseInfo()
	{
		
		String name, coursid,indexid,school, time;
		
		System.out.println("School		Course Name			CoursID			IndexIDs		Vacancy(Total)");
		System.out.println("========================================================================================================================================");
		for(int i=0;i<userController.getlistOfSchool().size();i++)
		{
			school = userController.getlistOfSchool().get(i).getSchoolID();
			System.out.print(school);
			for(int j=0; j<userController.getlistOfSchool().get(i).getCourse().size();j++)
			{
				int vacancy=0;
				name =userController.getlistOfSchool().get(i).getCourse().get(j).getName();
				coursid =userController.getlistOfSchool().get(i).getCourse().get(j).getCourseID();
			//	System.out.print( "		" + name + "			" + coursid + "			");
				System.out.print( "		" + name + "				" + coursid);
				for(int k =0; k<userController.getlistOfSchool().get(i).getCourse().get(j).getIndex().size();k++)
				{
					
					indexid = userController.getlistOfSchool().get(i).getCourse().get(j).getIndex().get(k).getIndexID();
					vacancy = userController.getlistOfSchool().get(i).getCourse().get(j).getIndex().get(k).getVacancy();
					time = userController.getlistOfSchool().get(i).getCourse().get(j).getIndex().get(k).getSchedule().printInfo();
					if(k==0)
					{
						System.out.println("			"+indexid + "			" + vacancy);
						System.out.println("									" + time);
					}
					else
					{
						System.out.println("									"+indexid + "			" + vacancy);
						System.out.println("									" + time);
					}
					
				}
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			}
			
		}
		
	}
	
	/**
	 * display all access period information from all schools
	 */
	public void showSchoolAccessPeriod()
	{
		ArrayList<School> schools = userController.getlistOfSchool();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for(int s=0; s<schools.size();s++){
			System.out.println("SchoolName: "+schools.get(s).getName());
			System.out.println("SchoolID: "+schools.get(s).getSchoolID());
			if(schools.get(s).getStartDate()!=null && schools.get(s).getEndDate()!=null)
			{
				System.out.println("Access Peroid Start Date: "+df.format(schools.get(s).getStartDate()));
				System.out.println("Access Peroid End Date: "+df.format(schools.get(s).getEndDate()));
			}
			else
			{
				System.out.println("Access Peroid Start Date: Not Set");
				System.out.println("Access Peroid Start Date: Not Set");
			}
			
		}
		System.out.println();
		
	}
	
	/**
	 * logic that controls the admin menu flow
	 */
	public void choose() {
		
		int choice = 0, vacancy=0;;
		String username,schoolid;
		Student student = null;
		School school;
		
		String  coursename,courseid,indexno;
		while (choice != 10) {
			
			printMenu();
			
			choice = scan.nextInt();
			scan.nextLine();

			switch (choice) {
			case 1:
				// edit student access period
				showSchoolAccessPeriod();
				Date sd, ed;
				String stringsd, stringed;
				System.out.println("Please Enter the School: ");
				schoolid = scan.nextLine();
				school = userController.findSchoolFromSchoolID(schoolid);
				if(school==null)
				{
					System.out.println("School not found!");
					break;
				}
				if (school.getStartDate() != null)
					System.out.println("The current Start Date is:" + df.format(school.getStartDate()));
				else
					System.out.println("The current Start Date is: Not Set");
				if (school.getEndDate() != null)
					System.out.println("The current End Date is:" + df.format(school.getEndDate()));
				else
					System.out.println("The current End Date is: Not Set");
				
				System.out.println("Please Enter Start Date in the format dd/MM/yyyy: ");
				stringsd = scan.nextLine();
				sd = convertDate(stringsd);
				if(sd ==null)
					break;
				System.out.println("Please Enter End Date in the format dd/MM/yyyy: ");
				stringed = scan.nextLine();
				ed = convertDate(stringed);
				if(ed==null)
					break;
				editAccessPeriod(school, sd, ed);
				break;
			case 2:
				// add a student
				showAllCourseInfo();
				String name, metricid, password,dobstring,nationality,email;
				char gender;
				Date dob;
				System.out.println("Please Enter SchoolID: ");
				schoolid = scan.nextLine();
				school = userController.findSchoolFromSchoolID(schoolid);
				if(school==null)
				{
					System.out.println("School not found!");
					break;
				}
				System.out.println("Please Enter Name: ");
				name = scan.nextLine();
				System.out.println("Please Enter Metric number: ");
				metricid = scan.nextLine();
				if(checkusername(metricid))
					break;
				System.out.println("Please Enter Password: ");
				password = userController.pass();
				try {
					password = userController.encrypt(password);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Please Enter Date of Birth in the format dd/MM/yyyy: ");
				dobstring = scan.nextLine();
				dob= convertDate(dobstring);
				if(dob ==null)
					break;
				System.out.println("Please Enter Gender M/F: ");
				gender = scan.nextLine().charAt(0);
				System.out.println("Please Enter Nationality: ");
				nationality = scan.nextLine();
/*				System.out.println("Please Enter Email: ");
				email = scan.nextLine();*/
				email = metricid + "@e.ntu.edu.sg";
				
				
				student = new Student(name,metricid,password,dob,gender,nationality,email,schoolid);
				
				addStudent(student, school);
				showAllStudentInfo();
				break;
			case 3:
				// remove a student
				
				System.out.println("Please Enter Username: ");
				username = scan.nextLine();
/*				student = userController.findByUsername(username);	*/		
				removeStudent(username);

				break;
			case 4:
				// add a course
				showAllCourseInfo();
				ArrayList<Index> totalindex = new ArrayList<Index>();
				Index index;
				boolean indexbreak = false;
				boolean flag =false;
				System.out.println("Please Enter the School: ");
				schoolid = scan.nextLine();
				school = userController.findSchoolFromSchoolID(schoolid);
				if(school == null)
				{
					System.out.println("School not found! ");
					break;
				}
				System.out.println("Please Enter Course Name: ");
				coursename = scan.nextLine();
				
				System.out.println("Please Enter CourseID: ");
				courseid = scan.nextLine();
				
				if(checkcourse(courseid)){
					break;
				}
				
				System.out.println("Please Enter Number of course index in total: ");
				int j =scan.nextInt();
				scan.nextLine();
				
				String check[] = new String[j];
				
				for(int i=0;i<j;i++){
					System.out.println("Please Enter Course index: ");
					indexno = scan.nextLine();
					check[i] = indexno;
					if(checkIndex(indexno))
					{
						indexbreak = true;
						break;
					}
					for(int k=0; k<i;k++)
					{
						if(check[k].equals(indexno))
						{
							flag = true;
							System.out.println("Index duplicated ");
							break;
						}	
					}
					if(flag)
						break;
					System.out.println("Please Enter Course vacancy: ");
					vacancy = scan.nextInt();
					scan.nextLine();
					
					Random rand = new Random();
					
					int randomday = rand.nextInt((5-1) + 1) + 1;
					int randomday1 = rand.nextInt((5-1) + 1) + 1;
					int randomday2 = rand.nextInt((5-1) + 1) + 1;
					
					int randomhour = rand.nextInt((20-8)+1) + 8;
					int randomhour1 = rand.nextInt((20-8)+1) + 8;
					int randomhour2= rand.nextInt((20-8)+1) + 8;


					Date startlab = convertTime(String.valueOf(randomhour) +":00");

					
					Date starttut = convertTime(String.valueOf(randomhour1) +":00");

					
					Date startlect = convertTime(String.valueOf(randomhour2) +":00");
					
					Schedule newschedule = new Schedule(randomday,randomday1,randomday2,startlab, startlect, starttut,indexno);
					
					index = new Index(indexno,courseid,vacancy,newschedule);
					
					totalindex.add(index);
					
				}
				
				if(!indexbreak)
				{
					Course newcourse = new Course(coursename,courseid,totalindex);
					addCourse(newcourse,school);
					showAllCourseInfo();
				}
				break;
			case 5:
				// update a course
				showAllCourseInfo();
				System.out.println("Please Enter the School: ");
				schoolid = scan.nextLine();
				school = userController.findSchoolFromSchoolID(schoolid);
				if(school==null)
				{
					System.out.println("School not found!");
					break;
				}
				System.out.println("Please Enter Course ID: ");
				courseid = scan.nextLine();
				
				UpdateCourse(courseid, school);
				break;
			case 6:
				// remove a course
				showAllCourseInfo();
				System.out.println("Please Enter the School: ");
				schoolid = scan.nextLine();
				school = userController.findSchoolFromSchoolID(schoolid);
				if(school == null)
				{
					System.out.println("School not found! ");
					break;
				}
				
				System.out.println("Please Enter Course ID: ");
				courseid = scan.nextLine();
				
				
				
				removeCourse(courseid,school);
					
				break;
			case 7:
				// show student list by index
				showAllCourseInfo();
				System.out.println("Enter index ID: ");
				String index1 = scan.nextLine();
				printStudentListByIndex(userController.findIndexFromSchools(index1));
				System.out.println("");
				break;
			case 8:
				// show student list by course
				showAllCourseInfo();
				System.out.println("Enter course ID: ");
				String course1 = scan.nextLine();
				printStudentListByCourse(userController.findCourseFromSchools(course1));
				System.out.println("");
				break;
			case 9:
				// Check available slot for an index number
				System.out.println("Enter the Course Index: ");
				String courseindex=scan.nextLine();
				Index ind=userController.findIndexFromSchools(courseindex);
				if(ind != null){
				System.out.println("The vacancy for"+courseindex+"is: "+checkVacancy(ind));
				}else{
					System.out.println("Index not found!");
				}
				
				break;
			case 10:
				//logout
				break;
			default:
				break;
				
			}
		}
	}
	
}

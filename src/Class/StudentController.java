package Class;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Manage the ability of what a student is able to do
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class StudentController {
	
	UserController userController = new UserController();

	Scanner sc = new Scanner(System.in);

	/**
	 * display menu of student functions
	 */
	public void printMenu() {
		System.out.println("Enter your choice");
		System.out.println("1. Add Course");
		System.out.println("2. Drop Course");
		System.out.println("3. Check/Print Cources Registered");
		System.out.println("4. Check Vacancies Available");
		System.out.println("5. Change Index Number Of Course");
		System.out.println("6. Swap Index Number with Another Student");
		System.out.println("7. Print time table");
		System.out.println("8. Logout");
	}


	/**
	 * print timetable of a specific student
	 * @param student
	 */

	public void printTimeTable(Student student) {
		
		DateFormat df1 = new SimpleDateFormat("HH:mm");
		Date dat = null;
		Date datend;
		Schedule sch = null;
		Calendar cal = Calendar.getInstance();
		ArrayList<Date> labList = new ArrayList<Date>();
		ArrayList<Date> lectList = new ArrayList<Date>();
		ArrayList<Date> tutList = new ArrayList<Date>();


		ArrayList<Lesson> mondayList=new ArrayList<Lesson>();
		ArrayList<Lesson> tuesdayList=new ArrayList<Lesson>();
		ArrayList<Lesson> wednesdayList=new ArrayList<Lesson>();
		ArrayList<Lesson> thursdayList=new ArrayList<Lesson>();
		ArrayList<Lesson> fridayList=new ArrayList<Lesson>();
		

		boolean monflag = false;
		boolean tueflag = false;
		boolean wedflag = false;
		boolean thurflag = false;
		boolean friflag = false;
		String whitespaces = "	   ";
		
		
 		
		ArrayList<Index> RegIndex = userController.findRegisteredCourseFromSchools(student);
		
		if (RegIndex != null) {
			for (int i = 0; i < RegIndex.size(); i++) {
				Course courseReg = userController.findCourseFromSchools(RegIndex.get(i).getCourseID());
				
				sch=RegIndex.get(i).getSchedule();
				
				switch (sch.getLab().getDay()) {
				case 1:
					mondayList.add(sch.getLab());
					break;
				case 2:
					tuesdayList.add(sch.getLab());
					break;
				case 3:
					wednesdayList.add(sch.getLab());
					break;
				case 4:
					thursdayList.add(sch.getLab());
					break;
				case 5:
					fridayList.add(sch.getLab());
					break;
					
				default: System.out.println("Day Wrong");
				}
				
				switch (sch.getLect().getDay()) {
				case 1:
					mondayList.add(sch.getLect());
					break;
				case 2:
					tuesdayList.add(sch.getLect());
					break;
				case 3:
					wednesdayList.add(sch.getLect());
					break;
				case 4:
					thursdayList.add(sch.getLect());
					break;
				case 5:
					fridayList.add(sch.getLect());
					break;
				default: System.out.println("Day Wrong");
				}
				
				switch (sch.getTut().getDay()) {
				case 1:
					mondayList.add(sch.getTut());
					break;
				case 2:
					tuesdayList.add(sch.getTut());
					break;
				case 3:
					wednesdayList.add(sch.getTut());
					break;
				case 4:
					thursdayList.add(sch.getTut());
					break;
				case 5:
					fridayList.add(sch.getTut());
					break;
				default: System.out.println("Day Wrong");
				}
			}

				 

			
			sortLesson(mondayList);
			sortLesson(tuesdayList);
			sortLesson(wednesdayList);
			sortLesson(thursdayList);
			sortLesson(fridayList);
			
			int i = 0;
			System.out.println(
					"================================================================================================================");
			System.out.print("Monday" + " 	");
			for (i = 0; i < mondayList.size(); i++) {
				System.out.print("	"+userController.findIndexFromSchools(mondayList.get(i).getIndexID()).getCourseID() + " "
						+ mondayList.get(i).getVenue() + " " + df1.format(mondayList.get(i).getStarttime()) + "-"
						+ df1.format(mondayList.get(i).getEndtime()));
			}
			System.out.println();
			System.out.println(
					"================================================================================================================");
			System.out.print("Tuesday" + " ");
			for (i = 0; i < tuesdayList.size(); i++) {
				System.out.print("	"+userController.findIndexFromSchools(tuesdayList.get(i).getIndexID()).getCourseID()
						+ " " + tuesdayList.get(i).getVenue() + " " + df1.format(tuesdayList.get(i).getStarttime())
						+ "-" + df1.format(tuesdayList.get(i).getEndtime()));
			}
			System.out.println();
			System.out.println(
					"================================================================================================================");
			System.out.print("Wednesday" + " ");
			for (i = 0; i < wednesdayList.size(); i++) {
				System.out.print("	"+userController.findIndexFromSchools(wednesdayList.get(i).getIndexID()).getCourseID()
						+ " " + wednesdayList.get(i).getVenue() + " " + df1.format(wednesdayList.get(i).getStarttime())
						+ "-" + df1.format(wednesdayList.get(i).getEndtime()));
			}
			System.out.println();
			System.out.println(
					"================================================================================================================");
			System.out.print("Thursday" + " ");
			for (i = 0; i < thursdayList.size(); i++) {
				System.out.print("	"+userController.findIndexFromSchools(thursdayList.get(i).getIndexID()).getCourseID()
						+ " " + thursdayList.get(i).getVenue() + " " + df1.format(thursdayList.get(i).getStarttime())
						+ "-" + df1.format(thursdayList.get(i).getEndtime()));
			}
			System.out.println();
			System.out.println(
					"================================================================================================================");
			System.out.print("Friday" + "	 ");
			for (i = 0; i < fridayList.size(); i++) {
				System.out.print("	"+userController.findIndexFromSchools(fridayList.get(i).getIndexID()).getCourseID()
						+ " " + fridayList.get(i).getVenue() + " " + df1.format(fridayList.get(i).getStarttime()) + "-"
						+ df1.format(fridayList.get(i).getEndtime()));
			}
			System.out.println();
			System.out.println(
					"================================================================================================================");
		}
		else System.out.println("No index found for this student.");
	}

	/**
	 * Method takes a given index and check against all index that are registered under a specific student
	 * @param index
	 * @param student
	 * @return
	 */
	public boolean checkSchedule(Index index, Student student) {
		Schedule sch;
		ArrayList<Index> RegIndex = userController.findRegisteredCourseFromSchools(student);
		if (RegIndex != null) {
			for (int i = 0; i < RegIndex.size(); i++) {
				Course courseReg = userController.findCourseFromSchools(RegIndex.get(i).getCourseID());
				sch = RegIndex.get(i).getSchedule();
				if (sch.checkConflict(index.getSchedule()) == true) {
					return true;
				}
			}
		}
		return false;
	}
	public void sortLesson(ArrayList<Lesson> LessonList) {
		
		int i=0;
		int j=0;
		Lesson temp;
		for (i=0;i<LessonList.size();i++) {
			for (j=1;j<LessonList.size()-i;j++)
				if (LessonList.get(j-1).getStarttime().getTime()>LessonList.get(j).getStarttime().getTime()) {
					temp=LessonList.get(j-1);
					LessonList.set(j-1, LessonList.get(j));
					LessonList.set(j,temp);
				}
		}
		
	}

	/**
	 * add a specific index under a course to a specific student
	 * @param index
	 * @param student
	 * @return
	 * @throws ParseException
	 */
	public String addCourse(String index, Student student) throws ParseException {
		String msg = "";
		Index in = userController.findIndexFromSchools(index);
		// perform checks
		if (in == null) {
			msg = "Index not found.";
			return msg;
		}
		ArrayList<Student> slist = in.getStudent();
		if (slist != null) {
			for (int i = 0; i < slist.size(); i++) {
				if (slist.get(i).getUsername().equals(student.getUsername())) {
					msg = "You are already registered under the index.";
					return msg;
				}
			}
		}
		ArrayList<Index> c = userController.findRegisteredCourseFromSchools(student);
		if (c != null) {
			for (Index i : c) {
				if (i.getCourseID().equals(in.getCourseID())) {
					msg = "You are not allowed to register multiple index under the same course.";
					return msg;
				}
			}
		}
		// add to waiting list
		if (in.getVacancy() < 1) {
			msg = "This index under the course is full, you will be under the waiting list.";
			addToWaitingList(in, student);
			return msg;
		}
		// perform add
		if (checkSchedule(in, student)) {
			System.out.println("Day/Time clash with other courses!");
		} else {
			if (slist == null) {
				slist = new ArrayList<Student>();
			}
			slist.add(student);
			in.setStudent(slist);
			in.setVacancy(in.getVacancy() - 1);
			userController.saveData();
			String body = "You are now registered under course "+in.getCourseID()+" , index "+in.getIndexID();
			String head = "Course registration";
			userController.sendEmail(body, head, student.getEmail());
			msg = "You have successfully registered the course.";
		}
		return msg;
	}

	/** 
	 * add a specific student to the waiting list under a specific index
	 * @param index
	 * @param s
	 */
	public void addToWaitingList(Index index, Student s) {
		ArrayList<Student> waitingList = index.getWaitingList();
		if (waitingList == null) {
			waitingList = new ArrayList<Student>();
			waitingList.add(s);
		} else
			waitingList.add(s);
		index.setWaitingList(waitingList);
		userController.saveData();
		System.out.println("You have successfully registered in the  waiting list");
	}

	/**
	 * drop a specific index under a course that belongs to a specific student
	 * @param index
	 * @param student
	 */
	public void dropCourse(String index, Student student) {
		String msg = "";
		Index in = userController.findIndexFromSchools(index);
		// perform checks
		if (in == null) {
			msg = "Index not found.";
			System.out.println(msg);
			return;
		}
		ArrayList<Student> slist = in.getStudent();
		if (slist == null || slist.size() == 0) {
			msg = "You are not registered under the index.";
			System.out.println(msg);
			return;
		}
		/*
		 * for (int i=0; i<slist.size();i++){ if
		 * (!slist.get(i).getUsername().equals(student.getUsername())){ msg =
		 * "You are not registered under the index."; return msg; } }
		 */

		// perform drop
		int ssize = slist.size();
		int i = 0;
		for (i = 0; i < ssize; i++) {
			if (slist.get(i).getUsername().equals(student.getUsername())) {
				slist.remove(i);
				slist.removeAll(Collections.singleton(null));
				break;
			}
		}
		if (i == ssize) {
			msg = "You are not registered under the index.";
			System.out.println(msg);
			return;
		} else {
			in.setStudent(slist);
			in.setVacancy(in.getVacancy() + 1);
			userController.saveData();
			String body = "You have successfuly dropped from" + in.getCourseID() + ", " + in.getIndexID();
			String head = "Drop course";
			userController.sendEmail(body, head, student.getEmail());
			msg = "You have successfully dropped the course.";
			System.out.println(msg);
		}
		// addFromWaitingList
		if (in.getWaitingList() != null && !in.getWaitingList().isEmpty()) {

			ArrayList<Student> waitingList = in.getWaitingList();
			Student stuTemp = waitingList.remove(0);
			waitingList.removeAll(Collections.singleton(null));

			// perform add
			slist.add(stuTemp);
			in.setStudent(slist);
			in.setVacancy(in.getVacancy() - 1);

			System.out.println("Student " + stuTemp.getName() + " has been added to index " + in.getIndexID());
			in.setWaitingList(waitingList);
			userController.saveData();
			// send email
			String body = "You have successfuly enrolled into " + in.getCourseID() + ", " + in.getIndexID() + " from WaitList!";
			String head = "Course registration";
			userController.sendEmail(body, head, stuTemp.getEmail());
		}
		return;
	}

	/**
	 * print all course/index registered under a specific student
	 * @param s
	 */
	public void printCourseReg(Student s) {
		System.out.println("   CourseID   CourseName   Index   ");
		System.out.println("===================================");
		ArrayList<Index> RegIndex = userController.findRegisteredCourseFromSchools(s);
		if (RegIndex != null) {
			for (int i = 0; i < RegIndex.size(); i++) {
				Course courseReg = userController.findCourseFromSchools(RegIndex.get(i).getCourseID());
				System.out.println(
						"   " + RegIndex.get(i).getCourseID() + "       " + courseReg.getName() + "         " + RegIndex.get(i).getIndexID());
			}
		} else
			System.out.println("No Course Registered found for this Student");
		return;
	}

	/**
	 * check available slots of a specific index under a course
	 * @param index
	 * @return
	 */
	public int checkVacancy(Index index) {
		return index.getVacancy();
	}

	/**
	 * change a currently registered index to a new index under the same course, for a specific student
	 * @param student
	 * @param currentIndex
	 * @param newIndex
	 * @return
	 */
	public String changeCourseIndex(Student student, String currentIndex, String newIndex) {
		String msg = "";
		Index current = userController.findIndexFromSchools(currentIndex);
		Index newin = userController.findIndexFromSchools(newIndex);
		if (current == null || current.getStudent().size() == 0) {
			msg = "You are not registered under the current index.";
			return msg;
		}
		boolean found = false;
		for (int i = 0; i < current.getStudent().size(); i++) {
			if (current.getStudent().get(i).getUsername().equals(student.getUsername())) {
				found = true;
				break;
			}
		}
		if (found == false)
		{
			msg = "You are not registered under the current index.";
			return msg;
		}
		if (newin == null) {
			msg = "New index not found.";
			return msg;
		}
		if (!current.getCourseID().equalsIgnoreCase(newin.getCourseID())) {
			msg = "Current index and new index does not belong to the same course.";
			return msg;
		}
		if (checkSchedule(newin, student)) {
			msg = "Day/Time clash with other courses!";
			return msg;
		}
		if (newin.getVacancy() < 1) {
			addToWaitingList(newin, student);
			msg = "New index is full, you will be under waiting list.";
			return msg;
		}
		System.out.println("Course: " + current.getCourseID());
		System.out.println("Current index No: " + current.getIndexID() + "\t\t" + "New index No: " + newin.getIndexID());
		System.out.println("Confirm to change? (yes/no) ");
		String cfm = sc.nextLine();
		if (cfm.equalsIgnoreCase("no")) {
			msg = "Change canceled.";
		} else if (cfm.equalsIgnoreCase("yes")) {
			// perform change
			ArrayList<Student> a = current.getStudent();
			for (int i = 0; i < a.size(); i++) {
				if (a.get(i).getUsername().equals(student.getUsername())) {
					a.remove(i);
					a.removeAll(Collections.singleton(null));
				}
			}
			current.setStudent(a);
			current.setVacancy(current.getVacancy() + 1);
			ArrayList<Student> b = newin.getStudent();
			if (b == null) {
				b = new ArrayList<Student>();
			}
			b.add(student);
			newin.setStudent(b);
			newin.setVacancy(newin.getVacancy() - 1);
			userController.saveData();
			String body = "You have successfuly changed into course " + newin.getCourseID() + ", index " + newin.getIndexID();
			String head = "Course index changed";
			userController.sendEmail(body, head, student.getEmail());
			msg = "Index successfully changed.";
		} else {
			msg = "Confirmation failed.";
		}
		return msg;
	}

	/**
	 * for two distinct students under the same course, swap their indexes
	 * @param s1
	 * @param index1
	 * @param s2
	 * @param index2
	 * @return
	 */
	public String swapIndex(Student s1, String index1, Student s2, String index2) {
		String msg = "";
		Index i1 = userController.findIndexFromSchools(index1);
		Index i2 = userController.findIndexFromSchools(index2);

		if (i1 == null || i1.getStudent().size() == 0) {
			msg = s1.getName() + " is not registered under " + index1 + ".";
			return msg;
		}
		boolean found = false;
		for (int i = 0; i < i1.getStudent().size(); i++) {
			if (i1.getStudent().get(i).getUsername().equals(s1.getUsername())) {
				found = true;
				msg = s1.getName() + " is not registered under " + index1 + ".";
				break;
			}
		}
		if (i2 == null || i2.getStudent().size() == 0) {
			msg = s2.getName() + " is not registered under " + index2 + ".";
			return msg;
		}
		for (int i = 0; i < i2.getStudent().size(); i++) {
			if (i2.getStudent().get(i).getUsername().equals(s2.getUsername())) {
				found = true;
				msg = s2.getName() + " is not registered under " + index2 + ".";
				break;
			}
		}
		if (found == false) {
			msg = "Student not found in the index";
			return msg;
		}
		if (!i1.getCourseID().equalsIgnoreCase(i2.getCourseID())) {
			msg = "Students are not registered under the same course.";
			return msg;
		}
		/*if (checkSchedule(i1, s1)) {
			msg = " Student 1 ssschedule conflict";
			return msg;
		}
		if (checkSchedule(i2, s2)) {
			msg = "Student 2 schedule conflict";
			return msg;
		}*/
		System.out.println("Course: " + i1.getCourseID());
		System.out.println("Student: " + s1.getName() + "\t\t" + "Student: " + s2.getName());
		System.out.println(s1.getUsername() + " | " + i1.getIndexID() + "\t\t" + s2.getUsername() + " | " + i2.getIndexID());
		System.out.println("Confirm to swap? (yes/no) ");
		String cfm = sc.nextLine();
		if (cfm.equalsIgnoreCase("no")) {
			msg = "Swap canceled.";
		} else if (cfm.equalsIgnoreCase("yes")) {
			// perform swap
			ArrayList<Student> sl1 = i1.getStudent();
			for (int i = 0; i < sl1.size(); i++) {
				if (sl1.get(i).getUsername().equals(s1.getUsername())) {
					sl1.remove(i);
					sl1.removeAll(Collections.singleton(null));
				}
			}
			sl1.add(s2);
			ArrayList<Student> sl2 = i2.getStudent();
			for (int i = 0; i < sl2.size(); i++) {
				if (sl2.get(i).getUsername().equals(s2.getUsername())) {
					sl2.remove(i);
					sl2.removeAll(Collections.singleton(null));
				}
			}
			sl2.add(s1);
			i1.setStudent(sl1);
			i2.setStudent(sl2);
			userController.saveData();
			String body1 = "You have successfuly swapped into " + i1.getCourseID() + ", " + i2.getIndexID() + " with " + s2.getName();
			String body2 = "You have successfuly swapped into " + i2.getCourseID() + ", " + i1.getIndexID() + " with " + s1.getName();
			String head = "Course swapped";
			userController.sendEmail(body1, head, s1.getEmail());
			userController.sendEmail(body2, head, s2.getEmail());
			msg = "Index successfully swapped.";
		} else {
			msg = "Confirmation failed.";
		}
		return msg;
	}

	/**
	 * check if the current day is under the access period for a given school
	 * @param id
	 * @return
	 * @throws ParseException
	 */
	public boolean checkAccessPeriod(String id) throws ParseException {
		Date start = null, end = null;
		for (School s : userController.getlistOfSchool()) {
			if (s.getSchoolID().equals(id)) {
				start = s.getStartDate();
				end = s.getEndDate();
			}
		}
		if (start == null || end == null) {
			return false;
		}
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new SimpleDateFormat("dd/MM/yyyy").parse(sdfDate.format(new Date()));
		if (start.compareTo(today) * today.compareTo(end) >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * logic for display menu
	 * @throws NoSuchAlgorithmException
	 * @throws ParseException
	 */
	public void choose() throws NoSuchAlgorithmException, ParseException {
		Student std = (Student) userController.getCurrentUser();
		int choice = 0;
		while (choice != 8) {
			printMenu();
			AdminController adminController = new AdminController();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			/**
			 * logic to add course
			 */
			case 1:
				if (checkAccessPeriod(std.getSchoolID()) == false) {
					System.out.println("Not in access period.");
					break;
				}
				adminController.showAllCourseInfo();
				System.out.println("Enter index number: ");
				String addIndex = sc.nextLine();
				System.out.println(addCourse(addIndex, std));
				break;
				
				/**
				 * logic to drop course
				 */
			case 2:
				if (checkAccessPeriod(std.getSchoolID()) == false) {
					System.out.println("Not in access period.");
					break;
				}
				printCourseReg(std);
				System.out.println("Enter index number: ");
				String dropIndex = sc.nextLine();
				dropCourse(dropIndex, std);
				break;
				
				/**
				 * logic to check/print course
				 */
			case 3:
				System.out.println("Course Registered: ");
				printCourseReg(std);
				break;
				
				/**
				 * logic to check vacancies available
				 */
			case 4:
				if (checkAccessPeriod(std.getSchoolID()) == false) {
					System.out.println("Not in access period.");
					break;
				}
				adminController.showAllCourseInfo();
				System.out.println("Enter the Course Index: ");
				String courseindex = sc.nextLine();
				Index ind = null;
				if (userController.findIndexFromSchools(courseindex) != null) {
					ind = userController.findIndexFromSchools(courseindex);
					System.out.println("The vacancy for " + courseindex + " is: " + checkVacancy(ind));
				} else
					System.out.println("No coresponding index found.");
				break;
				
				/**
				 * logic to change course index
				 */
			case 5:
				if (checkAccessPeriod(std.getSchoolID()) == false) {
					System.out.println("Not in access period.");
					break;
				}
				printCourseReg(std);
				System.out.println("Enter current index number: ");
				String current = sc.nextLine();
				System.out.println("Enter new index number: ");
				String newindex = sc.nextLine();
				System.out.println(changeCourseIndex(std, current, newindex));
				break;
		
				/**
				 * logic to swap index with another student
				 */
			case 6:
				if (checkAccessPeriod(std.getSchoolID()) == false) {
					System.out.println("Not in access period.");
					break;
				}
				printCourseReg(std);
				System.out.println("Enter index number: ");
				String i1 = sc.nextLine();
				System.out.println("Enter username for student 2: ");
				String username = sc.nextLine();
				System.out.println("Enter password for student 2: ");
				String password = userController.pass();
				//String password = sc.nextLine();
				System.out.println("Swap with another student's index number: ");
				String i2 = sc.nextLine();
				Student u2 = (Student) userController.login(username, password);
				//printCourseReg(u2);
				System.out.println(swapIndex(std, i1, u2, i2));
				break;
				
				/**
				 * logic to print time table for a student
				 */
			case 7:
				printTimeTable(std);
				break;
				
				/**
				 * logic for user to logout
				 */
			case 8:
				break;
				/**
				 * When user key in any other value
				 */
			default:
				break;
			}
		}
	}

}

package Class;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The main application, boundary class that will interact with the user, responsible of calling methods and display menus
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class UserInterface {

	/**
	 * the current user who is using the application
	 */
	private static User currentUser;
	/**
	 * instantiation of student controller class
	 */
	private static StudentController studentControl;
	/**
	 * instantiation of admin controller class
	 */
	private static AdminController adminControl;
	/**
	 * instantiation of user controller class
	 */
	private static UserController userControl;
	

	public static void main(String[] args) throws ParseException {
		
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		userControl = new UserController();

		userControl.loadStudentUserList();

		userControl.loadAdminUserList();
		//
		userControl.LoadData();	
		
		
		
	/*	showUserInfo();
		System.out.println("Default password for all user is '123'");
		System.out.println();
		showSchoolInfo();*/
		
		System.out.println();
			
		while (choice != 2) {

			System.out.println("1. Login");
			//System.out.println("2. Create student user");
			//System.out.println("3. Create admin user");
			System.out.println("2. Exit");
			//System.out.println("5. Show School Information");
			//System.out.println("6. Show User Information");
			try {
				choice = scan.nextInt();
			} catch (InputMismatchException e2) {
				// TODO Auto-generated catch block
				System.out.println("Invalid Input");
			}
			scan.nextLine();
			
			/*
			 * for (int i = 0; i < listOfStudents.size(); i++) {
			 * System.out.println(listOfStudents.get(i).getName()); } for(int i
			 * = 0 ; i <listOfAdmins.size(); i++){
			 * System.out.println(listOfAdmins.get(i).getName()); }
			 */

			switch (choice) {
			case 1:
				try {

					currentUser = userControl.login();
					
					
					if (currentUser != null) {
						setUpControllers();
						printMainMenu();
					}

				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			/*case 2:
				try {
					userControl.createStudentUserSerial();
					userControl.saveStudentUserList();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Data could not be parsed!");
				}
				break;
			case 3:
				try {
					userControl.createAdminUserSerial();
					userControl.saveAdminUserList();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Data could not be parsed!");

				}
				break;
*/
			case 2:
				userControl.saveAdminUserList();
				userControl.saveStudentUserList();
				userControl.saveData();
				break;
		/*	case 5:
				showSchoolInfo();
				break;
			case 6:
				showUserInfo();
				break;
			case 7:
				userControl.populate();*/
			default:
				break;

			}
		}
	}

	public static void showUserInfo()
	{
		AdminController admin = new AdminController();
		admin.showAllStudentInfo();
	}

	public static void showSchoolInfo()
	{
		AdminController admin = new AdminController();
		admin.showAllCourseInfo();
	}
	
	public static void setUpControllers(){
		
		if (currentUser instanceof Student) {

			studentControl = new StudentController();

		} else if (currentUser instanceof Admin) {
			adminControl = new AdminController();

		}

	}
 	
	/**
	 * Display main menu of the application
	 * @throws NoSuchAlgorithmException
	 * @throws ParseException
	 */
	public static void printMainMenu() throws NoSuchAlgorithmException, ParseException{ //prints the main menu according to the user
		
		if (currentUser instanceof Student) {

			try {
				studentControl.choose();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (currentUser instanceof Admin) {
			adminControl.choose();
		}
	}
}

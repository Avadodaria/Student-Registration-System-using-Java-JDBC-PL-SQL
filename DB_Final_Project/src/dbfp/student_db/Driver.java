package dbfp.student_db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dbfp.utilities.ConnectionUtility;
import dbfp.utilities.Display_Util;

public class Driver {
	public static void main(String[] args) {

		String bNumber = null, message = null;
		ResultSet rsObj = null;
		int choiceMenu = 0;

		try {
			ConnectionUtility connectionUtil = new ConnectionUtility();
			Display_Util displayObj = new Display_Util();
			ProcessQuery pqObj = new ProcessQuery(connectionUtil.createConnection());

			Scanner sc = new Scanner(System.in);
			while (true) {
				displayObj.displayMenu();
				choiceMenu = sc.nextInt();
				pqObj.commitToDB();
				switch (choiceMenu) {
				case 1:
					showSubmenu(sc, connectionUtil.getConnectionObj(), displayObj, pqObj);
					break;
				case 2:
					System.out.println("Enter B# in the Form of B---");
					bNumber = sc.next();
					rsObj = pqObj.getStudentCourseDetails(bNumber);
					if (rsObj == null) {
						System.out.println("Message : " + pqObj.getMessage());
					} else {
						displayObj.printHeaderString(rsObj);
						System.out.println("\n");
						displayObj.printResults(rsObj);
						rsObj = null;
					}
					break;

				case 3:
					System.out.println("Enter dept_code --> ");
					String departmentCode = sc.next();
					System.out.println("Enter course_no --> ");
					int course = sc.nextInt();
					rsObj = null;
					rsObj = pqObj.getPrerequisiteCourses(departmentCode, course);
					if (rsObj != null) {
						displayObj.printHeaderString(rsObj);
						System.out.println("\n");
						displayObj.printResults(rsObj);
						rsObj = null;
					}
					break;

				case 4:
					System.out.println("Enter classid in the form of - c----   -->");
					String classId = sc.next();
					rsObj = null;
					rsObj = pqObj.getClassAndAllStudents(classId);

					if (rsObj == null) {
						System.out.println("Message: " + pqObj.getMessage());
					} else {
						displayObj.printHeaderString(rsObj);
						System.out.println("\n");
						displayObj.printResults(rsObj);
						rsObj = null;
					}

					break;
				case 5:
					System.out.println("Enter B# in the form of - B---");
					bNumber = sc.next();
					System.out.println("Enter Classid in the form of - c----");
					classId = sc.next();
					message = pqObj.enrollStudentIntoCourse(bNumber, classId);

					if (message != null) {
						System.out.println("Message : " + message);
					}

					break;
				case 6:
					System.out.println("Enter B# in the form of - B---");
					bNumber = sc.next();
					System.out.println("Enter Classid in the form of - C----");
					classId = sc.next();
					message = pqObj.dropStudentFromClass(bNumber, classId);
					if (message != null) {
						System.out.println("Message : " + message);
					}

					break;
				case 7:
					System.out.println("Enter B# in the form of - B---");
					bNumber = sc.next();
					message = pqObj.deleteStudent(bNumber);

					if (message != null) {
						System.out.println("Message : " + message);
					}

					break;
				case 8:
					pqObj.closeConnection();
					pqObj = null;
					System.exit(0);
					break;
				}
			}
		} catch (SQLException sqle) {
			System.out.println("An error ocurred while executing a query.");
			sqle.printStackTrace();
			System.exit(-1);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Class not found.");
			cnfe.printStackTrace();
			System.exit(-1);
		}

	}

	public static void showSubmenu(Scanner sc, Connection connIn, Display_Util displayObjIn, ProcessQuery pqObjIn)
			throws SQLException {
		ResultSet rsObjSM = null;
		while (true) {
			displayObjIn.displaySubmenu();
			int optionSubmenu = sc.nextInt();
			System.out.println("-------------------------------------------------\t");
			switch (optionSubmenu) {
			case 1:
				rsObjSM = pqObjIn.getAllStudentDetails();
				if (rsObjSM != null) {
					displayObjIn.printHeaderString(rsObjSM);
					System.out.println("\n");
					displayObjIn.printResults(rsObjSM);
					rsObjSM = null;
				} else {
					displayObjIn.printNoValueMessage("student");
				}
				rsObjSM = null;
				break;
			case 2:
				rsObjSM = pqObjIn.getAllCourses();
				if (rsObjSM != null) {
					displayObjIn.printHeaderString(rsObjSM);
					System.out.println("\n");
					displayObjIn.printResults(rsObjSM);
					rsObjSM = null;
				} else {
					displayObjIn.printNoValueMessage("course");
				}
				break;
			case 3:
				rsObjSM = pqObjIn.getAllCourseCredits();
				if (rsObjSM != null) {
					displayObjIn.printHeaderString(rsObjSM);
					System.out.println("\n");
					displayObjIn.printResults(rsObjSM);
					rsObjSM = null;
				} else {
					displayObjIn.printNoValueMessage("course credit");
				}
				break;
			case 4:
				rsObjSM = pqObjIn.getAllPreqrequisites();
				if (rsObjSM != null) {
					displayObjIn.printHeaderString(rsObjSM);
					System.out.println("\n");
					displayObjIn.printResults(rsObjSM);
					rsObjSM = null;
				} else {
					displayObjIn.printNoValueMessage("prerequisite");
				}
				
				break;
			case 5:
				rsObjSM = pqObjIn.getAllClasses();
				if (rsObjSM != null) {
					displayObjIn.printHeaderString(rsObjSM);
					System.out.println("\n");
					displayObjIn.printResults(rsObjSM);
					rsObjSM = null;
				} else {
					displayObjIn.printNoValueMessage("class");
				}
				
				break;
			case 6:
				rsObjSM = pqObjIn.getAllEnrollments();
				if (rsObjSM != null) {
					displayObjIn.printHeaderString(rsObjSM);
					System.out.println("\n");
					displayObjIn.printResults(rsObjSM);
					rsObjSM = null;
				} else {
					displayObjIn.printNoValueMessage("enrollment");
				}
				break;
			case 7:
				rsObjSM = pqObjIn.getAllGrades();
				if (rsObjSM != null) {
					displayObjIn.printHeaderString(rsObjSM);
					System.out.println("\n");
					displayObjIn.printResults(rsObjSM);
					rsObjSM = null;
				} else {
					displayObjIn.printNoValueMessage("grade");
				}
				
				break;
			case 8:
				rsObjSM = pqObjIn.getAllLogsData();
				if (rsObjSM != null) {
					displayObjIn.printHeaderString(rsObjSM);
					System.out.println("\n");
					displayObjIn.printResults(rsObjSM);
					rsObjSM = null;
				} else {
					displayObjIn.printNoValueMessage("log");
				}
				break;
			case 9:
				return;

			}

		}

	}
}

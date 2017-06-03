package dbfp.utilities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Formatter;

public class Display_Util {
	private final String formatStr="%-24s";
	
	public void displayMenu() {
		System.out.println("-------------------------------------------------\t");
		System.out.println("\tStudent Registration System\t");
		System.out.println("-------------------------------------------------");
		System.out.println("1) Display Table");
		System.out.println("2) Get Student Course Details");
		System.out.println("3) Get Course list for given prerequisite course");
		System.out.println("4) Get Class,Course Title and Student Information");
		System.out.println("5) Enroll a Student");
		System.out.println("6) Drop a Student");
		System.out.println("7) Delete a Student");
		System.out.println("8) Exit");
		System.out.println("-------------------------------------------------\t");
		System.out.println("\nSelect an option --> ");
		System.out.println("-------------------------------------------------\t");

	}

	public void displaySubmenu() {
		System.out.println("-------------------------------------------------\t");
		System.out.println("\n\tDisplay Table\t");
		System.out.println("-------------------------------------------------");
		System.out.println("1) Students");
		System.out.println("2) Courses");
		System.out.println("3) Course_Credits");
		System.out.println("4) Prerequisites");
		System.out.println("5) Classes");
		System.out.println("6) Enrollments");
		System.out.println("7) Grades");
		System.out.println("8) Logs");
		System.out.println("9) Go back to main menu");
		System.out.println("-------------------------------------------------\t");

	}

	public void printHeaderString(ResultSet rs) throws SQLException {
		Formatter formatter=new Formatter();
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			System.out.print(String.format(formatStr, rsmd.getColumnLabel(i)));
		}
		rsmd = null;
		formatter.close();
		formatter=null;
		
	}

	public void printResults(ResultSet rs) throws SQLException {
		Formatter formatter=new Formatter();
		int count = rs.getMetaData().getColumnCount();
		while(rs.next()){
			for (int i = 1; i <= count; i++) {
				System.out.print( String.format(formatStr, rs.getString(i)));
			}
			System.out.print("\n");
		}
		formatter.close();
	}

	public void printNoValueMessage(String stringIn) {
		System.out.println("No "+stringIn+" values to show.");
	}

}

package dbfp.student_db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleTypes;

public class ProcessQuery {

	private final String studentAndCourseDetailsSP = "{call dbprocedure.get_students_course_details(?,?,?)}";
	private final String dropPrereqTableQuery = "drop table prerequisites_temp";
	private final String createPrereqTableQuery = "create table prerequisites_temp(pre_dept_code varchar2(4), pre_course_no number(3))";
	private final String showPrerqSP = "{call dbprocedure.get_prerequisites_rev(?, ?, ?)}";
	private final String showClassAndAllStudentsSP = "{call dbprocedure.get_class_course_student(?,?,?)}";
	private final String enrollStudentIntoClassSP = "{call dbprocedure.enroll_student(?,?,?)}";
	private final String dropStudentFromCourseSP = "{call dbprocedure.drop_student(?,?,?)}";
	private final String deleteStudent = "{call dbprocedure.delete_student(?,?)}";
	private final String showAllStudentsSP="{call dbprocedure.show_students(?)}";
	private final String showAllCoursesSP="{call dbprocedure.show_courses(?)}";
	private final String showAllCourseCreditsSP="{call dbprocedure.show_course_credit(?)}";
	private final String showAllPrequisitesSP="{call dbprocedure.show_prerequisites(?)}";
	private final String showAllClassesSP="{call dbprocedure.show_classes(?)}";
	private final String showAllEnrollmentsSP="{call dbprocedure.show_enrollments(?)}";
	private final String showAllGradesSP="{call dbprocedure.show_grades(?)}";
	private final String showAllLogsData="{call dbprocedure.show_logs(?)}";
	private final String commitQuery="commit";
	
	private Connection connectionObj = null;
	private String message = null;
	private ResultSet rsObj = null;

	public ProcessQuery(Connection createConnectionIn) {
		connectionObj = createConnectionIn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultSet getStudentCourseDetails(String bNumberIn) throws SQLException {
		rsObj = null;
		CallableStatement cs = connectionObj.prepareCall(studentAndCourseDetailsSP);
		//CallableStatement cs =(CallableStatement) connectionObj.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		cs.setString(1, bNumberIn);
		cs.registerOutParameter(2, OracleTypes.VARCHAR);
		cs.registerOutParameter(3, OracleTypes.CURSOR);
		cs.execute();
		String msg = cs.getString(2);
		
		if (msg != null ) {
			setMessage(msg);
		} else {
			rsObj = (ResultSet) cs.getObject(3);
		}
		cs = null;
		return rsObj;

	}

	public ResultSet getPrerequisiteCourses(String departmentCodeIn, int courseNumIn) throws SQLException {
		rsObj = null;
		Statement stmt = connectionObj.createStatement();
		stmt.executeQuery(dropPrereqTableQuery);
		stmt.executeQuery(createPrereqTableQuery);
		stmt = null;
		CallableStatement cs = connectionObj.prepareCall(showPrerqSP);
		cs.setString(1, departmentCodeIn);
		cs.setInt(2, courseNumIn);
		cs.registerOutParameter(3, OracleTypes.CURSOR);
		cs.execute();
		rsObj = (ResultSet) cs.getObject(3);
		cs = null;
		return rsObj;
	}

	public ResultSet getClassAndAllStudents(String classId) throws SQLException {
		rsObj = null;
		CallableStatement cs = connectionObj.prepareCall(showClassAndAllStudentsSP);
		cs.setString(1, classId);
		cs.registerOutParameter(2, OracleTypes.VARCHAR);
		cs.registerOutParameter(3, OracleTypes.CURSOR);
		cs.execute();
		String msg = cs.getString(2);
		if (msg != null) {
			setMessage(msg);
		} else {
			rsObj = (ResultSet) cs.getObject(3);
		}
		cs = null;
		return rsObj;
	}

	public String enrollStudentIntoCourse(String bNumber, String classId) throws SQLException {
		CallableStatement cs = connectionObj.prepareCall(enrollStudentIntoClassSP);
		cs.setString(1, bNumber);
		cs.setString(2, classId);
		cs.registerOutParameter(3, OracleTypes.VARCHAR);

		cs.execute();

		String msgVar = cs.getString(3);
		cs = null;
		return msgVar;
	}

	public String dropStudentFromClass(String bNumber, String classId) throws SQLException {
		CallableStatement cs = connectionObj.prepareCall(dropStudentFromCourseSP);
		cs.setString(1, bNumber);
		cs.setString(2, classId);
		cs.registerOutParameter(3, OracleTypes.VARCHAR);

		cs.execute();

		String msgVar = cs.getString(3);
		cs = null;
		return msgVar;
	}

	public String deleteStudent(String bNumber) throws SQLException {
		CallableStatement cs = connectionObj.prepareCall(deleteStudent);
		cs.setString(1, bNumber);
		cs.registerOutParameter(2, OracleTypes.VARCHAR);
		cs.execute();
		String mssg4 = cs.getString(2);
		return mssg4;
	}
	

	public ResultSet getAllStudentDetails() throws SQLException {
		rsObj=null;
		CallableStatement cs = connectionObj.prepareCall(showAllStudentsSP);
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rsObj = (ResultSet) cs.getObject(1);
		cs=null;
		return rsObj;
	}

	public ResultSet getAllCourses() throws SQLException {
		rsObj=null;
		CallableStatement cs = connectionObj.prepareCall(showAllCoursesSP);
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rsObj = (ResultSet) cs.getObject(1);
		cs=null;
		return rsObj;
	}

	public ResultSet getAllCourseCredits() throws SQLException {
		rsObj=null;
		CallableStatement cs = connectionObj.prepareCall(showAllCourseCreditsSP);
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rsObj = (ResultSet) cs.getObject(1);
		cs=null;
		return rsObj;
	}
	
	public ResultSet getAllPreqrequisites() throws SQLException {
		rsObj=null;
		CallableStatement cs = connectionObj.prepareCall(showAllPrequisitesSP);
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rsObj = (ResultSet) cs.getObject(1);
		cs=null;
		return rsObj;
	}

	public ResultSet getAllClasses() throws SQLException {
		rsObj=null;
		CallableStatement cs = connectionObj.prepareCall(showAllClassesSP);
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rsObj = (ResultSet) cs.getObject(1);
		cs=null;
		return rsObj;
	}

	public ResultSet getAllEnrollments() throws SQLException {
		rsObj=null;
		CallableStatement cs = connectionObj.prepareCall(showAllEnrollmentsSP);
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rsObj = (ResultSet) cs.getObject(1);
		cs=null;
		return rsObj;
	}

	public ResultSet getAllGrades() throws SQLException {
		rsObj=null;
		CallableStatement cs = connectionObj.prepareCall(showAllGradesSP);
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rsObj = (ResultSet) cs.getObject(1);
		cs=null;
		return rsObj;
	}
	
	public ResultSet getAllLogsData() throws SQLException {
		rsObj=null;
		CallableStatement cs = connectionObj.prepareCall(showAllLogsData);
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rsObj = (ResultSet) cs.getObject(1);
		cs=null;
		return rsObj;
	}
	
	public void commitToDB() throws SQLException{
		CallableStatement cs=connectionObj.prepareCall(commitQuery);
		cs.executeQuery();
		cs.close();
		cs=null;
	}
	
	public void closeConnection() throws SQLException {
		rsObj.close();
		rsObj=null;
		if (connectionObj != null)
			connectionObj.close();
	}
	

}

package dbfp.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
	
	private final String driverName="oracle.jdbc.driver.OracleDriver";
	private final String connectionUrl="jdbc:oracle:thin:@localhost:1521/orcl";
	private final String username="system";
	private final String password="Cls12345";
	private Connection connectionObj=null;
	
	public Connection createConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driverName);
		connectionObj = DriverManager.getConnection(connectionUrl,username,password);	
		setConnectionObj(connectionObj);
		return connectionObj;
	}

	public void closeConnection() throws SQLException{
		connectionObj.close();
	}
	
	public Connection getConnectionObj() {
		return connectionObj;
	}

	public void setConnectionObj(Connection connectionObjIn) {
		this.connectionObj = connectionObjIn;
	}
}

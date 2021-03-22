package MysqlHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

import constants.DocumentField;

public class MySQLConnectorUlti{
	private static final LinkedList<Connection> availableConnections = new LinkedList<Connection>();
	 
    private int maxConnection;

	public static Connection getConnection(){
		  Connection connection = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = DriverManager.getConnection(DocumentField.DB_URL, DocumentField.DB_USER, DocumentField.DB_PASSWD);
	            
	            System.out.println("connect successfully!");
	        } catch (Exception ex) {
	            System.out.println("connect failure!");
	            ex.printStackTrace();
	        }
	        return connection;
	}

	public boolean releaseConnection(Connection connection) {
		// TODO Auto-generated method stub
		return false;
	}
}

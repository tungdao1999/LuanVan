package MysqlHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constants.DocumentField;

public class MysqlAction {

		public static void insertToDatabase(String dayUpload,String fileName,String filePath,String linkWeb,String fileType,int isTrain) {
			Connection connection = MySQLConnectorUlti.getConnection();
			
			String query = "{CALL insert_document(?,?,?,?,?,?,@docId)}";
			
			try {
				CallableStatement stmt = connection.prepareCall(query);
				stmt.setString(1, dayUpload);
				stmt.setString(2, fileName);
				stmt.setString(3, filePath);
				stmt.setString(4, linkWeb);
				stmt.setString(5, fileType);
				stmt.setInt(6, 0);
				
				stmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public static int searchDocumentId(String fileName) {
			int result = 0;
			Connection connection = MySQLConnectorUlti.getConnection();
			
			String sql = "select iddocument from document where fileName = ? and isTrain = 1;";
			
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				
				ps.setString(1, fileName);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
}

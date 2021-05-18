package MysqlHelper;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import constants.DocumentField;
import entry.Document;

public class MysqlAction {
	
		private static Connection connection = MySQLConnectorUlti.getConnection(); 

		public static void insertToDatabase(Date dayUpload,String fileName,String filePath,String linkWeb,
				String labelSource, String fileType,String pharseState) {
			
	//		ResultSet rs = null;
			
			PreparedStatement ppst = null;
			
	//		int resultId = 0;
			
			String sql = "INSERT INTO document2(dayUpload,fileName,filePath,linkWeb,fileType,phraseState, labelSource) "+ "VALUES(?,?,?,?,?,?,?)";
			
			try {
				
				
				ppst = connection.prepareStatement(sql);
				
				ppst.setDate(1,dayUpload);
				ppst.setString(2, fileName);
				ppst.setString(3, filePath);
				ppst.setString(4, linkWeb);
				ppst.setString(5, fileType);
				ppst.setString(6, pharseState);
				ppst.setString(7, labelSource);
				
				ppst.execute();
				
//				rs = ppst.getGeneratedKeys();
//				
//
//				if(rs.next()) {
//					System.out.println("id number is" + rs.getInt(1));
//				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			
			}
			
//			return resultId;
			return;
			
		}
		public static int searchDocumentId(String fileName) {
			int result = 0;
			//Connection connection = MySQLConnectorUlti.getConnection();
			
			String sql = "select docid from document2 where fileName = ? and phraseState = ?;";
			
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				
				ps.setString(1, fileName);
				//just for testing
		//		ps.setString(2, "indexed");
				ps.setString(2, "processed");
				
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
		public static void updateTrainState(int docId) {
			// TODO Auto-generated method stub
			
			
		}
		public static Queue<String> getPreprocessedDirectory() {
			// TODO Auto-generated method stub
			Queue<String> queue = new LinkedList<String>();
			
			String statement = "Select dirName from directory where phrase = ?  and isTrained = ?";
			
			PreparedStatement prstmt;
			try {
				prstmt = connection.prepareStatement(statement);
				prstmt.setString(0, "preprocessed");
				prstmt.setInt(2, 0);
				ResultSet rs = prstmt.executeQuery();
				
				while(rs.next()) {
					queue.add(rs.getString("dirName"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return queue;
		}
		public static int getMergeVersion() throws SQLException {
			// TODO Auto-generated method stub
//			String statement = "Select Count(LogID) from TrainLog where state = ?";
//			PreparedStatement prstmt = connection.prepareStatement(statement);
//			prstmt.setString(1, "sucess");
//			ResultSet rs = prstmt.executeQuery();
//			int version = rs.getInt(0);
//			
//			
//			return version + 1;
			return 0;
		}
		public static List<Document> getWaitingFileToTrain() throws SQLException {
			// TODO Auto-generated method stub
			String statement = "Select * from document2 where phraseState = ?";
			PreparedStatement prstmt = connection.prepareStatement(statement);
			prstmt.setString(1, "indexed");
			List<Document> listDoc = new ArrayList<Document>();
			ResultSet rs = prstmt.executeQuery();
			while(rs.next()) {
				Document doc = new Document();
				doc.setDocID(rs.getInt("docID"));
				doc.setFilePath(rs.getString("filePath"));
				doc.setNumLine(rs.getInt("numLine"));
				doc.setSourceLabel(rs.getString("labelSource"));
				listDoc.add(doc);
			}
			return listDoc;
		}
		public static void updateIndexingState(String name, int numLine) throws SQLException {
			// TODO Auto-generated method stub
			String statement  =  "update document2 set numLine = ?, phraseState = ? where fileName = ?";
			PreparedStatement prstmt = connection.prepareStatement(statement);
			prstmt.setInt(1, numLine);
			prstmt.setString(2, "indexed");
			prstmt.setString(3, name);
			prstmt.execute();
			
		}
		public static void updatePreprocessState(String name) throws SQLException {
			// TODO Auto-generated method stub
			String statement  =  "update document2 set phraseState = ? where fileName = ?";
			PreparedStatement prstmt = connection.prepareStatement(statement);
			prstmt.setString(1, "processed");
			prstmt.setString(2, name);
			prstmt.execute();
		}
}

package temporarySearchegine;

import java.io.File;
import java.sql.Date;

import MysqlHelper.MysqlAction;
import constants.Filepath;

// use this class only for temporary test
// must be update later
public class UploadFileHandler {
	
	public void updateUploadFileState() {
		File uploadFileDir = new File(Filepath.uploadFile);
		File[] newUpload = uploadFileDir.listFiles();
		System.out.println(newUpload.length);
		for (File file : newUpload)  {
			Date CreatedDate= new Date(System.currentTimeMillis());
			MysqlAction.insertToDatabase(CreatedDate, file.getName(), file.getPath(), "uploaded", "UPL", "docx", "crawled");
		}
	}
	public static void main(String[] args) {
		UploadFileHandler ufh = new UploadFileHandler();
		ufh.updateUploadFileState();
	}
	
}

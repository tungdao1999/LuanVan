package temporarySearchegine;

import java.sql.SQLException;

import preprocess.PreprocessingProcess;

public class PreprocessBot {
	
	public void run() {
		PreprocessingProcess pp = new PreprocessingProcess();
		
		
		try {
			//pp.preprocessingITViec();
			//pp.preprocessingViblo();
			pp.preprocessingUploadFile();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		PreprocessBot bot = new PreprocessBot();
		bot.run();
	}

}

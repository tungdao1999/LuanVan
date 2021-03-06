package searchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class HtmlGatherer implements IDataGatherer {

	public void saveFile(String content) {
		// TODO Auto-generated method stub
		
	}

	public String saveInfor(String information) {
		// TODO Auto-generated method stub
		return null;
	}
	public String WriteToFile(String content) {
		File out = new File("outputDoc/result.txt");
		String path = out.getAbsolutePath();
		OutputStreamWriter writer;
		try {
			
			//block 1
			writer = new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8);
			
			//block 2
			writer.write(content);
			writer.close();
			
		} catch (FileNotFoundException e) {
			// cath block 1
			e.printStackTrace();
		} catch (IOException e) {
			// catch block 2
			e.printStackTrace();
		}
		
		return path;
	}
}

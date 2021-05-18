package preprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class TextFileParser implements TextParser{

	public String convertToText(String path) throws IOException {
		// TODO Auto-generated method stub
		
		StringBuffer sb = new StringBuffer();
		
		BufferedReader reader = new BufferedReader(new FileReader(new File(path), Charset.forName("UTF-8")));
		
		String line = "";
		while((line = reader.readLine()) !=null) {
			sb.append(line);
		}
		reader.close();
		return sb.toString().toLowerCase();
	}
	
}

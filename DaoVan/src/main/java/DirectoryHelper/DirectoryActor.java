package DirectoryHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DirectoryActor {
	public static void writeFile(String content,String filePath) throws IOException {
		File out = new File(filePath);
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8);
	
		writer.write(content);
		
		writer.close();
	}
	public static List<File> getPathOfTextCrawled(String directoryPath) {
		File[] files = new File(directoryPath).listFiles();
		
		List<File> listLink = new ArrayList<File>();
		
		for (int i = 0; i < files.length; i++) {
		
			listLink.add(files[i]);
		
		}
		
		return listLink;
	}
	public static void deleteFile(String path) {
		File file = new File(path);
		file.delete();
	}
	public static String readFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String result = "";
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    
		    }
		    
		    result = sb.toString();
		} finally {
		    br.close();
		}
		return result;
	}
}

package resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourcesUtils {
	public static File resourcesDirectory = new File("src/main/resources");
	public static String resourcePath = resourcesDirectory.getAbsolutePath();
	
	public static List<String> getResourceFileData(String path) throws IOException {
		String line;
		int count  = 0;
		List<String> data = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File(ResourcesUtils.resourcePath + path)));
		while ((line = br.readLine()) != null) {
			//data.add(line);
			count ++;
		}
		System.out.println(count);
		br.close();
		return data;
	}
	public static void main(String[] args) throws IOException {
		getResourceFileData("/Doc2VecModel/dataFileToTrain/mergeV0.txt");
	}
	
	
	

}

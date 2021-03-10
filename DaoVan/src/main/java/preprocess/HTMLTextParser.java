package preprocess;

import java.io.IOException;

import DirectoryHelper.DirectoryActor;

public class HTMLTextParser implements TextParser{

	public String convertToText(String path) {
		String content = "";
		try {
			content = DirectoryActor.readFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}

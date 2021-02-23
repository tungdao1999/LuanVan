package preprocess;

import java.io.IOException;
import java.util.List;

import resource.ResourcesUtils;

public class StopWordRemoval extends TextProcessing{
	
	public static List<String> listStopWords = getListStopWord();
	public StopWordRemoval(ITextProcessor itp) {
		super(itp);
		// TODO Auto-generated constructor stub
	}

	private static List<String> getListStopWord() {
		// TODO Auto-generated method stub
		try {
			return ResourcesUtils.getResourceFileData("PreProcess/VN_stopWords.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String preProcessing(String sourcePath) {
		// TODO Auto-generated method stub
		return removeByLibrary(this.itp.preProcessing(sourcePath));
	}
	public String removeByLibrary(String text) {
		String word = "";
		for (int i = 0; i < listStopWords.size(); i++) {
			word = listStopWords.get(i);
			if(text.contains(word))
				text = text.replaceAll(word, "");
		};
		return text;
	}
	

}

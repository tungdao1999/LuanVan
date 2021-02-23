package preprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jvntextpro.JVnTextPro;
import resource.ResourcesUtils;

public class PreprocessHelper {
	private static String preprocess_dir = "/PreProcess";

	public static String HTMLRegex = "<[^>]*>";
	
	public static String symbolRegex = "";
	
	public static List<String> htmlTags =getHTMLTags();

	public static List<String> stopWords = getStopWord();
	
	public static List<String> endLettersOfSentence() throws IOException {
		return ResourcesUtils.getResourceFileData(preprocess_dir + "/endLetters.txt");
	}

	public static String delimeters() throws IOException {
		List<String> endLetters = endLettersOfSentence();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < endLetters.size(); i++) {
			sb.append(endLetters.get(i));
		}
		sb.delete(sb.length() - 2, sb.length() - 1);
		return sb.toString();
	}
	public static String removeSymbol(String str) {
		str = str.replaceAll(symbolRegex, "");
		return str;
	}
	public static String removeHTML(String str) throws IOException {
		if (str.contains("<") && str.contains(">")) {
			int firstArrow = str.indexOf("<");
			int secArrow = str.indexOf(">");
			
			String content = str.substring(firstArrow, secArrow + 1);
			for (int i = 0; i < htmlTags.size(); i++) {
				if(content.contains(htmlTags.get(i))) {
					str = str.replaceAll(HTMLRegex, "");
				}
			}	
		}
		return str;
	}
	

	public static List<String> getHTMLTags() {
		try {
			return ResourcesUtils.getResourceFileData(preprocess_dir + "/HTMLTags.txt");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static List<String> getStopWord(){
		try {
			return ResourcesUtils.getResourceFileData(preprocess_dir + "/VN_stopWords.txt");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void segmentVietNamese(File f) {
		String path = ResourcesUtils.resourcePath;
		
		JVnTextPro textpro = new JVnTextPro();
		textpro.initSenSegmenter(path + "/models/jvnsensegmenter");
		textpro.initSenTokenization();
		textpro.initSegmenter(path + "/models/jvnsegmenter");
		// textpro.initPosTagger(path + "/models/jvnpostag/maxent");

		System.out.println(textpro.process(f));
	}
	public static void removeStopWords(String text) {
		for (String string : stopWords) {
			String word = " " + string;
			String text_temp = text;
			if(text.contains(word)) {
				System.out.println(string);
				text_temp = text_temp.replace(word, "");
				System.out.println(text_temp);
			}
		}
	}
	public static void main(String[] args) {
		String text = "Xử lý ngôn ngữ tự nhiên là một nhánh của lĩnh vực trí tuệ nhân tạo và có thể nói đây là phần khó nhất trong lĩnh vực trí tuệ nhân tạo";
String path = ResourcesUtils.resourcePath;
		
		JVnTextPro textpro = new JVnTextPro();
		textpro.initSenSegmenter(path + "/models/jvnsensegmenter");
		textpro.initSenTokenization();
		textpro.initSegmenter(path + "/models/jvnsegmenter");
		// textpro.initPosTagger(path + "/models/jvnpostag/maxent");

		System.out.println(textpro.process(text));
		removeStopWords(text);
		System.out.println(text);
	}

}

package preprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import resource.ResourcesUtils;

public class test {

	public static void main(String[] args) throws IOException {
//		 TODO Auto-generated method stub
		TextParser parser = new OfficeWordParser();
		TextParserFactory factory = new TextParserFactory(parser);
		ITextProcessor processor = new StopWordRemoval(new TextTokenizer(new TextCleaner(factory)));
		String a = processor.preProcessing("D://group11_requirement_ver1.0 .docx");
		System.out.println(a);
	}

}

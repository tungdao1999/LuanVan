package preprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import resource.ResourcesUtils;

public class test {

	public static void main(String[] args) throws IOException {
		String INDEX_DIR ="D://VN_stopWords.txt";
//		 TODO Auto-generated method stub
//		TextParser parser = new OfficeWordParser();
//		TextParserFactory factory = new TextParserFactory(parser);
//		ITextProcessor processor = new TextTokenizer(new TextCleaner(factory));
		
		TextParser parser = new HTMLTextParser();
		TextParserFactory factory = new TextParserFactory(parser);
		ITextProcessor processor = new VNmeseSegmenter(new TextCleaner(new TextTokenizer(factory)));
		
		String a = processor.preProcessing(INDEX_DIR);
		System.out.println(a);
	}

}

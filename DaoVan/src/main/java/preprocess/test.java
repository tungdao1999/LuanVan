package preprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import resource.ResourcesUtils;

public class test {

	public static void main(String[] args) throws IOException {
		String INDEX_DIR = ResourcesUtils.resourcePath + "/crawled/ITViec/0 11 tài liệu lập trình C++ miễn phí chất nhất.txt";
//		 TODO Auto-generated method stub
//		TextParser parser = new OfficeWordParser();
//		TextParserFactory factory = new TextParserFactory(parser);
//		ITextProcessor processor = new TextTokenizer(new TextCleaner(factory));
		
		TextParser parser = new HTMLTextParser();
		TextParserFactory factory = new TextParserFactory(parser);
		ITextProcessor processor = new TextTokenizer(new TextCleaner(factory));
		
		String a = processor.preProcessing(INDEX_DIR);
		System.out.println(a);
	}

}

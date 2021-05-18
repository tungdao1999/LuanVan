package preprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import resource.ResourcesUtils;

public class test {

	public static void main(String[] args) throws IOException {
		String PATH ="D:\\Luan van tot nghiep\\Luan van mau\\Luan van tot nghiep_Viet_Khang_K2016.docx";
//		 TODO Auto-generated method stub
		TextParser parser = new OfficeWordParser();
//		TextParser parser = new TextFileParser();
		TextParserFactory factory = new TextParserFactory(parser);
		ITextProcessor processor = new TextTokenizer(new TextCleaner(factory));
		
//		TextParser parser = new HTMLTextParser();
//		TextParserFactory factory = new TextParserFactory(parser);
//		ITextProcessor processor = new VNmeseSegmenter(new TextCleaner(new TextTokenizer(factory)));
//		
		String a = processor.preProcessing(PATH);
		System.out.println(a);
	}

}

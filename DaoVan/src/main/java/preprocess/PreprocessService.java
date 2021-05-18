package preprocess;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DirectoryHelper.DirectoryActor;
import constants.DocumentField;
import constants.Filepath;
import entry.Sentence;
import printer.SentenceAddressHelper;
import resource.ResourcesUtils;

public class PreprocessService {
	static TextParser parser;
	static TextParserFactory factory;
	static ITextProcessor processor;
	
	public static String PreprocessRawTextFile(String filePath) {	
		if((processor == null) & (!(parser instanceof TextFileParser))){
			parser = new TextFileParser();
			factory = new TextParserFactory(parser);
			processor = new VNmeseSegmenter(new TextCleaner(new TextTokenizer(factory)));
		}
		return processor.preProcessing(filePath);
	}
	public static String preProcessText(String text) {
		if((processor == null) & (!(parser instanceof RawTextParser))){
			parser = new RawTextParser();
			factory = new TextParserFactory(parser);
			processor = new VNmeseSegmenter(new TextCleaner(new TextTokenizer(factory)));
		}
		return processor.preProcessing(text);
	}
	public static String PreprocessRawDocFile(String filePath) {
		// TODO Auto-generated method stub
		if((processor == null) & (!(parser instanceof OfficeWordParser))){
			parser = new OfficeWordParser();
			factory = new TextParserFactory(parser);
			processor = new VNmeseSegmenter(new TextCleaner(new TextTokenizer(factory)));
		}
		return processor.preProcessing(filePath);
	}
	public static void PreprocessCheckedFile(String filepath, String preSegmentFile,String preProcessedFile) throws IOException {
		parser = new OfficeWordParser();
	//	parser = new TextFileParser();
		factory = new TextParserFactory(parser);
		processor = new TextTokenizer(new TextCleaner(factory));
	//	File checkedFile = new File(filepath);
		
		String checkedFileContent = processor.preProcessing(filepath);
		//checkedFileContent = new TextCleaner(null).removeBlankRow(checkedFileContent);
		
		DirectoryActor.writeFile(checkedFileContent, preSegmentFile);
//		
//		ArrayList<String> contentList = new ArrayList<String>(Arrays.asList(checkedFileContent.split("\n")));
		
	//	List<String> contentList = Arrays.asList(checkedFileContent.split("\n"));
		
//		for (int i = 0; i < contentList.size(); i++) {
//			if(!contentList.get(i).contains(TextCleaner.symbol)) {
//				contentList.remove(i);
//			}
//		}
//		String joined = String.join("\n", contentList);
//		DirectoryActor.writeFile(joined, preSegmentFile);
		
		factory.setParser(new RawTextParser());
		processor = new VNmeseSegmenter(factory);
		checkedFileContent = processor.preProcessing(checkedFileContent);
		
		DirectoryActor.writeFile(checkedFileContent, preProcessedFile);
		
//		return checkedFileContent;
		
		
	}
//	private static void createdLogFile(String checkedFileContent,File checkedFile,File logFile) {
//		// TODO Auto-generated method stub
//		String[] tokensContent = checkedFileContent.split("\n");
//		SentenceAddressHelper sah  = new SentenceAddressHelper();
//		sah.open(checkedFile);
//		Sentence sent  = null;
//		
//		
//		for (int tokenIndex = 0; tokenIndex < tokensContent.length; tokenIndex++) {
//			//sent = HightLightHelper.getSentenceAddress();
//		}
		
//	}
	public static void main(String[] args) throws IOException {
		
		PreprocessCheckedFile("D://luanvan1.doc", ResourcesUtils.resourcePath + "/tempoData/presegment.txt", 
				ResourcesUtils.resourcePath + "/tempoData/preprocess.txt");
	}

}

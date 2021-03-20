package preprocess;

public class PreprocessService {
	static TextParser parser = new HTMLTextParser();
	static TextParserFactory factory = new TextParserFactory(parser);
	static ITextProcessor processor = new VNmeseSegmenter(new TextCleaner(new TextTokenizer(factory)));
	public static String PreprocessRawTextFile(String file) {	
		return processor.preProcessing(file);
	}

}

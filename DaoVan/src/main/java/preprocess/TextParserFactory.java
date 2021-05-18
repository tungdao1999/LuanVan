package preprocess;

import java.io.IOException;

public class TextParserFactory implements ITextProcessor{
	private TextParser parser;
	
	public TextParser getParser() {
		return parser;
	}

	public void setParser(TextParser parser) {
		this.parser = parser;
	}


	public TextParserFactory(TextParser parser) {
		super();
		this.parser = parser;
	}

	public String preProcessing(String sourcePath) {
		
		try {
			return this.parser.convertToText(sourcePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}

package preprocess;

import java.util.List;

public class TextCleaner extends TextProcessing{
	//private static List<String> htmlTags = getHTMLTags();
	private static String HTML_REGEX = "<[^>]*>";
	private static String SYMBOL_REGEX = "<[^>]*>";
	
	public TextCleaner(ITextProcessor itp) {
		super(itp);
	}

	private static List<String> getHTMLTags() {
		// TODO Auto-generated method stub
		
		return null;
	}

	public String preProcessing(String sourcePath) {
		// TODO Auto-generated method stub
		return cleanText(this.itp.preProcessing(sourcePath));
	}
	public String cleanText(String text) {
		return removeHTMLTag(removeBlankRow(text));
	}
	public String removeHTMLTag(String text) {
		text = text.replaceAll(HTML_REGEX, "");
		return text;
	}
	public String removeBlankRow(String text) {
		text = text.replaceAll("\n\n", "");
		return text;
	}
	public String removeSymbol(String text) {
		text = text.replaceAll(SYMBOL_REGEX, "");
		return text;
	}

}

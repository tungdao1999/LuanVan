package preprocess;

import jvntextpro.JVnTextPro;
import resource.ResourcesUtils;

public class TextTokenizer extends TextProcessing{
	private static String END_REGEX = "\\.|\\?|\\!";

	public TextTokenizer(ITextProcessor itp) {
		super(itp);
		// TODO Auto-generated constructor stub
	}

	public String preProcessing(String sourcePath) {
		
		return tokenize((itp.preProcessing(sourcePath)));
	}
	public String tokenize(String text) {
		return splitVNmeseWord(splitSentences(text));
	}
	public String splitVNmeseWord(String text) {
		JVnTextPro textpro = new JVnTextPro();
		textpro.initSenSegmenter(ResourcesUtils.resourcePath + "/PreProcess/"+ "models/jvnsensegmenter");
		textpro.initSenTokenization();
		textpro.initSegmenter(ResourcesUtils.resourcePath + "/PreProcess/"+ "models/jvnsegmenter");
		textpro.initPosTagger(ResourcesUtils.resourcePath + "/PreProcess/"+ "models/jvnpostag/maxent");
		return textpro.process(text);
	}
	public String splitSentences(String text) {
		return text.replaceAll(END_REGEX, "\n");
	}

}

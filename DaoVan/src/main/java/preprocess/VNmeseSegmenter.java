package preprocess;

import jvntextpro.JVnTextPro;
import resource.ResourcesUtils;

public class VNmeseSegmenter extends TextProcessing {
	private static JVnTextPro textpro = new JVnTextPro();
	static {
		textpro.initSegmenter(ResourcesUtils.resourcePath + "/PreProcess/" + "models/jvnsegmenter");
	}

	public VNmeseSegmenter(ITextProcessor itp) {
		super(itp);
		// TODO Auto-generated constructor stub
	}

	public String preProcessing(String text) {
		// TODO Auto-generated method stub
		return splitVNmeseWord(itp.preProcessing(text));
	}

	public String splitVNmeseWord(String text) {
		
		return textpro.process(text);
	}

}

package preprocess;

import java.util.stream.IntStream;

import jvntextpro.JVnTextPro;
import resource.ResourcesUtils;

public class TextTokenizer extends TextProcessing{
	private static String END_REGEX = "(?=\\. [A-Z]|\\? [A-Z]|\\! [A-Z])";


	public TextTokenizer(ITextProcessor itp) {
		super(itp);
		// TODO Auto-generated constructor stub
	}

	public String preProcessing(String sourcePath) {
		return tokenize((itp.preProcessing(sourcePath)));
	}
	public String tokenize(String text) {
		return (splitSentences(text));
	}

	public String splitSentences(String text) {
		StringBuilder sb = new StringBuilder();
		for (String string : text.split(END_REGEX)) {
			sb.append(string + "\n");
		}
		return sb.toString();
	}
	public static void main(String[] args) {
			String text = 
					"a) Khái niệm: Câu kể (còn gọi là câu trần thuật) là những câu dùng để:\r\n" + 
					"- Kể, tả hoặc giới thiệu về sự vật, sự việc.\r\n" + 
					"- Nói lên ý nghĩa hoặc tâm tư, tình cảm. \r\n" + 
					"- Cuối câu kể đặtdấu chấm.\r\n" + 
					"b) Câu đơn: Câu đơn là câu do một cụm chủ ngữ – vị ngữ (gọi tắt là cụm chủ vị) tạo thành.\r\n" + 
					"VD: Mùa xuân // đã về.";
			String processed = PreprocessService.preProcessText(text);
			System.out.println("processed : " + processed);
	}
}

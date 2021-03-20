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
		return (splitSentences(text));
	}

	public String splitSentences(String text) {
		return text.replaceAll(END_REGEX, "\n");
	}
	public static void main(String[] args) {
		String text = "Tại bài viết Tài khoản người sử dụng và phân quyền truy cập tệp trên Ubuntu (P1) mình đã giới thiệu về người sử dụng và quản lý tài khoản người sử dụng trên Ubuntu. Trong bài viết lần này, mình sẽ trình bày về quản lý quyền truy cập trên Ubuntu.\r\n" + 
				"\r\n" + 
				"Các nhóm người sử dụng\r\n" + 
				"Mỗi tệp hay thư mục luôn thuộc về một người sử dụng và một nhóm xác định. Mỗi file bao gồm 3 nhóm người sử dụng. Người tạo ra chúng sẽ thuộc nhóm người sở hữu (owner), còn nhóm chứa người tạo ra file hay thư mục đó thì sẽ thuộc nhóm sở hữu (group). Bất kì người sử dụng nào không phải chủ sở hữu hoặc nhóm sở hữu thì sẽ thuộc nhóm những người sử dụng khác (others). Mỗi nhóm";
		text = text.replaceAll(END_REGEX, "\n");
		System.out.println(text);
		text = text.replaceAll(TextCleaner.SYMBOL_REGEX, "");
		System.out.println("--------------------------");
		System.out.println(text = text.replaceAll("\r\n", ""));
		System.out.println(new VNmeseSegmenter(null).splitVNmeseWord(text));
		
	}
}

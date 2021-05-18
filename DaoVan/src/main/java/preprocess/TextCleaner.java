package preprocess;

import java.util.List;
import java.util.regex.Pattern;

public class TextCleaner extends TextProcessing{
	//private static List<String> htmlTags = getHTMLTags();
	private static String HTML_REGEX = "<[^>]*>";
	public static String SYMBOL_REGEX =  "[/+-@#$%^&*(),.'\\\"”“:{}|\\[\\]<>/]";
	public static String symbol = "[^abcdefghijklmnopqrstuvxyzàáâãèéêìíòóôõùúăđĩũơưạảấầẩẫậắằẳẵặẹẻẽếềểễệỉịọỏốồổỗộớờởỡợụủứừửữự"
			+ "ABCDEFGHIJKLMNOPQRSTUVXYZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼẾỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰ\n\t"
			+ " 0123456789]";
//	public static String symbol = "[^A-Za-z0-9]";
	
	public TextCleaner(ITextProcessor itp) {
		super(itp);
	}

	public String preProcessing(String sourcePath) {
		// TODO Auto-generated method stub
		System.out.println("clean: " +sourcePath);
		return cleanText(this.itp.preProcessing(sourcePath));
	}
	public String cleanText(String text) {
		return removeBlankRow(removeSymbol(removeHTMLTag((text))));
	}
	public String removeHTMLTag(String text) {
		text = text.replaceAll(HTML_REGEX, "");
		return text;
	}
	public String removeBlankRow(String text) {
		text = text.replaceAll("\r\n", "");
		//text = text.replaceAll("\n\n", "");
		text = text.replaceAll("\n+", "\n");
		text = text.trim().replaceAll(" +|\t+", " ");
		text = text.replaceAll("\n\\s+", "");
		return text;
	}
	public String removeSymbol(String text) {
		text = text.replaceAll(symbol, " ");
		return text;
	}
	public static void main(String[] args) {
		String str = "i.1.1. giới thiệu\r\n" + 
				"\r\n" + 
				"thuật ngữ search engine thường được dùng để mô tả cho cả crawler-based search engine và human-powered directory. đây là hai loại máy tìm kiếm mà chúng thu thập chỉ mục theo các cách khác nhau.";
		System.out.println(str.replaceAll(symbol, ""));
	}
}

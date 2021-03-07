package lucene.crawler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class ArticleWebCrawler {
	ICrawlMethod strategy;
	String directory;
	public ICrawlMethod getStrategy() {
		return strategy;
	}
	public void setStrategy(ICrawlMethod strategy) {
		this.strategy = strategy;
	}
	public ArticleWebCrawler(ICrawlMethod strategy, String directory) {
		super();
		this.directory = directory;
		this.strategy = strategy;
	}
	
	public abstract List<String> getFeedsLink();
	public abstract void crawlArticle(String link);
	public abstract void crawl();
	public void extractFile(String articleContent, String articleName) {
		// TODO Auto-generated method stub
		String symbol ="[!@#$%^&*(),.?\":{}|<>/]";
		String fileName = articleName.replaceAll(symbol, "");
		File file = new File(directory  + "/" + fileName+ ".txt");
		
		try {
			PrintWriter writer = new PrintWriter(file, Charset.forName("UTF-8"));
			writer.println(articleContent);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
}

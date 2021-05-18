package lucene.crawler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VibloWebCrawler extends ArticleWebCrawler{
	static String webDomain = "https://viblo.asia";
	static String webLink = "https://viblo.asia/newest"; 
	static String labelSource = "VIB";
	
	
	public VibloWebCrawler(ICrawlMethod strategy, String directory) {
		super(strategy, directory);
	}
	@Override
	public List<String> getFeedsLink(){
		List<String> feedsLink = new ArrayList<String>();
		Document document = null;
		try {
			document = Jsoup.connect(webLink).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements feeds = document.getElementsByClass("post-title--inline");
		for (Element feed : feeds) {
			Element aTag = feed.getElementsByAttribute("href").get(0);
			String link = webDomain + aTag.attr("href");
			feedsLink.add(link);
		}
		return feedsLink;
	}
	public void addFeedLinks() {
		this.strategy.addFeedLinks(getFeedsLink());
	}
	@Override
	public void crawl() {
		addFeedLinks();
		Document document = null;
		String  articleLink, articleName, articleContent = null;
		while(!strategy.isDone()) {
			
			articleLink = strategy.nextLink();
			System.out.println("nextLink" +  articleLink);
			try {
				document = Jsoup.connect(articleLink).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			try {
			articleName = document.getElementsByClass("article-content__title").get(0).text();
			articleContent = document.getElementsByTag("article").get(0).text();
			}
			catch (Exception e){
				continue;
			}
//			if(strategy.needMoreRelatedPosts()) {
//				System.out.println("need more related");
//				List<String> relatedPost= new ArrayList<String>();
//				Elements relateds = document.select("post-recommendations bg-light");
//				System.out.println(relateds.isEmpty());
//				System.out.println(relateds.get(0));
//				for (Element related : relateds) {
//					System.out.println(related.text());
//					Element aTag = related.getElementsByAttribute("href").get(0);
//					String relatedLink = webDomain + aTag.attr("href");
//					System.out.print(": " + relatedLink);
//					relatedPost.add(relatedLink);
//				}
//				strategy.addRelatedLinks(relatedPost);
//			}
			extractFile(articleContent, articleName, articleLink, labelSource);
			strategy.increaseCrawledArticle();
		}
		
	}
	

}

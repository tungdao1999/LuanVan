package lucene.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiWebCrawler extends ArticleWebCrawler {
	String labelSource = "WIK";
	String wikiSign = "wiki";
	String wikiDocSign = "/w/";
	String editSign = "https://";
	String webDomain ="https://vi.wikipedia.org";
	String webLink  = "https://vi.wikipedia.org/wiki/C%C3%B4ng_ngh%E1%BB%87_th%C3%B4ng_tin";
	public WikiWebCrawler(ICrawlMethod strategy, String directory) {
		super(strategy, directory);
	}

	@Override
	public List<String> getFeedsLink() {
		List<String> feedsLink = new ArrayList<String>();
		Document document = null;
		try {
			document = Jsoup.connect(webLink).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements feeds = document.getElementsByClass("mw-parser-output");
		
		Elements links = null;
		for (Element element : feeds) {
			
			links = element.select("a");
			
		}
		String text = "";
		String link = "";
		for (Element element : links) {
			text = element.attr("href");
			if(!text.contains(editSign) && ( text.contains(wikiSign) || text.contains(wikiDocSign))) {
				link = webDomain + text;
				feedsLink.add(link);
				System.out.println(link);
			}
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
		String articleLink, articleName, articleContent = "";
		while (!strategy.isDone()) {
			
			articleLink = strategy.nextLink();
			
			try {
				document = Jsoup.connect(articleLink).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			articleName = document.getElementsByClass("firstHeading").get(0).text();
			
			Elements elements = document.getElementsByClass("mw-body");
			
			for (Element element : elements) {
				articleContent.concat(element.select("p").text());
			}//loop for append string 
			
			if (strategy.needMoreRelatedPosts()) {
				
				List<String> relatedPost = new ArrayList<String>();
				
				Elements feeds = document.getElementsByClass("mw-parser-output");
				
				Elements links = null;
				for (Element element : feeds) {
					
					links = element.select("a");
					
				}// end loop find a tag
				String text = "";
				String link = "";
				for (Element element : links) {
					text = element.attr("href");
					if(!text.contains(editSign) && ( text.contains(wikiSign) || text.contains(wikiDocSign))) {
						link = webDomain + text;
						relatedPost.add(link);
					}
				}// end loop find all link
				
				strategy.addRelatedLinks(relatedPost);
				
			}//end if need relate post
			extractFile(articleContent, articleName, articleLink, labelSource);
			strategy.increaseCrawledArticle();
		}// end while
		
	}
	

}

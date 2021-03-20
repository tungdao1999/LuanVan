package lucene.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lucene.search.FileSearcher;
import resource.ResourcesUtils;

public class ITViecWebCrawler extends ArticleWebCrawler {

	//static String webDomain = "https://viblo.asia";
	static String webLink = "https://itviec.com/blog/";
	
	
	public ITViecWebCrawler(ICrawlMethod strategy, String directory) {
		super(strategy, directory);
		// TODO Auto-generated constructor stub

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
		Elements feeds = document.getElementsByClass("bottom");
		for (Element feed : feeds) {
			Element aTag = feed.getElementsByAttribute("href").get(0);
			String link = aTag.attr("href");
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
		String articleLink, articleName, articleContent = null;
		while (!strategy.isDone()) {
			articleLink = strategy.nextLink();
			try {
				if(isExist(articleLink)) {
					continue;
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(articleLink);
			try {
				document = Jsoup.connect(articleLink).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			articleName = document.getElementsByClass("meta").get(0).text();
			System.out.println("name" + articleName);
			articleContent = document.getElementsByClass("post-content").get(0).text();
			System.out.println("content" + articleContent);
			if (strategy.needMoreRelatedPosts()) {
				List<String> relatedPost = new ArrayList<String>();
				Element relateds = document.getElementsByClass("yarpp-related").get(0);

				Elements aTags = relateds.getElementsByAttribute("href");
				for (Element tag : aTags) {
					String relatedLink = tag.attr("href");
					relatedPost.add(relatedLink);
				}

				strategy.addRelatedLinks(relatedPost);
			}
			extractFile(articleContent, articleName);
			strategy.increaseCrawledArticle();
		}

	}


	private boolean isExist(String articleLink) throws Exception {
		// TODO Auto-generated method stub
		FileSearcher fs = new FileSearcher(ResourcesUtils.resourcePath + "/lucene/index");	
		return fs.checkExistedLink(articleLink);
	}

	@Override
	public void crawlArticle(String link) {
		// TODO Auto-generated method stub
		
	}

}

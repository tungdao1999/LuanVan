package lucene.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiWebCrawler extends ArticleWebCrawler {
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

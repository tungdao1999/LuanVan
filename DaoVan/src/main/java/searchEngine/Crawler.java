package searchEngine;

import java.util.List;
import java.util.Stack;

public abstract class Crawler {
		private Stack<String> linkHaveCrawled;
		private CrawlLinkALgo crawlAlgo;
		abstract String extractFile(String link);
		abstract List<String> extractLinkInNewsFeed();
}

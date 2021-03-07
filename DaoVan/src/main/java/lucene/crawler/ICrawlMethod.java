package lucene.crawler;

import java.util.List;

public interface ICrawlMethod {
	String nextLink();
	void addFeedLinks(List<String> feedsLink);
	void addRelatedLinks(List<String> relatedPost);
	void configParam(int maxArticleSize, int maxRelatedPostPerLink);
	boolean isDone();
	boolean needMoreRelatedPosts();
	void increaseCrawledArticle();
	
}

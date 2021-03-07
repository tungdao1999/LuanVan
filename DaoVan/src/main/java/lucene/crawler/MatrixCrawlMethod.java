package lucene.crawler;

import java.util.List;

public class MatrixCrawlMethod implements ICrawlMethod{
	int maxArticleSize;
	int maxRelatedPost;
	String[][] linkMatrix;

	public String nextLink() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addFeedLinks(List<String> feedsLink) {
		// TODO Auto-generated method stub
		
	}

	public void addRelatedLinks(List<String> relatedPost) {
		// TODO Auto-generated method stub
		
	}

	public void configParam(int maxArticleSize, int maxRelatedPostPerLink) {
		// TODO Auto-generated method stub
		
	}

	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean needMoreRelatedPosts() {
		// TODO Auto-generated method stub
		return false;
	}

	public void increaseCrawledArticle() {
		// TODO Auto-generated method stub
		
	}

}

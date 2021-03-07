package lucene.crawler;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TraditionalCrawlMethod implements ICrawlMethod{
	Queue<String> linkQueue = new LinkedList<String>();
	int maxRelatedLink;
	int maxArticleSize;
	int crawledArticle = 0;
	
	public String nextLink() {
		// TODO Auto-generated method stub
		return linkQueue.poll();
	}

	public void addFeedLinks(List<String> feedsLink) {
		// TODO Auto-generated method stub
		if(maxArticleSize < feedsLink.size()) {
			for (int i = 0; i < maxArticleSize; i++) {
				linkQueue.add(feedsLink.get(i));
			}
		}
		else {
		for (int i = 0; i < feedsLink.size(); i++) {
				linkQueue.add(feedsLink.get(i));
		}
		}
	}

	public void addRelatedLinks(List<String> relatedPost) {
		// TODO Auto-generated method stub
		for (int i = 0; i < relatedPost.size(); i++) {
			if(i < maxRelatedLink && linkQueue.size() < maxArticleSize) {
				linkQueue.add(relatedPost.get(i));
			}
		}
	}

	public void configParam(int maxArticleSize, int maxRelatedLinkPerArticle) {
		// TODO Auto-generated method stub
		this.maxRelatedLink = maxRelatedLinkPerArticle;
		this.maxArticleSize = maxArticleSize;
	}

	public boolean isDone() {
		// TODO Auto-generated method stub
		return linkQueue.isEmpty() || crawledArticle >= maxArticleSize;
	}

	public boolean needMoreRelatedPosts() {
		// TODO Auto-generated method stub
		return linkQueue.size() < maxArticleSize; 
	}
	public void increaseCrawledArticle() {
		this.crawledArticle++;
	}
	public boolean checkLinkExisted(String link) {
		return false;
	}
	

}

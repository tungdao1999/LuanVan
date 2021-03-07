package lucene.crawler;

import org.apache.lucene.store.Directory;

import resource.ResourcesUtils;

public class crawl {
	public static void main(String[] args) {
		ICrawlMethod method = new TraditionalCrawlMethod();
		method.configParam(30, 4);
		String dir = ResourcesUtils.resourcePath + "/" + "crawled/ITViec"; 
		ArticleWebCrawler crawler = new ITViecWebCrawler(method, dir);
		crawler.crawl();
	}

}

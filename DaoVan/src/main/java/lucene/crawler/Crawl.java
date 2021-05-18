package lucene.crawler;

import org.apache.lucene.store.Directory;

import constants.DocumentField;
import resource.ResourcesUtils;

public class Crawl {
	
	public void crawling(int maxArticleSize,int maxRelateLinkSize,String crawlingMethod,String webCrawler) {
		
		//init
		ICrawlMethod method = null;
		String dir = "";
		ArticleWebCrawler crawler = null;
		
		//set crawl method 
		if(crawlingMethod.equalsIgnoreCase(DocumentField.TRANDITIOAL_METHOD)) {
			method = new TraditionalCrawlMethod();
		}else if(crawlingMethod.equalsIgnoreCase(DocumentField.MATTRIX_METHOD)) {
			method = new MatrixCrawlMethod();
		}
		
		//set crawl config
		method.configParam(maxArticleSize, maxRelateLinkSize);
		
		
		//set web crawler
		if(webCrawler.equalsIgnoreCase(DocumentField.ITVIEC_CRAWL)) {
			dir = ResourcesUtils.resourcePath + "/" + "crawled/ITViec"; 
			crawler = new ITViecWebCrawler(method, dir);
		}else if(webCrawler.equalsIgnoreCase(DocumentField.VIBLO_CRAWL)) {
			dir = ResourcesUtils.resourcePath + "/" + "crawled/Viblo";
			crawler = new VibloWebCrawler(method, dir);
		}else if(webCrawler.equalsIgnoreCase(DocumentField.WIKI_CRAWL)) {
			dir = ResourcesUtils.resourcePath + "/" + "crawled/Wiki";
		}
		
		crawler.crawl();
	}
	public static void main(String[] args) {
//		ICrawlMethod method = new TraditionalCrawlMethod();
//		method.configParam(30, 4);
//		String dir = ResourcesUtils.resourcePath + "/" + "crawled/ITViec"; 
//		ArticleWebCrawler crawler = new ITViecWebCrawler(method, dir);
//		crawler.crawl();
	}

}
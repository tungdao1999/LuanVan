package temporarySearchegine;

import org.apache.lucene.store.Directory;

import constants.DocumentField;
import indexing.IIndexer;
import indexing.ITViecIndexer;
import indexing.VilboIndexer;
import indexing.WikiIndexer;
import lucene.crawler.Crawl;
import lucene.search.DirectoryInitilizer;
import preprocess.PreprocessingProcess;
import resource.ResourcesUtils;

public class CrawlBot {
		public void run() {
			String dirPath =  ResourcesUtils.resourcePath + "/lucene/index";
			Crawl crawl = new Crawl();
			String[] web = {DocumentField.VIBLO_CRAWL,DocumentField.ITVIEC_CRAWL,DocumentField.WIKI_CRAWL};
			
			for (String w : web) {
				crawl.crawling(30, 4, DocumentField.TRANDITIOAL_METHOD, w);
			}
			
			PreprocessingProcess pp = new PreprocessingProcess();
			
			pp.preprocessingITViec();
			pp.preprocessingViblo();
			pp.preprocessingUploadFile();
			
			IIndexer indexer = null;
			
			Directory dir = DirectoryInitilizer.createFSDirectory(dirPath);
			
			for (String w : web) {
				if(w.equalsIgnoreCase(DocumentField.VIBLO_CRAWL)) {
					indexer = new VilboIndexer();
					indexer.indexingByUrl(dir);
				}else if(w.equalsIgnoreCase(DocumentField.ITVIEC_CRAWL)) {
					indexer = new ITViecIndexer();
					indexer.indexingByUrl(dir);
				}
				else if(w.equalsIgnoreCase(DocumentField.WIKI_CRAWL)) {
					indexer = new WikiIndexer();
					indexer.indexingByUrl(dir);
				}
			}
			
					
		}
		public static void main(String[] args) {
			CrawlBot cb = new CrawlBot();
			cb.run();
		}
}

package temporarySearchegine;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import constants.DocumentField;
import indexing.IIndexer;
import indexing.ITViecIndexer;
import indexing.UploadFileIndexer;
import indexing.VilboIndexer;
import indexing.WikiIndexer;
import lucene.search.DirectoryInitilizer;
import resource.ResourcesUtils;

public class IndexBot {
	public void run() {
		String[] web = { /*DocumentField.VIBLO_CRAWL, DocumentField.ITVIEC_CRAWL,*/DocumentField.UPLOAD_CRAWL/* DocumentField.WIKI_CRAWL */};
		String dirPath =  ResourcesUtils.resourcePath + "/lucene/index";
		Directory dir = DirectoryInitilizer.createFSDirectory(dirPath);
		IIndexer indexer = null;
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
		else if(w.equalsIgnoreCase(DocumentField.UPLOAD_CRAWL)) {
			indexer = new UploadFileIndexer();
			indexer.indexingByUrl(dir);
		}
	}
	}
	public void deleteAllData() throws IOException {
		String dirPath =  ResourcesUtils.resourcePath + "/lucene/index";
		
		StandardAnalyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		
		
		Directory dir = DirectoryInitilizer.createFSDirectory(dirPath);

		IndexWriter writer = new IndexWriter(dir , config);
          writer.deleteAll();
          writer.commit();
          writer.close();
	}
	public static void main(String[] args) {
		IndexBot bot= new IndexBot();
				bot.run();
			
//			try {
//				bot.deleteAllData();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}

}

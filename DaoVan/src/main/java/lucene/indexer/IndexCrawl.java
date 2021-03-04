package lucene.indexer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import resource.ResourcesUtils;

public class IndexCrawl {
	private static String INDEX_DIR = ResourcesUtils.resourcePath + "/lucene/index";
	
	public static void main(String[] args) {
		
	}

	public void writeCrawledDocument(String web, String link, String content) throws IOException {
		// TODO Auto-generated method stub
		Document document = new Document();
		String id = UUID.randomUUID().toString();
	    document.add(new StringField("id", id , Field.Store.YES));
	    document.add(new TextField("web", web, Field.Store.YES));
	    document.add(new TextField("link", link , Field.Store.YES));
	    document.add(new TextField("content", content , Field.Store.YES));
	    
	    IndexWriter writer = createWriter();
	    writer.addDocument(document);
	    writer.commit();
	    writer.close();
	    
	}
	private IndexWriter createWriter() throws IOException {
		FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer = new IndexWriter(dir, config);
		return writer;
	}
	

}

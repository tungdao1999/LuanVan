package indexing;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.store.Directory;

public interface IIndexer {
	public void indexingByUrl(Directory dir);
	public Document parseToDocument(String id,String label, String linkWeb,String fileName,String filePath,String content);
	public List<File> getPathOfTextCrawled();
	public String readFile(String path) throws IOException;
}

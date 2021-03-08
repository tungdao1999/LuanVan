package indexing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;

import resource.ResourcesUtils;

public class VilboIndexer implements IIndexer {
	private static String INDEX_DIR = ResourcesUtils.resourcePath + "/lucene/crawled";
	private static String HOME_PAGE = "https://viblo.asia/p/";
	
	public void indexingByUrl(Directory dir) {
		StandardAnalyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(dir,config);
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}
		
		// init all link in folder
		
		List<File> listLink = new ArrayList<File>();
		listLink = getPathOfTextCrawled();
		for (File file : listLink) {
			
			String id = UUID.randomUUID().toString();
			String linkWeb = HOME_PAGE + file.getName();
			String fileName = file.getName();
			String fileContent = "";
			try {
				fileContent = readFile(INDEX_DIR+file.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			Document document = parseToDocument(id, linkWeb, fileName, fileContent);
			try {
				writer.addDocument(document);
				writer.commit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Document parseToDocument(String id,String linkWeb,String fileName,String content) {
		Document document = new Document();
		document.add(new StringField(DocumentField.ID_FIELD, id, Field.Store.YES));
		document.add(new StringField(DocumentField.LINK_WEB_FIELD, linkWeb, Field.Store.YES));
		document.add(new StringField(DocumentField.FILE_NAME_FIELD, fileName, Field.Store.YES));
		document.add(new TextField(DocumentField.CONTENT_FIELD, content, Field.Store.NO));
		return document;
	}
	public List<File> getPathOfTextCrawled() {
		File[] files = new File(INDEX_DIR).listFiles();
		
		List<File> listLink = new ArrayList<File>();
		
		for (int i = 0; i < files.length; i++) {
		
			listLink.add(files[i]);
		
		}
		
		return listLink;
	}
	public String readFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String result = "";
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    
		    }
		    
		    result = sb.toString();
		} finally {
		    br.close();
		}
		return result;
	}
	public static void main(String[] args) throws IOException {
		FileTxtIndex fti = new FileTxtIndex();
	}
}

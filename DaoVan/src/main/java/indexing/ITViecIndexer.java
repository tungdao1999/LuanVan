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

import constants.DocumentField;
import resource.ResourcesUtils;

public class ITViecIndexer implements IIndexer {
	private static String INDEX_DIR = ResourcesUtils.resourcePath + "/lucene/crawled/ITViec/";
	private static String HOME_PAGE = "https://itviec.com/blog/";
	private static String BASE_LABEL = "ITV_";
	
	public void indexingByUrl(Directory dir) {
		int sentence_index  = 0;
		BufferedReader br = null;
		String line = "";
		int docId =0;
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
		String label = "";
		for (File file : listLink) {	
			String linkWeb = HOME_PAGE + file.getName().replace(".txt", "");
			String fileName = file.getName();		
			try {
				br = new BufferedReader(new FileReader(file));
				while((line = br.readLine()) != null) {
					label = BASE_LABEL+docId+"_Line_"+sentence_index; 
					Document document = parseToDocument(BASE_LABEL, label, linkWeb, fileName,file.getPath(), line);
					try {
						writer.addDocument(document);
						writer.commit();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Document parseToDocument(String id,String label, String linkWeb,String fileName,String filePath,String sentence) {
		Document document = new Document();
		document.add(new StringField(DocumentField.ID_FIELD, id, Field.Store.YES));
		document.add(new StringField(DocumentField.LABEL_FIELD, label, Field.Store.YES));
		document.add(new TextField(DocumentField.LINK_WEB_FIELD, linkWeb, Field.Store.YES));
		document.add(new TextField(DocumentField.FILE_NAME_FIELD, fileName, Field.Store.YES));
		document.add(new TextField(DocumentField.FILE_PATH, filePath, Field.Store.YES));
		document.add(new TextField(DocumentField.CONTENT_FIELD, sentence, Field.Store.YES));
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
	
}

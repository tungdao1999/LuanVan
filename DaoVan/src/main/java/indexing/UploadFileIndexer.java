package indexing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
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

import MysqlHelper.MysqlAction;
import constants.DocumentField;
import resource.ResourcesUtils;

public class UploadFileIndexer implements IIndexer {
	private static String INDEX_DIR = ResourcesUtils.resourcePath + "/lucene/crawled/uploadFile/";
	//private static String HOME_PAGE = "";
	private static String BASE_LABEL = "UPL_";
	
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
		int numLine = 0;
		for (File file : listLink) {	
			try {
			numLine = 0;
			br = new BufferedReader(new FileReader(file));
			String fileName = file.getName();	
			String linkWeb = fileName;
			System.out.println("name" + fileName);
		//	MysqlAction.insertToDatabase(java.time.LocalTime.now().toString(), fileName, file.getPath(),linkWeb, "txt", 1);
			docId = MysqlAction.searchDocumentId(fileName);
				while((line = br.readLine()) != null) {
					label = BASE_LABEL+docId+"_"+(numLine+1);
					Document document = parseToDocument(String.valueOf(docId), label, linkWeb, fileName,file.getPath(), line);
					numLine ++;
					System.out.println(label + " numline: "+  numLine);
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
			}
			finally {
				try {
					MysqlAction.updateIndexingState(file.getName(), numLine);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

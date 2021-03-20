package indexing;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.store.Directory;

import constants.DocumentField;

public class WikiIndexer implements IIndexer {

	public void indexingByUrl(Directory dir) {
		// TODO Auto-generated method stub
		
	}

	public Document parseToDocument(String id,String label, String linkWeb,String fileName,String sentence) {
		Document document = new Document();
		document.add(new StringField(DocumentField.ID_FIELD, id, Field.Store.YES));
		document.add(new StringField(DocumentField.LABEL_FIELD, label, Field.Store.YES));
		document.add(new TextField(DocumentField.LINK_WEB_FIELD, linkWeb, Field.Store.YES));
		document.add(new TextField(DocumentField.FILE_NAME_FIELD, fileName, Field.Store.YES));
		document.add(new TextField(DocumentField.CONTENT_FIELD, sentence, Field.Store.YES));
		return document;
	}

	public List<File> getPathOfTextCrawled() {
		// TODO Auto-generated method stub
		return null;
	}

	public String readFile(String path) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}

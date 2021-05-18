package lucene.search;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import constants.DocumentField;
import indexing.IIndexer;
import indexing.ITViecIndexer;
import resource.ResourcesUtils;

public class test {
	public static void main(String[] args) {
		String dirPath =  ResourcesUtils.resourcePath + "/lucene/index";
		Directory dir = DirectoryInitilizer.createFSDirectory(dirPath);
//		IIndexer indexer = new ITViecIndexer();
//		indexer.indexingByUrl(dir);
		
		try {
			FileSearcher searcher = new FileSearcher(dir);
			TopDocs hits = searcher.searchContent("Lập trình viên", 5);
			System.out.println("Total Results :: " + hits.totalHits);
			 for (ScoreDoc sd : hits.scoreDocs) 
			        {
			            Document d = searcher.getSearcher().doc(sd.doc);
			         //   System.out.println(d.toString());
			            System.out.println(d.get(DocumentField.FILE_NAME_FIELD));
			        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

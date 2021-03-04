package lucene.indexer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import preprocess.OfficeWordParser;
import preprocess.TextParserFactory;
import resource.ResourcesUtils;

public class DocumentIndexer {
	private static String INDEX_DIR = ResourcesUtils.resourcePath + "/lucene/index";

	private static IndexWriter createWriter() throws IOException {
		FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer = new IndexWriter(dir, config);
		return writer;
	}
	private static Document createDocument(Integer id, String firstName, String lastName, String website) 
	{
	    Document document = new Document();
	    document.add(new StringField("id", id.toString() , Field.Store.YES));
	    document.add(new TextField("fn", firstName, Field.Store.YES));
	    document.add(new TextField("lastName", lastName , Field.Store.YES));
	    document.add(new TextField("website", website , Field.Store.YES));
	    return document;
	}
	 public static void main(String[] args) throws Exception 
	    {
//	        IndexWriter writer = createWriter();
//	        List<Document> documents = new ArrayList<Document>();
//	         
////	        TextParserFactory parser = new TextParserFactory(new OfficeWordParser());
////	        String text = parser.preProcessing("D://luanvan1.doc");
////	        Document document1 = createDocument(5, "My", "Doc", text);
//	        Document document1 = createDocument(1, "Le", "A", "le a mot hai ba bon");
//	        documents.add(document1);
//	        
//	        Document document2 = createDocument(2, "Ly", "Bi", "Bi van ly nam sau bay tam");
//	        documents.add(document2);
//	         
//	        //Let's clean everything first
//	        writer.deleteAll();
//	         
//	        writer.addDocuments(documents);
//	        writer.commit();
//	        writer.close();
		 	IndexSearcher searcher = createSearcher();
         
	        //Search by ID
	        TopDocs foundDocs = searchContent("giảm vị trí mà ngoại lệ cần được xử lý", searcher);
	         
	        System.out.println("Total Results :: " + foundDocs.totalHits);
	         
	        for (ScoreDoc sd : foundDocs.scoreDocs) 
	        {
	            Document d = searcher.doc(sd.doc);
	            
	            System.out.println(d.get("link"));
	        }
	         
	        //Search by firstName
//	        TopDocs foundDocs2 = searchByFirstName("Ly", searcher);
//	         
//	        System.out.println("Total Results :: " + foundDocs2.totalHits);
//	         
//	        for (ScoreDoc sd : foundDocs2.scoreDocs) 
//	        {
//	            Document d = searcher.doc(sd.doc);
//	            System.out.println(String.format(d.get("id")));
//	        }
	    }
	 private static IndexSearcher createSearcher() throws IOException {
	        Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
	        IndexReader reader = DirectoryReader.open(dir);
	        IndexSearcher searcher = new IndexSearcher(reader);
	        return searcher;
	    }
	 private static TopDocs searchByFirstName(String firstName, IndexSearcher searcher) throws Exception
	    {
	        QueryParser qp = new QueryParser("fn", new StandardAnalyzer());
	        Query firstNameQuery = qp.parse(firstName);
	        TopDocs hits = searcher.search(firstNameQuery, 10);
	        return hits;
	    }
	 
	    private static TopDocs searchById(Integer id, IndexSearcher searcher) throws Exception
	    {
	        QueryParser qp = new QueryParser("id", new StandardAnalyzer());
	        Query idQuery = qp.parse(id.toString());
	        TopDocs hits = searcher.search(idQuery, 10);
	        return hits;
	    }
	    private static TopDocs searchContent(String content, IndexSearcher searcher) throws ParseException, IOException {
	    	//QueryParser qp = new QueryParser("fn", new StandardAnalyzer());
	    	StandardAnalyzer analyzer = new StandardAnalyzer();
	    	QueryParser qp = new QueryParser("content", analyzer);
	    	//MultiFieldQueryParser multiParser = new MultiFieldQueryParser(fields, analyzer);
	    	//multiParser.
	    	qp.setDefaultOperator(QueryParser.Operator.AND);
	    	Query query = qp.parse(content);
	    	//Query
	    	TopDocs hits = searcher.search(query, 10);
	        return hits;
	    }
	    private static TopDocs searchByWeb(String web, IndexSearcher searcher) throws Exception
	    {
	    	QueryParser qp = new QueryParser("web", new StandardAnalyzer());
	        Query idQuery = qp.parse(web);
	        TopDocs hits = searcher.search(idQuery, 10);
	        return hits;
	    }
	    public void writeDocuments(List<Document> documents) throws IOException {
	    	 IndexWriter writer = createWriter();
	    	 writer.addDocuments(documents);
	    	 writer.commit();
	    	 writer.close();
	    }
	    
	
	

}

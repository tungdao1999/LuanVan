package lucene.search;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import constants.DocumentField;

public class FileSearcher {
	IndexSearcher searcher;
	
	public FileSearcher(Directory dir) {
		try {
			this.searcher = createSearcher(dir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public IndexSearcher createSearcher(Directory dir) throws IOException {
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		return searcher;
	}

	public TopDocs searchContent(String content, int resultAmount)
			throws IOException, ParseException {
		// QueryParser qp = new QueryParser("fn", new StandardAnalyzer());
		StandardAnalyzer analyzer = new StandardAnalyzer();
		QueryParser qp = new QueryParser(DocumentField.CONTENT_FIELD, analyzer);
		// MultiFieldQueryParser multiParser = new MultiFieldQueryParser(fields,
		// analyzer);
		// multiParser.
		qp.setDefaultOperator(QueryParser.Operator.AND);
		Query query = qp.parse(content);
		// Query
		TopDocs hits = searcher.search(query, resultAmount);
		return hits;
	}

	public TopDocs searchByWeb(String web, int resultAmount) throws Exception {
		QueryParser qp = new QueryParser(DocumentField.LINK_WEB_FIELD, new StandardAnalyzer());
		Query idQuery = qp.parse(web);
		TopDocs hits = searcher.search(idQuery, resultAmount);
		return hits;
	}
	public Document doc(int d) {
		try {
			return this.searcher.doc(d);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public IndexSearcher getSearcher() {
		return searcher;
	}

	public void setSearcher(IndexSearcher searcher) {
		this.searcher = searcher;
	}

}

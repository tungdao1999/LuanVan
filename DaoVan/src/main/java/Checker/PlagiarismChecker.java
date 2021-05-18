package Checker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import constants.DocumentField;
import constants.Filepath;
import doc2vec.D2VService;
import doc2vec.D2VSimilarityCaculator;
import entry.Duplicate;
import entry.Sentence;
import lucene.search.FileSearcher;
import preprocess.PreprocessService;
import resource.ResourcesUtils;

public class PlagiarismChecker {
//	public File preProcessFile(File fileIn) throws FileNotFoundException {
//		String content = PreprocessService.PreprocessRawDocFile(fileIn.getAbsolutePath());
//		File processedFile = new File(ResourcesUtils.resourcePath + "/tempoData/"+fileIn.getName().replaceAll("docx", "txt"));
//		PrintWriter pw = new PrintWriter(processedFile);
//		pw.write(content);
//		pw.close();
//		return processedFile;
//	}

	public static List<Duplicate> checkPlagiarism(File fileIn/* , File fileOut */) throws IOException, ParseException {
		List<Duplicate> sources = new ArrayList<Duplicate>();
		int count = 0;
		int numDoc = 5;
		BufferedReader br = new BufferedReader(new FileReader(fileIn));
		String sentence = "";
		FileSearcher searcher = new FileSearcher(Filepath.index_dir);
		D2VService d2vService = new D2VService(new D2VSimilarityCaculator());
		TopDocs hits = null;
		List<Sentence> listSent  = null;
		while((sentence = br.readLine()) != null) {
			int index = -1;
			listSent = new ArrayList<Sentence>();
			hits = searcher.searchContent(sentence, numDoc);
			 for (ScoreDoc sd : hits.scoreDocs) 
		        {
			//	 System.out.println(hits.totalHits);
		            Document d = searcher.getSearcher().doc(sd.doc);
		            Sentence sent = new Sentence(d.get(DocumentField.LABEL_FIELD), 
		            		d.get(DocumentField.CONTENT_FIELD), d.get(DocumentField.LINK_WEB_FIELD));
		            listSent.add(sent);
		        }
			  Integer simi = 0;
			  try {
			  index = d2vService.getMostSimilarSentences(listSent, sentence, simi);
			  }catch (IllegalArgumentException e) {
				// TODO: handle exception
				  sources.add(null);
				  continue;
			}
			  if(index == -1) {
				  sources.add(null);
			  }
			  else {
				  Duplicate dup  = new Duplicate();
				  dup.setDuplicateSent(sentence);
				  dup.setDuplicateSentIndex(count);
				  dup.setScore(simi);
				  dup.setSourceSent(listSent.get(index).getContent());
				  dup.setSourceLink(listSent.get(index).getSource());
				  sources.add(dup);
			  }
			count++;
		}
		br.close();
		return sources;
	}
	
	public static void main(String[] args) {
		String path = "D:\\Test\\LuanVan\\DaoVan\\src\\main\\resources\\tempoData\\preprocess.txt";
		File f = new File(path);
		try {
			List<Duplicate> listDup = checkPlagiarism(f);
			for (Duplicate duplicate : listDup) {
				if(duplicate != null) {
					System.out.println("***********************");
					System.out.println(duplicate.getDuplicateSent());
					System.out.println(duplicate.getSourceSent());
					System.out.println(duplicate.getSourceLink());
				}
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

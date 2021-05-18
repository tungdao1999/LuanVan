package doc2vec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.deeplearning4j.text.documentiterator.LabelsSource;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.DocumentField;
import lucene.search.DirectoryInitilizer;
import lucene.search.FileSearcher;
import resource.ResourcesUtils;

public class Doc2VecTrain {
	static String modelDocPath = ResourcesUtils.resourcePath + "/Doc2VecModel/docVector";
	private static Logger log = LoggerFactory.getLogger(Doc2VecTrain.class);
    private static String modelWordPath = ResourcesUtils.resourcePath + "/Doc2VecModel/word2vec.txt";

	public void trainNewFile(File inputTxt, List<String> labelsource) throws IOException {
      log.info("Start loading data ..."+inputTxt.getName());
               
      SentenceIterator iter = new LineSentenceIterator(inputTxt);
              
      TokenizerFactory token = new DefaultTokenizerFactory();
               
      token.setTokenPreProcessor(new CommonPreprocessor());
      AbstractCache<VocabWord> cache=new AbstractCache<VocabWord>();
              
      LabelsSource source = new LabelsSource(labelsource);
      log.info("Train the model ...");
      ParagraphVectors vec = new ParagraphVectors.Builder()
              .minWordFrequency(1)
              .iterations(5)
              .epochs(10)
              .layerSize(300)
              .learningRate(0.025)
              .labelsSource(source)
              .windowSize(5)
              .iterate(iter)
              .trainWordVectors(false)
              .vocabCache(cache)
              .tokenizerFactory(token)
              .sampling(0)
              .build();

      vec.fit();

      WordVectorSerializer.writeWordVectors(vec, new FileOutputStream(new File(modelWordPath)));
      WordVectorSerializer.writeParagraphVectors(vec, new File(modelDocPath));
		System.out.println(inputTxt.getName() + " train success");
	}
	public void trainNewFile(File inputTxt, String label) throws IOException {
	      log.info("Start loading data ..."+inputTxt.getName());
	               
	      SentenceIterator iter = new LineSentenceIterator(inputTxt);
	              
	      TokenizerFactory token = new DefaultTokenizerFactory();
	               
	      token.setTokenPreProcessor(new CommonPreprocessor());
	      AbstractCache<VocabWord> cache=new AbstractCache<VocabWord>();
	              
	      LabelsSource source = new LabelsSource(label);
	      log.info("Train the model ...");
	      ParagraphVectors vec = new ParagraphVectors.Builder()
	              .minWordFrequency(1)
	              .iterations(150)
	              .epochs(30)
	              .layerSize(300)
	              .learningRate(0.025)
	              .labelsSource(source)
	              .windowSize(5)
	              .iterate(iter)
	              .trainWordVectors(false)
	              .vocabCache(cache)
	              .tokenizerFactory(token)
	              .sampling(0)
	              .build();

	      vec.fit();

	      WordVectorSerializer.writeWordVectors(vec, new FileOutputStream(new File(modelWordPath)));
	      WordVectorSerializer.writeParagraphVectors(vec, new File(modelDocPath));
			System.out.println(inputTxt.getName() + " train success");
		}
	


//	public void trainMultipleFile(List<File> files, String fileLabel) throws IOException {
//		TokenizerFactory tFact = new DefaultTokenizerFactory();
//		tFact.setTokenPreProcessor(new CommonPreprocessor());
//		ParagraphVectors vec  = null;
//		ParagraphVectors existsVec = WordVectorSerializer.readParagraphVectors(modelFilePath);
//
//		for (int i = 0; i < files.size(); i++) {
//			LabelsSource labelFormat = new LabelsSource(fileLabel);
//			SentenceIterator iter = new BasicLineIterator(files.get(i));
//			vec = new ParagraphVectors.Builder().useExistingWordVectors(existsVec).iterations(2).epochs(1)
//					.layerSize(100).learningRate(0.025).labelsSource(labelFormat).windowSize(5).trainWordVectors(false)
//					.iterate(iter)
//					.tokenizerFactory(tFact).sampling(0).build();
//			vec.fit();
//			existsVec = vec;
//		}
//	
//
//		WordVectorSerializer.writeParagraphVectors(vec, modelFilePath);
//	}

	public double getSimilarity(String text, String label) throws IOException {
		ParagraphVectors existsVec = WordVectorSerializer.readParagraphVectors(modelDocPath);
		return existsVec.similarityToLabel(text, label);
	}
	public static void main(String[] args) throws IOException, SQLException {
//		ParagraphVectors existsVec = WordVectorSerializer.readParagraphVectors(modelDocPath);
//         TokenizerFactory token = new DefaultTokenizerFactory();
//         token.setTokenPreProcessor(new CommonPreprocessor());
//         existsVec.setTokenizerFactory(token);
//         System.out.println(existsVec.similarityToLabel("tập_tin css và js của bạn_đó", "ITV_30_10"));
//        Collection<String> lst =  existsVec.nearestLabels("tập_tin css và js của bạn_đó", 10);
//        System.out.println(lst);
//        for (String string : lst) {
//        	System.out.println(existsVec.similarityToLabel("tập_tin css và js của bạn_đó", string));
//		}
//         double[] docVector = existsVec.getWordVector("ITV_50_32");
//         System.out.println(Arrays.toString(docVector));
//		System.out.println(existsVec.similarity("Concatenate assets Dịch nôm_na là ghép những tài_sản - tài_sản ở_đây được hiểu là các tập_tin css và js của bạn_đó", "VIB_13_10"));
//		System.out.println(existsVec.similarityToLabel("tập_tin css và js của bạn_đó", "VIB_13_10"));

////   	System.out.println(existsVec.similarityToLabel("Màu tím thể_hiện mức_độ liên_quan giữa từ hiện_tại với các từ đằng trước nó", lst.toArray()[0].toString()));
//    	
    	String dirPath =  ResourcesUtils.resourcePath + "/lucene/index";
		Directory dir = DirectoryInitilizer.createFSDirectory(dirPath);
		String content = "";
		
		try {
			FileSearcher searcher = new FileSearcher(dir);
		//	TopDocs hits = searcher.searchContent("Về việc các công_ty IT đã có những chính_sách thiết_thực", 10);
			TopDocs hits = searcher.searchByLabel("VIB_13_10", 10);
			System.out.println("Total Results :: " + hits.totalHits);
			 for (ScoreDoc sd : hits.scoreDocs) 
			        {
			            Document d = searcher.getSearcher().doc(sd.doc);
			         //   System.out.println(d.toString());
			            content = d.get(DocumentField.CONTENT_FIELD);
			            System.out.println(content);
			        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new FileReader(new File(ResourcesUtils.resourcePath + "/Doc2VecModel/dataFileToTrain/mergeV0.txt")));
		int count = 0;
		String line = "";
		while((line = br.readLine()) != null) {
			if(line.equalsIgnoreCase(content)) {
				System.out.println(count + "  " + line);
				break;
			}
			count++;
		}
		List<String> label = new TrainBot().buildLabelSource();
		System.out.println(label.indexOf("VIB_13_10"));
		//System.out.println(label.get);
	}

}

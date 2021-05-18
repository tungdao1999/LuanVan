package doc2vec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

import resource.ResourcesUtils;

public class D2VSimilarityCaculator {
	private ParagraphVectors p2v;
	private String modelDocPath =  ResourcesUtils.resourcePath + "/Doc2VecModel/docVector";

	public D2VSimilarityCaculator() throws IOException {
		super();
		this.p2v = WordVectorSerializer.readParagraphVectors(modelDocPath);
		TokenizerFactory token = new DefaultTokenizerFactory();
		p2v.setTokenizerFactory(token);
		//System.out.println((p2v.getWordVectorMatrixNormalized("cÃ´ng_ty")));
	}
	@SuppressWarnings({ "Bad Result", "deprecation" })
	public double getSimilarityWithLabel(String comparedText, String label) {
		return p2v.similarityToLabel(comparedText, label);
	}
	//return similarity between a text and a label with its content
	//mixed w2v and d2v
	public double getSimilarityMixed(String comparedText, String comparedLabel, String labelContent, int labelInfluence) {
		INDArray textMean = meanArrayOf(comparedText);
		String content = labelContent;
		for (int i = 0; i < labelInfluence; i++) {
			content = content.concat(" "+comparedLabel);
		}
		INDArray labelMean = meanArrayOf(content);
		
		return Transforms.cosineSim(textMean, labelMean);
	}
	public double getMaxSimilarityInLabels(String comparedText, List<String> labels) {
		double simi = 0.0;
		for (String label : labels) {
			double labelSimi = p2v.similarityToLabel(comparedText, label);
			if(labelSimi > simi ) simi = labelSimi;
		}
		return simi;
		
	}
	public double similarityTwoRawText(String text1, String text2) {
		 	INDArray docmean1 = meanArrayOf(text1);
		 	INDArray docmean2 = meanArrayOf(text2);
	        return  Transforms.cosineSim(docmean1, docmean2);
	}
	private INDArray meanArrayOf(String text) {
		List<String> tokens = p2v.getTokenizerFactory().create(text).getTokens();
        List<VocabWord> document = new ArrayList<VocabWord>();
        for (String token: tokens) {
            if (p2v.getVocab().containsWord(token)) {
                document.add(p2v.getVocab().wordFor(token));
            }
        }
        INDArray arr1 = Nd4j.create(document.size(),300);
        for(int i = 0; i < document.size(); i++) {
        	INDArray wordfor = p2v.getWordVectorMatrixNormalized(document.get(i).getWord());
            arr1.putRow(i,wordfor);
        }

        INDArray docMean = arr1.mean(0);
        return docMean;
	}
	public static void main(String[] args) {
		try {
			D2VSimilarityCaculator service = new D2VSimilarityCaculator();
			String text1 = "hÃ¬nh phÃ¢n_tÃ­ch thiáº¿t_káº¿ há»‡_thá»‘ng";
			String text2 = "hÃ¬nh_áº£nh phÃ¢n_tÃ­ch thiÃªt_káº¿ há»‡_thá»‘ng";
			System.out.println(service.similarityTwoRawText(text1, text2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
		
}

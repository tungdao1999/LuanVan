package doc2vec;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.text.documentiterator.LabelsSource;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import resource.ResourcesUtils;

public class Doc2VecTrain {
	static String modelFilePath = ResourcesUtils.resourcePath + "/Doc2VecModel/wordVector";

	public void trainNewFile(File file, String fileLabel) throws IOException {
		TokenizerFactory tFact = new DefaultTokenizerFactory();
		tFact.setTokenPreProcessor(new CommonPreprocessor());

		LabelsSource labelFormat = new LabelsSource(fileLabel);
		SentenceIterator iter = new BasicLineIterator(file);

		ParagraphVectors existsVec = WordVectorSerializer.readParagraphVectors(modelFilePath);
		ParagraphVectors vec = new ParagraphVectors.Builder().useExistingWordVectors(existsVec).iterations(2).epochs(1)
				.iterate(iter).layerSize(100).learningRate(0.025).labelsSource(labelFormat).windowSize(5)
				.trainWordVectors(false).tokenizerFactory(tFact).sampling(0).build();
		;
		vec.fit();

		WordVectorSerializer.writeParagraphVectors(vec, modelFilePath);
	}

	public void trainMultipleFile(List<File> files, String fileLabel) throws IOException {
		TokenizerFactory tFact = new DefaultTokenizerFactory();
		tFact.setTokenPreProcessor(new CommonPreprocessor());
		ParagraphVectors vec  = null;
		ParagraphVectors existsVec = WordVectorSerializer.readParagraphVectors(modelFilePath);

		for (int i = 0; i < files.size(); i++) {
			LabelsSource labelFormat = new LabelsSource(fileLabel);
			SentenceIterator iter = new BasicLineIterator(files.get(i));
			vec = new ParagraphVectors.Builder().useExistingWordVectors(existsVec).iterations(2).epochs(1)
					.layerSize(100).learningRate(0.025).labelsSource(labelFormat).windowSize(5).trainWordVectors(false)
					.iterate(iter)
					.tokenizerFactory(tFact).sampling(0).build();
			vec.fit();
			existsVec = vec;
		}
	

		WordVectorSerializer.writeParagraphVectors(vec, modelFilePath);
	}

	public double getSimilarity(String text, String label) throws IOException {
		ParagraphVectors existsVec = WordVectorSerializer.readParagraphVectors(modelFilePath);
		return existsVec.similarityToLabel(text, label);
	}

}

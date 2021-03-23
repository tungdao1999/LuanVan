package doc2vec;

import java.io.IOException;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.nd4j.linalg.api.ndarray.INDArray;

import resource.ResourcesUtils;

public class Doc2VecTest {
	static String modelFilePath = ResourcesUtils.resourcePath + "/Doc2VecModel/wordVector";
	
	public void testDocument(String dirPath) {
		try {
			ParagraphVectors existsVec = WordVectorSerializer.readParagraphVectors(modelFilePath);
			INDArray
			LabelSee
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

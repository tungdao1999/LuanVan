package doc2vec;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import MysqlHelper.MysqlAction;
import entry.Sentence;

public class D2VService {
	D2VSimilarityCaculator caculator;
	double simiThreshold = 0.5;

	public double getSimiThreshold() {
		return simiThreshold;
	}
	public void setSimiThreshold(double simiThreshold) {
		this.simiThreshold = simiThreshold;
	}
	public D2VService(D2VSimilarityCaculator caculator) {
		super();
		this.caculator = caculator;
	}
	public D2VService() throws IOException {
		super();
		this.caculator = new D2VSimilarityCaculator();
	}
	public int getMostSimilarSentences(List<Sentence> listSentences, String comparedText, Integer simiScore) {
		double maxCurrentSimi = 0;
		int maxIndex = -1;
		double simi  = 0.0;
		Sentence sent  = null;
		for (int i = 0; i < listSentences.size(); i++) {
			
			sent = listSentences.get(i);
			if(sent.getLabel() != null) {
			simi = caculator.getSimilarityMixed(comparedText, sent.getLabel(), sent.getContent(), 1);
			}
			else {
				simi = caculator.similarityTwoRawText(comparedText, sent.getContent());
			}
			if(simi > maxCurrentSimi) {
				maxCurrentSimi= simi;
				maxIndex = i;
			}
			
		}
		if(maxCurrentSimi > simiThreshold) {
			simiScore = 100;
			return maxIndex;
		}
		else {
			return -1;
		}
		
	}
	public void trainFile(File file, String label) throws IOException {
		Doc2VecTrain trainer =  new Doc2VecTrain();
		trainer.trainNewFile(file, label);
	}
	public void trainAUploadFile(File file) throws IOException {
		int id  = MysqlAction.searchDocumentId(file.getName());
		String label = "UPL_" + id + "_";
		trainFile(file, label);
	}
	public static void main(String[] args) {
		try {
			D2VService service = new D2VService();
			File file = new File("D:\\Test\\LuanVan\\DaoVan\\src\\main\\resources\\lucene\\crawled\\uploadFile\\KLTN_Ung-dung-Cho-Xe_Ap-dung-React-native_14130110_14130052.txt");
			service.trainAUploadFile(file);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}

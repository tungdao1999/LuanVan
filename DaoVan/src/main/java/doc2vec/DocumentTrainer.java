package doc2vec;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import MysqlHelper.MySQLConnectorUlti;
import MysqlHelper.MysqlAction;
import constants.DocumentField;
import resource.ResourcesUtils;

public class DocumentTrainer {
	private String label_wiki = "wiki_";
	private String label_ITV ="ITV_";
	private String label_VIB ="VIB_";
	private String lable_UPL ="UPL_";
	
	private Doc2VecTrain doc2vec = new Doc2VecTrain();
	
	private static String wiki_Dir = ResourcesUtils.resourcePath + "/lucene/crawled/wiki/";
	private static String viblo_Dir = ResourcesUtils.resourcePath + "/lucene/crawled/Viblo/";
	private static String itViec_Dir = ResourcesUtils.resourcePath + "/lucene/crawled/ITViec/";
	private static String upload_Dir = ResourcesUtils.resourcePath + "/lucene/crawled/uploadFile/";
	
	//UPL_1_LINE_1
	
	public void trainDocument(String sourceDir, String sourceLabel) {
		int docId = 0;
		String label = "";
		File[] files = new File(sourceDir).listFiles();
		for (File file : files) {
			docId = MysqlAction.searchDocumentId(file.getName());
			label = sourceLabel+docId+"_LINE_";
			try {
				doc2vec.trainNewFile(file, label);
			} catch (IOException e) {
				// catch train doc2vec
				e.printStackTrace();
			}
		}
	}
	public void getFileToTrain() {
		Queue<String> listDiretory = new LinkedList<String>();
		listDiretory.add(wiki_Dir);listDiretory.add(viblo_Dir);listDiretory.add(upload_Dir);listDiretory.add(itViec_Dir);
		
		while (!listDiretory.isEmpty()) {
			String directory = listDiretory.poll();
			String label = returnLabelUseDir(directory);
			
			trainDocument(directory, label);
			
		}
		
	}
	public String returnLabelUseDir(String dirPath) {
		String result = "";
		String[] splitItems = dirPath.split("/");
		int lastIndex = splitItems.length-1;
		String tag = splitItems[lastIndex];
		if(tag.equalsIgnoreCase(DocumentField.ITViec_TAG)) {
			result = label_ITV;
		}else if(tag.equalsIgnoreCase(DocumentField.WIKI_TAG)) {
			result = label_wiki;
		}else if(tag.equalsIgnoreCase(DocumentField.VIBLO_TAG)) {
			result = label_VIB;
		}else if(tag.equalsIgnoreCase(DocumentField.UPLOAD_TAG)) {
			result = lable_UPL;
		}
		return result;
	}
	public static void main(String[] args) {
		DocumentTrainer dt = new DocumentTrainer();
		dt.getFileToTrain();
	}
}

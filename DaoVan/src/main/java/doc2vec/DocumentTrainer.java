//package doc2vec;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//
//import MysqlHelper.MySQLConnectorUlti;
//import MysqlHelper.MysqlAction;
//import constants.DocumentField;
//import resource.ResourcesUtils;
//
//public class DocumentTrainer {
//	private String label_wiki = "wiki_";
//	private String label_ITV = "ITV_";
//	private String label_VIB = "VIB_";
//	private String lable_UPL = "UPL_";
//
//	private Doc2VecTrain doc2vec = new Doc2VecTrain();
//
//	private static String wiki_Dir = ResourcesUtils.resourcePath + "/lucene/crawled/wiki/";
//	private static String viblo_Dir = ResourcesUtils.resourcePath + "/lucene/crawled/Viblo/";
//	private static String itViec_Dir = ResourcesUtils.resourcePath + "/lucene/crawled/ITViec/";
//	private static String upload_Dir = ResourcesUtils.resourcePath + "/lucene/crawled/uploadFile/";
//
//	// UPL_1_LINE_1
//
//	public void trainDocument(String sourcePath, List<String> labelsource) {
//		File file = new File(sourcePath);
//			docId = MysqlAction.searchDocumentId(file.getName());
//	//		label = sourceLabel+docId+"_";
//			try {
//				doc2vec.trainNewFile(file, labelsource);
//			} catch (IOException e) {
//				// catch train doc2vec	
//				e.printStackTrace();
//			}
//			finally {
//				MysqlAction.updateTrainState(docId);
//			}
//		
//	}
//
//	public void getFileToTrain() {
//		Queue<String> listDiretory = MysqlAction.getPreprocessedDirectory();
//		List<String> labelsource = buildLabelsource(listDiretory);
//
//		while (!listDiretory.isEmpty()) {
//			String directory = listDiretory.poll();
//			String label = returnLabelUseDir(directory);
//
//			trainDocument(directory, labelsource);
//		}
//		return;
//	}
//
//	public String returnLabelUseDir(String dirPath) {
//		String result = "";
//		String[] splitItems = dirPath.split("/");
//		int lastIndex = splitItems.length - 1;
//		String tag = splitItems[lastIndex];
//		if (tag.equalsIgnoreCase(DocumentField.ITViec_TAG)) {
//			result = label_ITV;
//		} else if (tag.equalsIgnoreCase(DocumentField.WIKI_TAG)) {
//			result = label_wiki;
//		} else if (tag.equalsIgnoreCase(DocumentField.VIBLO_TAG)) {
//			result = label_VIB;
//		} else if (tag.equalsIgnoreCase(DocumentField.UPLOAD_TAG)) {
//			result = lable_UPL;
//		}
//		return result;
//	}
//
//	public static void main(String[] args) {
//		DocumentTrainer dt = new DocumentTrainer();
//		dt.getFileToTrain();
//	}
//}

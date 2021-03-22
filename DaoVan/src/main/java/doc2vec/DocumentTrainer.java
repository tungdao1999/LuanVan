package doc2vec;

import java.io.File;
import java.io.IOException;

import MysqlHelper.MySQLConnectorUlti;
import MysqlHelper.MysqlAction;
import resource.ResourcesUtils;

public class DocumentTrainer {
	private String label_wiki = "wiki_";
	private String label_ITV ="ITV_";
	private String label_VIB ="VIB_";
	private String lable_UPL ="UPL_";
	
	private Doc2VecTrain doc2vec = new Doc2VecTrain();
	
	private static String wiki_Dir = ResourcesUtils.resourcePath + "/lucene/crawled/uploadFile/";
	//UPL_1_LINE_1
	public void trainWikiDoc() {
		int docId = 0;
		String label = "";
		File[] files = new File(wiki_Dir).listFiles();
		for (File file : files) {
			docId = MysqlAction.searchDocumentId(file.getName());
			label = label_wiki+docId+"_LINE_";
			try {
				doc2vec.trainNewFile(file, label);
			} catch (IOException e) {
				// catch train doc2vec
				e.printStackTrace();
			}
		}
	}
	public void trainItViecDoc() {
		
	}
	public void trainVibloDoc() {
		
	}
	public void trainUploadDoc() {
		
	}
}

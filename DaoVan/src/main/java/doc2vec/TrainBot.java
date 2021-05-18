package doc2vec;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import MysqlHelper.MysqlAction;
import entry.Document;
import resource.ResourcesUtils;

public class TrainBot {
	List<Document> documentWaitingToTrain;
	List<File> filesWaitingToTrain = new ArrayList<File>();
	private File mergedFileToTrain;
	private int mergeVersion;
	Doc2VecTrain trainer;
	public void setup() throws SQLException {
		mergeVersion = MysqlAction.getMergeVersion();
		try {
			documentWaitingToTrain = MysqlAction.getWaitingFileToTrain();
			documentWaitingToTrain.sort(new Comparator<Document>() {

				public int compare(Document o1, Document o2) {
					// TODO Auto-generated method stub
					return o1.getDocID() - o2.getDocID();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		for (Document document : documentWaitingToTrain) {
			filesWaitingToTrain.add(new File(document.getFilePath()));
		}
	//	String newFilePath = ResourcesUtils.resourcePath + "/Doc2VecModel/dataFileToTrain/" + "merge" + "V" + mergeVersion + ".txt";
	//	File newMergeFile = new File(newFilePath);
	//	PreTrainMerger.mergeMultipleDoc(newMergeFile, documentWaitingToTrain);
	//	setMergedFileToTrain(newMergeFile);
	}
	public File getMergedFileToTrain() {
		return mergedFileToTrain;
	}
	public void setMergedFileToTrain(File mergedFileToTrain) {
		this.mergedFileToTrain = mergedFileToTrain;
	}
	public boolean startTrain() throws SQLException {
		setup();
		List<String> labelSource  = buildLabelSource();
		trainer = new Doc2VecTrain();
		try {
			trainer.trainNewFile(mergedFileToTrain, labelSource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			for (Document document : documentWaitingToTrain) {
				MysqlAction.updateTrainState(document.getDocID());
			}
		}
		return true;
	}
	public List<String> buildLabelSource() throws SQLException {
		// TODO Auto-generated method stub
		setup();
		List<String> labelSource = new ArrayList<String>();
		for (Document document : documentWaitingToTrain) {
			for (int i = 0; i <= document.getNumLine(); i++) {
				labelSource.add(document.getSourceLabel() + "_" + document.getDocID() + "_" + i);
			}
			
		}
		System.out.println(labelSource.size());
		return labelSource;
	}
	
	public static void main(String[] args) {
		TrainBot bot = new TrainBot();
		try {
			bot.startTrain();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}

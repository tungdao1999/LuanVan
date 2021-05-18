package doc2vec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

import constants.DocumentField;
import constants.Filepath;
import entry.Document;
import resource.ResourcesUtils;

public class PreTrainMerger {
	public static File mergeMultipleFile(File desFile, List<File> files) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(desFile, Charset.forName("UTF-8"), true));
			BufferedReader br = null;
			String line = "";
			for (File file : files) {
				br = new BufferedReader(new FileReader(file, Charset.forName("UTF-8")));
				while((line = br.readLine()) != null) {
					pw.println(line);
				}
			}
			pw.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return desFile;
	}
	public static  File mergeMultipleDirectory(File desFile, List<File> directories) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(desFile, Charset.forName("UTF-8"), true));
			BufferedReader br = null;
			String line = "";
			File[] listFile = null;
			for (File dir : directories) {
				listFile = dir.listFiles();
				for (File file : listFile) {
					br = new BufferedReader(new FileReader(file, Charset.forName("UTF-8")));
					while((line = br.readLine()) != null) {
						pw.println(line);
					}
				}
			}
			pw.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return desFile;
	}
//	public static File mergeProcessedFiles() {
//		File itv_dir = new File(Filepath.itviec_crawled);
//		File vib_dir = new File(Filepath.viblo_crawled);
//		File outputFile = new File(ResourcesUtils.resourcePath + "/Doc2VecModel/merge.txt");
//		List<>
//		return mergeMultipleDirectory(outputFile, itv_dir, vib_dir);
//	}
	public static void main(String[] args) {
//		mergeProcessedFiles();
	}
	public static void mergeMultipleDoc(File newMergeFile, List<Document> documentWaitingToTrain) {
		// TODO Auto-generated method stub
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(newMergeFile, Charset.forName("UTF-8"), true));
			BufferedReader br = null;
			String line = "";
	//		File[] listFile = null;
			for (Document doc : documentWaitingToTrain) {
					File f = new File(doc.getFilePath());
					br = new BufferedReader(new FileReader(f, Charset.forName("UTF-8")));
					while((line = br.readLine()) != null) {
						pw.println(line);
					}
				
			}
			pw.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	//	return newMergeFile;
	}

}

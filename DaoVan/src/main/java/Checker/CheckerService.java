package Checker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;



import preprocess.PreprocessService;
import resource.ResourcesUtils;
import entry.Duplicate;
import printer.PrintResult;

public class CheckerService {
	File inputFile;
	File preSegmentFile;
	File preProcessedFile;
	

	public void setPreSegmentFile(File preSegmentFile) {
		this.preSegmentFile = preSegmentFile;
	}

	public void setPreProcessedFile(File preProcessedFile) {
		this.preProcessedFile = preProcessedFile;
	}
	public CheckerService() {
		
	}
	public CheckerService(File inputFile) {
		super();
		this.inputFile = inputFile;
		buildProcessedFiles();
	}

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	
	public void buildProcessedFiles() {
		try {
			PreprocessService.PreprocessCheckedFile(inputFile.getAbsolutePath(),preSegmentFile.getAbsolutePath(),
					preProcessedFile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public  List<Duplicate> getFinalResult() throws IOException, ParseException{
		 List<Duplicate> dupList = PlagiarismChecker.checkPlagiarism(preProcessedFile);
		 List<String> preProcessList = new ArrayList<String>();

		 String sentence = "";
		 BufferedReader br = new BufferedReader(new FileReader(preSegmentFile));
		 while((sentence = br.readLine()) != null) {
			preProcessList.add(sentence);
		 }
		 br.close();
		 
		 for (Duplicate dup : dupList) {
			 if(dup != null) {
				 dup.setDuplicateSent(preProcessList.get(dup.getDuplicateSentIndex()));
			 }
		}
		 return dupList;
		 
	}
	public static void main(String[] args) {
		File inputFile = new File("D:\\Luan van tot nghiep\\Luan van mau\\KLTN_Ung-dung-Cho-Xe_Ap-dung-React-native_14130110_14130052.docx");
		File presegmentFile = new File(ResourcesUtils.resourcePath + "/tempoData/presegment.txt");
		File preprocessFile = new File(ResourcesUtils.resourcePath + "/tempoData/preprocess.txt");
		CheckerService cs = new CheckerService();
		cs.setInputFile(inputFile);cs.setPreProcessedFile(preprocessFile);cs.setPreSegmentFile(presegmentFile);
		cs.buildProcessedFiles();
		List<Duplicate> listdup = null;
		try {
			listdup = cs.getFinalResult();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (Duplicate dup : listdup) {
//			System.out.println("dupsent: " +dup.getDuplicateSent());
//			System.out.println("dupSource: " + dup.getSourceSent());
//		}
		File f = new File("D:/result.docx");
		PrintResult.printFinalFile(listdup, f);
		
	}
	

}

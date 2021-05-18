package entry;

public class Document {
	private int docID;
	public int getDocID() {
		return docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}


	private String filePath;
	private int numLine;
	private String sourceLabel;
	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getNumLine() {
		return numLine;
	}

	public void setNumLine(int numLine) {
		this.numLine = numLine;
	}

	public String getSourceLabel() {
		return sourceLabel;
	}

	public void setSourceLabel(String sourceLabel) {
		this.sourceLabel = sourceLabel;
	}
	

}

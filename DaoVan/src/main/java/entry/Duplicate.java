package entry;

public class Duplicate {
	int duplicateSentIndex;
	public int getDuplicateSentIndex() {
		return duplicateSentIndex;
	}
	public void setDuplicateSentIndex(int duplicateSentIndex) {
		this.duplicateSentIndex = duplicateSentIndex;
	}
	// Câu bị trùng 
	String duplicateSent;
	// Câu nguồn
	String sourceSent;
	//Link nguồn
	String sourceLink;
	// Điểm trùng lặp(tính theo %)
	int score;
	public String getDuplicateSent() {
		return duplicateSent;
	}
	public void setDuplicateSent(String duplicateSent) {
		this.duplicateSent = duplicateSent;
	}
	public String getSourceSent() {
		return sourceSent;
	}
	public void setSourceSent(String sourceSent) {
		this.sourceSent = sourceSent;
	}
	public String getSourceLink() {
		return sourceLink;
	}
	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	

}

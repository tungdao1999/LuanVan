package entry;

public class Sentence {
	private String label;
	private String content;
	private String source;
	
	//add paragraph, run, etc
	
	public Sentence(String label, String content, String source) {
		super();
		this.label = label;
		this.content = content;
		this.source = source;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
}

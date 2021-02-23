package preprocess;

public abstract class TextProcessing implements ITextProcessor{
	ITextProcessor itp;

	public TextProcessing(ITextProcessor itp) {
		this.itp = itp;
	}
	
	
}

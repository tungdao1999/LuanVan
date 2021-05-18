package printer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.apache.pdfbox.util.Matrix;

public class TestThing {
	void printSubwords(PDDocument document, String searchTerm) throws IOException
	{
	    System.out.printf("* Looking for '%s'\n", searchTerm);
	    for (int page = 0; page <= document.getNumberOfPages(); page++)
	    {
	        List<TextPositionSequence> hits = findSubwords(document, page, searchTerm);
	        for (TextPositionSequence hit : hits)
	        {
	            TextPosition lastPosition = hit.textPositionAt(hit.length() - 1);
	           
	            System.out.printf("  Page %s at %s, %s with width %s and last letter '%s' at %s, %s\n",
	                    page, hit.getX(), hit.getY(), hit.getWidth(),
	                    lastPosition.getUnicode(), lastPosition.getXDirAdj(), lastPosition.getYDirAdj());
	        }
	    }
	}
	public List<TextPositionSequence> findSubwords(PDDocument document, int page, final String searchTerm) throws IOException
	{
	    final List<TextPositionSequence> hits = new ArrayList<TextPositionSequence>();
	    PDFTextStripper stripper = new PDFTextStripper()
	    {
	        @Override
	        protected void writeString(String text, List<TextPosition> textPositions) throws IOException
	        {
	        	
	        	
	            TextPositionSequence word = new TextPositionSequence(textPositions);
	            String string = word.toString();
	            System.out.println(string);
	            int fromIndex = 0;
	            int index;
	            while ((index = string.indexOf(searchTerm, fromIndex)) > -1)
	            {
	                hits.add(word.subSequence(index, index + searchTerm.length()));
	                fromIndex = index + 1;
	            }
	            super.writeString(text, textPositions);
	        }
	    };

	    stripper.setSortByPosition(true);
	    stripper.setStartPage(page);
	    stripper.setEndPage(page);
	    stripper.getText(document);
	    return hits;
	}
	public static void main(String[] args) throws IOException {
		TestThing tt = new TestThing();
		File file = new File("document/JD-PKH.docx.pdf");
        PDDocument document = PDDocument.load(file);
        
        tt.printSubwords(document, "là một trong những");
	}
}

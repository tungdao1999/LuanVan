package printer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationTextMarkup;
import org.apache.pdfbox.text.PDFTextStripper;

public class FindSentence extends PDFTextStripper {
	public FindSentence() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	public void getSentence(String sentence) {
		
	}
	public void paintASentence(double minx, double miny,double maxx, double maxy,int currentpage,double height, double width,int rotation) throws IOException {
		File file = new File("document/JD-PKH.docx.pdf");
        PDDocument document = PDDocument.load(file);
        double page_height;
        double page_width;
        double Newwidth, Newheight, Newminx, Newmaxx, Newminy, Newmaxy;
        int Newrotation;
        
        PDPage page = document.getPage(0);
        List<PDAnnotation> annotations = page.getAnnotations();
        
        
//        Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
        
        
        PDColor red = new PDColor(new float[] { 2, 0, 0 }, PDDeviceRGB.INSTANCE);
        
        page_height = page.getMediaBox().getHeight();
        System.out.println(page_height);
        page_width  = page.getMediaBox().getWidth();
        System.out.println(page_width);
        Newheight = height;
        
        Newminx = minx;
        Newmaxx = maxx;
        Newminy = page_height - miny;
        Newmaxy = page_height - maxy + Newheight;
        
        PDAnnotationTextMarkup txtMark = new PDAnnotationTextMarkup(PDAnnotationTextMarkup.SUB_TYPE_HIGHLIGHT);
        txtMark.setColor(red);
        txtMark.setConstantOpacity((float)0.3);
        
        PDRectangle position = new PDRectangle();
        position.setLowerLeftX((float) Newminx);
        position.setLowerLeftY((float) Newminy);
        position.setUpperRightX((float) Newmaxx);
        position.setUpperRightY((float) ((float) Newmaxy+Newheight));
        txtMark.setRectangle(position);
        
        float[] quads = new float[8];
        quads[0] = position.getLowerLeftX()-10;  // x1
        System.out.println(quads[0]);
        quads[1] = position.getUpperRightY()-2; // y1
        System.out.println(quads[1]);
        quads[2] = position.getUpperRightX(); // x2
        System.out.println(quads[2]);
        quads[3] = quads[1]; // y2
        System.out.println(quads[3]);
        quads[4] = quads[0]-10;  // x3
        System.out.println(quads[4]);
        quads[5] = position.getLowerLeftY()-2; // y3
        System.out.println(quads[5]);
        quads[6] = quads[2]; // x4
        System.out.println(quads[6]);
        quads[7] = quads[5]; // y4
        System.out.println(quads[7]);
        txtMark.setQuadPoints(quads);
//        txtMark.setContents(tokenStream.get(i).toString());
        annotations.add(txtMark);
        

        File highlighted_doc = new File("document/result1.pdf");
        document.save(highlighted_doc);

        document.close();
	}
	public static void main(String[] args) throws IOException {
//		minx72.0
//		miny108.2689208984375
//		maxx370.89947509765625
//		maxy108.2689208984375
//		current page number1
//		height8.53759765625
//		width10.111846923828125
//		rotation0
		FindSentence fs = new FindSentence();
		fs.paintASentence(72.0, 108.2689208984375, 370.89947509765625, 108.2689208984375, 1, 8.53759765625, 10.111846923828125, 0);
	}
}

package printer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.hwpf.sprm.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.awt.*;
public class Highlighter {
		 public void paintText() throws Exception {
			 HWPFDocument document = new HWPFDocument(new FileInputStream("document/luanvan1.doc"));
			  Range range = document.getRange();
			  for (int p = 50 ; p < range.numParagraphs(); p++) {
			   Paragraph paragraph = range.getParagraph(p);
			   paragraph.text();
//			System.out.println(paragraph);
//			System.out.println(paragraph.text());
			   if (!paragraph.getShading().isEmpty()) {
//			System.out.println("Paragraph's shading: " + paragraph.getShading());
			   }

			   for (int r = 0; r < paragraph.numCharacterRuns(); r++) {
			    CharacterRun run = paragraph.getCharacterRun(r);
//			    System.out.println(run.text());
//			System.out.println(run);
			    if (run.isHighlighted()) {
//			System.out.println("Run's highlighted color: " + run.getHighlightedColor());
			    }
			    if(run.text().equalsIgnoreCase("sự hướng") || run.text().contains("sự hướng")) {
			    	byte numcolor = 2;
			    	run.setHighlighted(numcolor);
			    	String sentence = run.text();
					String txt = "sự hướng";
					String textNeed = sentence.substring(0,sentence.indexOf("sự hướng"));
					System.out.println(textNeed);
					paragraph.insertBefore(txt);
					paragraph.replaceText(run.text(), textNeed);
					
					run.setHighlighted((byte) 3);
					
					
//			    	System.out.println(run.getHighlightedColor());
//			    	System.out.println("has word"+ run.isBold());
			    }
			   }
			  }
//			  System.out.println(range.numParagraphs());
			  document.write(new File("document/luanvan2.doc"));
			  document.close();
		 }
		 public static void main(String[] args) throws Exception {
			 Highlighter hl = new Highlighter();
			 hl.paintText();
			 
		 }
}

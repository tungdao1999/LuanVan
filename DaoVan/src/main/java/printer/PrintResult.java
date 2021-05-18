package printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;

import entry.Duplicate;

public class PrintResult {
	static Long[] widths = {(long) 1400, (long) 2700, (long) 3000, (long) 1440};
	public static void printFinalFile(List<Duplicate> listDup, File resultFile) {
		XWPFDocument document = new XWPFDocument();
		try{  
			FileOutputStream out = new FileOutputStream(resultFile);
			XWPFTable tab = document.createTable();
			CTTblGrid grid = tab.getCTTbl().addNewTblGrid();
		    for (long w : widths) {
		      grid.addNewGridCol().setW(BigInteger.valueOf(w));
		    }    
			XWPFTableRow row = tab.getRow(0);
			
			row.getCell(0).setText("Câu trùng lặp");
			row.addNewTableCell().setText("Tài liệu");
			row.addNewTableCell().setText("Câu nguồn");
			row.addNewTableCell().setText("Độ tương đồng");
			int i  = 0;
			for (Duplicate duplicate : listDup) {
				i++;
				XWPFTableRow newRow = tab.insertNewTableRow(i);
				if(duplicate != null) {
					newRow.addNewTableCell().setText(duplicate.getDuplicateSent());
					newRow.addNewTableCell().setText(duplicate.getSourceLink());
					newRow.addNewTableCell().setText(duplicate.getSourceSent());
					newRow.addNewTableCell().setText(String.valueOf(duplicate.getScore()));
				}
			}
			document.write(out);
			
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

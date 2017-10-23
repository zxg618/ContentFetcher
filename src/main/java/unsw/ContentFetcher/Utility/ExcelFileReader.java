package unsw.ContentFetcher.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import unsw.ContentFetcher.Entity.Record;

public class ExcelFileReader extends Reader {
	protected String filePath = "";
	
	public ExcelFileReader(String filePath) {
		this.filePath = filePath;
	}
	
	public Record[] readFileContent() {
		ArrayList<Record> recordList = new ArrayList<Record>();
		File file = new File(this.filePath);
		
		try {
		    XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
		    
		    XSSFSheet sheet = wb.getSheetAt(0);
		    XSSFRow row;
		    
		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();

		    for(int r = 1; r < rows; r++) {
		        row = sheet.getRow(r);
		        String fullName = "";
	        	String primaryCompany = "";
	        	String biography = "";
		        if(row != null) {
		        	fullName = this.getContentFromCell(row, 0);
		        	primaryCompany = this.getContentFromCell(row, 1);
		        	biography = this.getContentFromCell(row, 2);
		        }
		        
		        Record record = new Record(r, fullName, primaryCompany, biography);
		        if (record.isValid()) {
		        	recordList.add(record);
		        }
		    }
		    
		    wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return recordList.toArray(new Record[0]);
	}
	
	protected String getContentFromCell(XSSFRow row, int cellNum) {
		XSSFCell cell = row.getCell((short)cellNum);
		String content = "";
        if(cell != null) {
        	content = cell.toString().trim();
        }
        
        return content;
	}
}

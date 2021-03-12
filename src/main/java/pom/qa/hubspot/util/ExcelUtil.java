package pom.qa.hubspot.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName)
	{
		try {
			FileInputStream fip = new FileInputStream(appConstants.TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(fip);
			sheet = book.getSheet(sheetName);
			
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();

			Object data[][] = new Object[rowCount][colCount];
			
			for(int r=0; r<rowCount;r++)
			{
				for(int c=0; c< colCount;c++)
				{
					data[r][c] = sheet.getRow(r+1).getCell(c);
				}
			}
			return data;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

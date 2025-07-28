package excelFileUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetdatafromExcelFile {
	public String GetDataFromExcelFile(String Sheet, int row, int cell) throws EncryptedDocumentException, IOException{
		FileInputStream fis = new FileInputStream("./TestData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String Value = wb.getSheet(Sheet).getRow(row).getCell(cell).getStringCellValue();
		wb.close();
		return Value;
	}
	
	public int getLastRowNum(String Sheet) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./TestData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		return wb.getSheet(Sheet).getLastRowNum();
	}

}

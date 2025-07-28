package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.instagram.com/");
		Thread.sleep(2000);
		
		String xlpath = "./TestData/TestScriptData.xlsx";
		FileInputStream fis = new FileInputStream(xlpath);
		Workbook wb = WorkbookFactory.create(fis);
		//Sheet sh = wb.getSheet("Campaign");
		
		//Row row = sh.getRow(1);
		//String Campaign_name = row.getCell(2).getStringCellValue();
		//String Target_Size = row.getCell(3).getStringCellValue();
		
		Thread.sleep(2000);
		
		FileOutputStream fos = new FileOutputStream(xlpath);
		
		wb.write(fos);
		
		wb.close();
	}

}

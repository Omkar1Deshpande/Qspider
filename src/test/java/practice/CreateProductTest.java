package practice;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import excelFileUtility.GetdatafromExcelFile;
import javaUtility.GetdatafromJavaFile;
import pom.pom_Homepage;
import pom.pom_addProduct;
import webDriverUtility.WebDriverUtility;

@Listeners(listenersUtility.ListenersImplementation.class)
public class CreateProductTest extends BaseClass{
	@Test
	public void createTheProduct() throws IOException {
		
		//Utilities
		WebDriverUtility wU = new WebDriverUtility();
		GetdatafromExcelFile eUtil = new GetdatafromExcelFile();
		GetdatafromJavaFile jUtil = new GetdatafromJavaFile();
		
		//POM Pages
		pom_Homepage home = new pom_Homepage(driver);
		pom_addProduct AP = new pom_addProduct(driver);
		
		//1. Wait for Page to load
		wU.waitForPageToload(driver);
		
		//2. Add Product
		home.getProducts().click();
		home.getCreateProd().click();
		
		//3. Get Data from Excel
		String Product_Name = eUtil.GetDataFromExcelFile("Product", 1, 2);
		String Quantity = eUtil.GetDataFromExcelFile("Product", 1, 3);
		String PricePerUnit = eUtil.GetDataFromExcelFile("Product", 1, 4);
		
		//4. Enter the Data
		int num = jUtil.random(100);
		AP.getText_PName().sendKeys(Product_Name+num);
		AP.getText_quantity().sendKeys(Quantity);
		AP.getText_price().clear();
		AP.getText_price().sendKeys(PricePerUnit);
		wU.select("Electronics", AP.getDropdown_SelectCategory());
		wU.select("Vendor_68300 - (Electronics)", AP.getDropdown_SelectVendor());
		
		
		//5. Add Product
		AP.getButton_Add().click();
		
		//6. Check for toast message
		wU.waitforVisibilityofElement(driver, home.getAlert());	
		String Alert_msg = home.getToast().getText();
//		String Expected_msg;
		String Expected_msg = "Product "+Product_Name+num+" Successfully Added";
		
		Assert.assertEquals(Alert_msg, Expected_msg);
		
		//7. Close Toast Message
		home.getBtn_toast_close().click();
		
		
	}

}

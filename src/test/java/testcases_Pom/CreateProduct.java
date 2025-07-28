package testcases_Pom;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import excelFileUtility.GetdatafromExcelFile;
import javaUtility.GetdatafromJavaFile;
import pom.pom_Homepage;
import pom.pom_Login;
import pom.pom_addProduct;
import propertiesFileUtility.GetdatafromPropFile;
import webDriverUtility.WebDriverUtility;

public class CreateProduct {

	public static void main(String[] args) throws IOException {
		GetdatafromPropFile propUtil = new GetdatafromPropFile();
		String BROWSER = propUtil.GetDataFromPropFile("browser");
		String URL = propUtil.GetDataFromPropFile("url");
		String USERNAME = propUtil.GetDataFromPropFile("username");
		String PASSWORD = propUtil.GetDataFromPropFile("password");
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(URL);
		//POM_Login
		pom_Login PL = new pom_Login(driver);
		PL.getUserame().sendKeys(USERNAME);
		PL.getPassword().sendKeys(PASSWORD);
		PL.getSignIn().click();
		
		//WebDriver Utility
		WebDriverUtility wU = new WebDriverUtility();
		wU.waitForPageToload(driver);

		//Pom_Homepage
		pom_Homepage home = new pom_Homepage(driver);
		home.getProducts().click();
		home.getCreateProd().click();
		
		//Excel Utility
		GetdatafromExcelFile eUtil = new GetdatafromExcelFile();
		String Product_Name = eUtil.GetDataFromExcelFile("Product", 1, 2);
		String Quantity = eUtil.GetDataFromExcelFile("Product", 1, 3);
		String PricePerUnit = eUtil.GetDataFromExcelFile("Product", 1, 4);
		
		//Java Utility
		GetdatafromJavaFile jUtil = new GetdatafromJavaFile();
		
		//Pom_addProduct
		pom_addProduct AP = new pom_addProduct(driver);
		AP.getText_PName().sendKeys(Product_Name+jUtil.random(100));
		AP.getText_quantity().sendKeys(Quantity);
		AP.getText_price().sendKeys(PricePerUnit);
		wU.select("Electronics", AP.getDropdown_SelectCategory());
		wU.select("Vendor_68300 - (Electronics)", AP.getDropdown_SelectVendor());
		AP.getButton_Add().click();
		
		//Verify Product creation
		wU.waitforVisibilityofElement(driver, home.getAlert());	
		String Alert_msg = home.getToast().getText();
		if(Alert_msg.contains(Product_Name)){
			System.out.println("Product is Created!");
		}
		else {
			System.out.println("Product is not Created!");
		}
		
		//Logout
		home.getBtn_toast_close().click();
		home.getUser().click();
		home.getBtn_logout().click();
		
	
		driver.close();
		
		

	}

}

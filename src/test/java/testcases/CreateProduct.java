package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import excelFileUtility.GetdatafromExcelFile;
import javaUtility.GetdatafromJavaFile;
import propertiesFileUtility.GetdatafromPropFile;
import webDriverUtility.WebDriverUtility;

public class CreateProduct {

	public static void main(String[] args) throws IOException, InterruptedException {
		//Properties Utility
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
				Thread.sleep(2000);
				
				driver.findElement(By.id("username")).sendKeys(USERNAME);
				driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
				driver.findElement(By.xpath("//button[text()='Sign In']")).click();
				
				//WebDriver Utility
				WebDriverUtility wUtil = new WebDriverUtility();
				wUtil.waitForPageToload(driver);
				
				WebElement Product = driver.findElement(By.linkText("Products"));
				wUtil.clickonWebElement(driver, Product);
				WebElement AddProduct = driver.findElement(By.xpath("//span[contains(text(),'Add Product')]"));
				wUtil.waitforVisibilityofElement(driver, AddProduct);
				wUtil.clickonWebElement(driver, AddProduct);
				
				//Excel Utility
				GetdatafromExcelFile eUtil = new GetdatafromExcelFile();
				String Product_Name = eUtil.GetDataFromExcelFile("Product", 1, 2);
				String Quantity = eUtil.GetDataFromExcelFile("Product", 1, 3);
				String PricePerUnit = eUtil.GetDataFromExcelFile("Product", 1, 4);
				
				//Java Utility
				GetdatafromJavaFile jUtil = new GetdatafromJavaFile();
				
				driver.findElement(By.name("productName")).sendKeys(Product_Name+jUtil.random(100));
				driver.findElement(By.name("quantity")).sendKeys(Quantity);
				driver.findElement(By.name("price")).sendKeys(PricePerUnit);
				
				WebElement pCategory = driver.findElement(By.name("productCategory"));
				wUtil.select("Electronics", pCategory);
				
				WebElement pVendor = driver.findElement(By.name("vendorId"));
				wUtil.select("Vendor_68300 - (Electronics)", pVendor);	
				
				WebElement Add = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
				wUtil.clickonWebElement(driver, Add);
				
				WebElement toast = driver.findElement(By.xpath("//div[@role='alert']"));
				wUtil.waitforVisibilityofElement(driver, toast);
				
				String Alert_msg = toast.getText();
				if(Alert_msg.contains(Product_Name)){
					System.out.println("Product is Created!");
				}
				else {
					System.out.println("Product is not Created!");
				}
				
				WebElement user_icon = driver.findElement(By.className("user-icon"));
				wUtil.clickonWebElement(driver, user_icon);
				WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
				wUtil.clickonWebElement(driver, logout);
	}

}

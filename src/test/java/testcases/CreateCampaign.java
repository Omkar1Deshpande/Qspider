package testcases;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
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

public class CreateCampaign {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
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
		
		WebElement CCampaign = driver.findElement(By.xpath("//*[text()[contains(.,'Create Campaign')]]"));
		wUtil.waitforVisibilityofElement(driver, CCampaign);
		wUtil.clickonWebElement(driver, CCampaign);
		
		//Excel Utility
		GetdatafromExcelFile eUtil = new GetdatafromExcelFile();
		String Camapaign_Name = eUtil.GetDataFromExcelFile("Campaign", 1, 2);
		String Target_Size = eUtil.GetDataFromExcelFile("Campaign", 1, 3);
		
		//Java Utility
		GetdatafromJavaFile jUtil = new GetdatafromJavaFile();
		
		driver.findElement(By.name("campaignName")).sendKeys(Camapaign_Name+jUtil.random(100));
		driver.findElement(By.name("targetSize")).sendKeys(Target_Size);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/form/button")).click();
		
		WebElement toast = driver.findElement(By.xpath("//div[@role='alert']"));
		wUtil.waitforVisibilityofElement(driver, toast);
		
		String Alert_msg = toast.getText();
		if(Alert_msg.contains(Camapaign_Name)){
			System.out.println("Campaign is Created!");
		}
		else {
			System.out.println("Campaign is not Created!");
		}
		
		WebElement user_icon = driver.findElement(By.className("user-icon"));
		wUtil.clickonWebElement(driver, user_icon);
		WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
		wUtil.clickonWebElement(driver, logout);
		
	}

}

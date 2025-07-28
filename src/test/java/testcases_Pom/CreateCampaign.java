package testcases_Pom;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

import excelFileUtility.GetdatafromExcelFile;
import javaUtility.GetdatafromJavaFile;
import pom.pom_Homepage;
import pom.pom_Login;
import pom.pom_addCampaign;
import propertiesFileUtility.GetdatafromPropFile;
import webDriverUtility.WebDriverUtility;

public class CreateCampaign {

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
		home.getCampaigns().click();
		home.getCreateCamp().click();
		
		//Excel Utility
		GetdatafromExcelFile eUtil = new GetdatafromExcelFile();
		String Camapaign_Name = eUtil.GetDataFromExcelFile("Campaign", 1, 2);
		String Target_Size = eUtil.GetDataFromExcelFile("Campaign", 1, 3);
		String Camapaign_Status = eUtil.GetDataFromExcelFile("Campaign", 1, 4);
		
		//Java Utility
		GetdatafromJavaFile jUtil = new GetdatafromJavaFile();
		
		//Pom_addCampaign
		pom_addCampaign CC = new pom_addCampaign(driver);
		CC.getText_CName().sendKeys(Camapaign_Name+jUtil.random(100));
		CC.getText_CTargetSize().sendKeys(Target_Size);
		CC.getText_CStatus().sendKeys(Camapaign_Status);
		CC.getCalendar_Date().sendKeys("23072025");
		CC.getButton_AddCampaign().click();
		
		//Verify Campaign creation
		wU.waitforVisibilityofElement(driver, home.getAlert());	
		String Alert_msg = home.getToast().getText();
		String Expected_msg = "Campaign "+Camapaign_Name+" Successfully Added";

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(Alert_msg, Expected_msg);
		soft.assertAll();
		
		//Logout
		home.getBtn_toast_close().click();
		home.getUser().click();
		home.getBtn_logout().click();
		
	
		driver.close();
		
	}

}

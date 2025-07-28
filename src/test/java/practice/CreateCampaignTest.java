package practice;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import excelFileUtility.GetdatafromExcelFile;
import javaUtility.GetdatafromJavaFile;
import pom.pom_Homepage;
import pom.pom_addCampaign;
import webDriverUtility.WebDriverUtility;

@Listeners(listenersUtility.ListenersImplementation.class)
public class CreateCampaignTest extends BaseClass{
	@Test
	public void createTheCampaign() throws IOException {
		
		//Utilities
		WebDriverUtility wU = new WebDriverUtility();
		GetdatafromExcelFile eUtil = new GetdatafromExcelFile();
		GetdatafromJavaFile jUtil = new GetdatafromJavaFile();
		
		//POM Pages
		pom_Homepage home = new pom_Homepage(driver);
		pom_addCampaign CC = new pom_addCampaign(driver);
		
		//1. Wait for Page to load
		wU.waitForPageToload(driver);
		
		//2. Create Campaign
		home.getCampaigns().click();
		home.getCreateCamp().click();
		
		//3. Get Data from Excel
		String Camapaign_Name = eUtil.GetDataFromExcelFile("Campaign", 1, 2);
		String Target_Size = eUtil.GetDataFromExcelFile("Campaign", 1, 3);
		String Camapaign_Status = eUtil.GetDataFromExcelFile("Campaign", 1, 4);
		
		//4. Enter the Data
		CC.getText_CName().sendKeys(Camapaign_Name);
		CC.getText_CTargetSize().sendKeys(Target_Size);
		CC.getText_CStatus().sendKeys(Camapaign_Status);
		//CC.getCalendar_Date().sendKeys(jUtil.getRequiredDate(30));
		CC.getCalendar_Date().sendKeys("28072025");
		
		//5. Create Campaign
		CC.getButton_AddCampaign().click();
		
		//6. Check for toast message
		wU.waitforVisibilityofElement(driver, home.getAlert());	
		String Alert_msg = home.getToast().getText();
		//Campaign qwrw Successfully Added
		String Expected_msg = "Campaign "+Camapaign_Name+" Successfully Added";
		Assert.assertEquals(Alert_msg, Expected_msg);
		//soft.assertAll();
		
		//7. Close Toast Message
		home.getBtn_toast_close().click();
		
	}

}

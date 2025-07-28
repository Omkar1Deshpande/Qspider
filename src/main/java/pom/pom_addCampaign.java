package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pom_addCampaign {
	WebDriver driver;

	public pom_addCampaign(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="campaignName")
	private WebElement text_CName;
	
	@FindBy(name="targetSize")
	private WebElement text_CTargetSize;
	
	@FindBy(xpath="//*[@id=\"content\"]/div[2]/form/button")
	private WebElement button_AddCampaign;

	@FindBy(name="campaignStatus")
	private WebElement text_CStatus;
	
	@FindBy(name="expectedCloseDate")
	private WebElement calendar_Date;

	public WebElement getText_CName() {
		return text_CName;
	}

	public WebElement getText_CTargetSize() {
		return text_CTargetSize;
	}

	public WebElement getButton_AddCampaign() {
		return button_AddCampaign;
	}

	public WebElement getText_CStatus() {
		return text_CStatus;
	}

	public WebElement getCalendar_Date() {
		return calendar_Date;
	}
	
}
	
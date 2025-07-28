package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pom_Homepage {
	WebDriver driver;

	public pom_Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Campaigns")
	private WebElement campaigns;
	
	@FindBy(linkText="Products")
	private WebElement products;
	
	@FindBy(linkText="Contacts")
	private WebElement contacts;
	
	@FindBy(xpath="//*[text()[contains(.,'Create Campaign')]]")
	private WebElement CreateCamp;
	
	@FindBy(xpath="//span[contains(text(),'Add Product')]")
	private WebElement CreateProd;
	
	@FindBy(className="user-icon")
	private WebElement user;
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement alert;
	
	@FindBy(xpath="//div[text()='Logout ']")
	private WebElement btn_logout;
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement toast;
	
	@FindBy(xpath="//button[contains(text(),'✖︎')]")
	private WebElement btn_toast_close;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCampaigns() {
		return campaigns;
	}

	public WebElement getProducts() {
		return products;
	}

	public WebElement getContacts() {
		return contacts;
	}

	public WebElement getCreateCamp() {
		return CreateCamp;
	}
	
	public WebElement getCreateProd() {
		return CreateProd;
	}

	public WebElement getUser() {
		return user;
	}

	public WebElement getAlert() {
		return alert;
	}

	public WebElement getBtn_logout() {
		return btn_logout;
	}

	public WebElement getToast() {
		return toast;
	}

	public WebElement getBtn_toast_close() {
		return btn_toast_close;
	}
	
	
}

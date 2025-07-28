package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pom_Login {
	
	WebDriver driver;

	public pom_Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="username")
	private WebElement Userame;
	
	@FindBy(id="inputPassword")
	private WebElement Password;
	
	@FindBy(xpath="//button[contains(text(),'Sign In')]")
	private WebElement SignIn;

	public WebElement getUserame() {
		return Userame;
	}

	public WebElement getPassword() {
		return Password;
	}

	public WebElement getSignIn() {
		return SignIn;
	}
	
	
}

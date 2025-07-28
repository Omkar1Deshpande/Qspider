package baseClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import pom.pom_Homepage;
import pom.pom_Login;
import propertiesFileUtility.GetdatafromPropFile;
import webDriverUtility.WebDriverUtility;

public class BaseClass {
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	
	@BeforeSuite
	public void opneDBConnectivity() {
		System.out.println("DB Connection Open");
	}
	
	@BeforeTest
	public void startParallelExecution() {
		System.out.println("Pre conditions Done");
	}
	
	@BeforeClass
	public void launchBrowser() throws IOException {
		GetdatafromPropFile propUtil = new GetdatafromPropFile();
		String BROWSER = propUtil.GetDataFromPropFile("browser");
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		sdriver=driver;
		driver.manage().window().maximize();
		System.out.println("Browser Launched");
	}
	
	@BeforeMethod
	public void loginToApp() throws IOException {
		WebDriverUtility wU = new WebDriverUtility();
		GetdatafromPropFile propUtil = new GetdatafromPropFile();
		wU.waitForPageToload(driver);
		String URL = propUtil.GetDataFromPropFile("url");
		String USERNAME = propUtil.GetDataFromPropFile("username");
		String PASSWORD = propUtil.GetDataFromPropFile("password");
		driver.get(URL);
		pom_Login PL = new pom_Login(driver);
		PL.getUserame().sendKeys(USERNAME);
		PL.getPassword().sendKeys(PASSWORD);
		PL.getSignIn().click();
		
		System.out.println("Logged in");
	}
	
	@AfterMethod
	public void logoutToApp() {
		pom_Homepage home = new pom_Homepage(driver);
		home.getUser().click();
		home.getBtn_logout().click();
		System.out.println("Logged out");
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
		System.out.println("Browser Closed");
	}
	
	@AfterTest
	public void stopParallelExecution() {
		System.out.println("Post Conditions");
	}
	
	@AfterSuite	
	public void closeDBConnectivity() {
		System.out.println("DB Connection Closed");
	}

}

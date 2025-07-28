package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class PropertiesFile {

	public static void main(String[] args) throws IOException, InterruptedException {
		FileInputStream fis = new FileInputStream("./src/test/resources/ExternalResources/commondata.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		WebDriver driver = null;
		
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get(url);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("Name 4");
		driver.findElement(By.name("campaignStatus")).sendKeys("Status 1");
		driver.findElement(By.name("targetSize")).sendKeys("5");
		driver.findElement(By.name("expectedCloseDate")).sendKeys("3172025");
		driver.findElement(By.name("targetAudience")).sendKeys("Audience 1");
		//driver.findElement(By.name("textarea")).sendKeys("Description Hola");
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		driver.findElement(By.xpath("//button[text()='✖︎']")).click();
		
		Thread.sleep(2000);
		
		WebElement we = driver.findElement(By.className("user-icon"));
		Actions act = new Actions(driver);
		act.moveToElement(we).click();
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		
		//driver.close();
		
	}

}

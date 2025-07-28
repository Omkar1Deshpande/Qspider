package dataDrivenTesting;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JSONFile {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		String jsonpath = "./src/test/resources/ExternalResources/commondata.json";
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(new FileReader(jsonpath));
		String browser = obj.get("browser").toString();
		String url = obj.get("url").toString();
		String username = obj.get("username").toString();
		String password = obj.get("password").toString();
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get(url);
		
		WebElement Un = driver.findElement(By.name("username"));
		Un.sendKeys(username);
		WebElement Pwd = driver.findElement(By.name("password"));
		Pwd.sendKeys(password);
		
		driver.findElement(By.xpath("//div[contains(text(),'Log in')]")).click();
		
		//driver.close();
		
	}

}

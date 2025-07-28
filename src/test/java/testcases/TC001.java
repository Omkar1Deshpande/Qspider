package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TC001 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8098/");
		Thread.sleep(2000);
		
		//login
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/section/div/div/div[2]/div/div/div/form/button")).click();
		
		//create Campaign
		String Campaign = "Tester0002";
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()[contains(.,'Create Campaign')]]")).click();
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);
		driver.findElement(By.name("targetSize")).sendKeys("2");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/form/button")).click();
		
		//Check campaign created
		
		
		//logout
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/button")).click();
		WebElement we = driver.findElement(By.xpath("//*[@id=\"navbarNav\"]/div/div/div"));
		act.moveToElement(we).perform();
		driver.findElement(By.xpath("//*[@id=\"navbarNav\"]/div/div/div[2]/div[3]")).click();
	}

}

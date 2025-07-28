package testcases;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TC002 {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8098/");
		
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Create Campaign')]")).click();
		driver.findElement(By.name("campaignName")).sendKeys("TesterO"+new Random().nextInt(100));
		driver.findElement(By.name("targetSize")).sendKeys(""+new Random().nextInt(100));
		
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("MM-dd-YYYY");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		driver.findElement(By.name("campaignStatus")).sendKeys("Ongoing till "+cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String datereq = sim.format(cal.getTime());
		driver.findElement(By.name("expectedCloseDate")).sendKeys(datereq);
		driver.findElement(By.xpath("//button[contains(text(),'Create Campaign')]")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'✖︎')]")).click();
		Thread.sleep(2000);
		
		WebElement we = driver.findElement(By.className("user-icon"));
		Actions act = new Actions(driver);
		act.moveToElement(we).click();
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		
	}
	
}

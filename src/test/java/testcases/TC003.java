package testcases;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC003 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8098/");
		
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
		
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Add Product')]")).click();
		
		driver.findElement(By.name("productName")).sendKeys("Product "+new Random().nextInt(1000));
		driver.findElement(By.name("quantity")).sendKeys(""+new Random().nextInt(1000));
		driver.findElement(By.name("price")).sendKeys(""+new Random().nextInt(250,500));
		
		
		WebElement cat = driver.findElement(By.name("productCategory"));
		Select sCat = new Select(cat);
		sCat.selectByIndex(2);
		
		WebElement ven = driver.findElement(By.name("vendorId"));
		Select sVen = new Select(ven);
		sVen.selectByIndex(10);
		
		driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'✖︎')]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[1]/div/div[4]/input")).sendKeys(args);
		
		WebElement we = driver.findElement(By.className("user-icon"));
		Actions act = new Actions(driver);
		act.moveToElement(we).click();
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		
		//search for created product
		
	}

}

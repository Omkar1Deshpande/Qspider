package practice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class FBCC {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/");

		TakesScreenshot ss = (TakesScreenshot) driver;

		File temp = ss.getScreenshotAs(OutputType.FILE);

		File perm = new File("./target/Screenshot.jpeg");

		FileHandler.copy(temp, perm);

		driver.quit();

	}

}


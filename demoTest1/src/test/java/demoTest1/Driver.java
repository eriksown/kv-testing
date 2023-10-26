package demoTest1;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Driver {
	
	public static WebDriver driver;
	
	
	@Parameters("browser")
	@BeforeTest(alwaysRun = true)
	public void startWebDriver(String br) {
		if (br.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (br.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
	}
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	public void takePicture(WebDriver driver, String testName) throws IOException {
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshotFile , new File(".\\screenshots\\"+testName+".png"));
	}
}

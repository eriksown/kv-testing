package demoTest1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		System.out.println("Browser should be: "+br);
		
		driver.manage().window().maximize();
		System.out.println("Driver is: "+br);
	}
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
